package info.daviot.soup.hearthstone

import info.daviot.scraper.Scraper
import scala.concurrent.Future
import info.daviot.scraper.DispatchUrlReader
import org.jsoup.nodes.Document
import org.jsoup.Jsoup
import info.daviot.cards.Deck
import org.jsoup.nodes.Element
import scala.collection.JavaConversions._
import scala.concurrent.ExecutionContext.Implicits.global
import info.daviot.soup.DispatchJsoupScraper

class HsTopDecksScraper(val initial: Iterable[String]) extends DispatchJsoupScraper {
  type Data = Deck

  def parseCards(content: Element) = for {
    a <- content.select("a")
    name = a.select("span.name").head.text
    count = a.select("span.count").headOption.map(_.text.toInt).getOrElse(1)
  } yield s"${count}x $name"

  def extract(id: Id, content: String): Future[Option[Deck]] = for {
    doc <- parseDocument(content)
  } yield for {
    cl <- doc.select("span.deck-info").headOption
    c = cl.text.replaceAll("Class:", "").replaceAll("- Type.*", "").trim
    content = doc.select("#neutral , #classes").select("li").map(_.text)
  } yield Deck(doc.title, c, Deck.parse(content), id)

  def links(content: String): Future[List[String]] =
    for {
      doc <- parseDocument(content)
    } yield (for {
      link <- doc.getElementsByAttributeValueStarting("href", "http://www.hearthstonetopdecks.com/decks/").toList
    } yield link.attr("href")).distinct

}
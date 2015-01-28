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

class GosuGamersScraper(val initial: Iterable[String]) extends DispatchJsoupScraper {
  type Data = Deck

  val classRe = """.*Class: (\w+).*""".r

  def parseCards(content: Element) = for {
    a <- content.select("a")
    name = a.select("span.name").head.text
    count = a.select("span.count").headOption.map(_.text.toInt).getOrElse(1)
  } yield s"${count}x $name"

  def extract(id: Id, content: String): Future[Option[Deck]] = for {
    doc <- parseDocument(content)
  } yield for {
    content <- doc.select("div.deck").headOption
    deckStats <- doc.select("div.deck-stat-summary").headOption
    classRe(cl) = deckStats.text
  } yield Deck(doc.title, cl, Deck.parse(parseCards(content)), id)

  def links(content: String): Future[List[String]] =
    for {
      doc <- parseDocument(content)
    } yield (for {
      link <- doc.getElementsByAttributeValueStarting("href", "/hearthstone/decks/").toList
    } yield "http://www.gosugamers.net" + link.attr("href")).distinct

}
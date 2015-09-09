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
import info.daviot.scraper.DataParser
import info.daviot.scraper.LinksParser
import info.daviot.soup.JsoupParser
import org.jsoup.select.Elements
import info.daviot.cards.Card

class HsTopDecksScraper(
  val initial: Iterable[String],
  cacheFolder: String,
  season: String)
  extends DispatchJsoupScraper[Deck](
    HstdDataParser,
    new HstdLinksParser(season),
    cacheFolder)

object HstdDataParser extends DataParser[String, Deck] with JsoupParser {
  def parseCards(content: Element) = for {
    a <- content.select("a")
    name = a.select("span.name").head.text
    count = a.select("span.count").headOption.map(_.text.toInt).getOrElse(1)
  } yield s"${count}x $name"

  private def parseCards(data: Elements) = for {
    cardLine <- data
    name = cardLine.select(".card-name").head.text
    count = cardLine.select(".card-count").head.text.toInt
  } yield Card(count, name)

  def extract(id: String, content: String): Future[Option[Deck]] = for {
    doc <- parseDocument(content)
  } yield for {
    cl <- doc.select("div.deck-info").headOption
    c = cl.select("a").head.text
    cards = doc.select("a.card-frame")
  } yield Deck(doc.title, c, parseCards(cards).toList, id)
}

class HstdLinksParser(season: String) extends LinksParser[String] with JsoupParser {
  def extract(id: String, content: String): Future[List[String]] =
    for {
      doc <- parseDocument(content)
    } yield (for {
      link <- doc.getElementsByAttributeValueMatching("href",
        s"(http://www.hearthstonetopdecks.com/decks/|http://www.hearthstonetopdecks.com/deck-category/.*/season-$season/page/)").toList
      if !id.contains("/decks/") // do not follow links from deck page
    } yield link.attr("href")).distinct
}
 
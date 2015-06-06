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
import info.daviot.scraper.FileCache
import info.daviot.scraper.DataParser
import info.daviot.soup.JsoupParser
import info.daviot.scraper.LinksParser

class GosuGamersScraper(
  val initial: Iterable[String],
  cacheFolder: String)
  extends DispatchJsoupScraper[Deck](GgDataParser, GgLinksParser, cacheFolder)

object GgDataParser extends DataParser[String, Deck] with JsoupParser {
  val classRe = """.*Class: (\w+).*""".r

  def parseCards(content: Element) = for {
    a <- content.select("a")
    name = a.select("span.name").head.text
    count = a.select("span.count").headOption.map(_.text.toInt).getOrElse(1)
  } yield s"${count}x $name"

  def extract(id: String, content: String): Future[Option[Deck]] = for {
    doc <- parseDocument(content)
  } yield for {
    content <- doc.select("div.deck").headOption
    deckStats <- doc.select("div.deck-stat-summary").headOption
    classRe(cl) = deckStats.text
  } yield Deck(doc.title, cl, Deck.parse(parseCards(content)), id)
}

object GgLinksParser extends LinksParser[String] with JsoupParser {

  val pageRe = """.*page=(\d+).*""".r

  private def keepPage(link: String): Boolean = link match {
    case pageRe(page) => page.toInt < 5
    case _            => true
  }

  def extract(id: String, content: String): Future[List[String]] =
    for {
      doc <- parseDocument(content)
    } yield (for {
      link <- doc.getElementsByAttributeValueMatching("href", "/hearthstone/decks[?/]").toList
      href = link.attr("href")
      if keepPage(href) && !href.contains("sort=")
    } yield if (href.startsWith("http://www.gosugamers.net")) href
    else "http://www.gosugamers.net" + href).distinct

}
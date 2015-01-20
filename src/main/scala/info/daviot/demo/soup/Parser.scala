package info.daviot.demo.soup

import org.jsoup.Jsoup
import scala.collection.JavaConversions._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import grizzled.slf4j.Logging
import org.jsoup.nodes.Document

object Parser extends App with Logging {
  val decks = Await.result(
    for {
      urls <- UrlExtract.extractFrom("http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-10/")
      decks <- Future.sequence(urls.map(DeckExtract.extractFrom))
    } yield decks, 1000.seconds)

  println(decks)

  trait DataExtract[T] {
    def extractFrom(url: String): Future[T]
  }

  def readUrl(url: String): Future[Document] = Future {
    debug(s"reading from $url")
    val doc = Jsoup.connect(url).get
    debug(s"read from $url")
    doc
  }

  object UrlExtract extends DataExtract[List[String]] {
    def extractFrom(url: String): Future[List[String]] =
      for {
        doc <- readUrl(url)
      } yield for {
        link <- doc.getElementsByAttributeValueStarting("href", "http://www.hearthstonetopdecks.com/decks/").toList take 3
      } yield link.attr("href")
  }

  object DeckExtract extends DataExtract[Deck] {
    def extractFrom(url: String): Future[Deck] = for {
      doc <- readUrl(url)
      content = doc.select("#neutral , #classes").select("li").map(_.text)
      cl = doc.select("span.deck-info").head.text.replaceAll("Class:", "").replaceAll("- Type.*", "").trim
    } yield Deck(doc.title, cl, Deck.parse(content))
  }

  case class Deck(title: String, hero: String, cards: List[Card])

  object Deck {
    def parse(content: Iterable[String]): List[Card] =
      content.toList.flatMap(Card.parse)
  }

  case class Card(count: Int, name: String)

  object Card {
    val re = """(\d)\s*x\s*(.*)""".r
    def parse(s: String) = s match {
      case re(count, name) => Some(Card(count.toInt, name))
      case _ => None
    }
  }
}
package info.daviot.demo.soup

import org.jsoup.Jsoup
import scala.collection.JavaConversions._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import grizzled.slf4j.Logging
import org.jsoup.nodes.Document
// check also : http://www.gosugamers.net/hearthstone/decks
object Parser extends App with Logging {
  val decks = Await.result(
    for {
      urls <- UrlExtract.extractFrom("http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-10/")
      decks <- Future.sequence(urls.map(DeckExtract.extractFrom))
    } yield decks, 1000.seconds)

  println(decks)

  val distances = decks.toArray.map { deck1 => decks.toArray.map { _.distance(deck1) } }

  println(distances.map(_.mkString(" | ")).mkString("\n"))

  val names = for (d <- decks.toArray) yield d.name
  ShowClusters.show(distances, names)

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
      } yield (for {
        link <- doc.getElementsByAttributeValueStarting("href", "http://www.hearthstonetopdecks.com/decks/").toList
      } yield link.attr("href")).distinct
  }

  object DeckExtract extends DataExtract[Deck] {
    def extractFrom(url: String): Future[Deck] = for {
      doc <- readUrl(url)
      content = doc.select("#neutral , #classes").select("li").map(_.text)
      cl = doc.select("span.deck-info").head.text.replaceAll("Class:", "").replaceAll("- Type.*", "").trim
    } yield Deck(doc.title, cl, Deck.parse(content), url)
  }

  case class Deck(name: String, hero: String, cards: List[Card], url: String) {
    //manahattan
    def distance(d: Deck): Double = {
      val keys = cardsMap.keySet ++ d.cardsMap.keySet
      val all = for (k <- keys.toList) yield math.abs(cardsMap(k) - d.cardsMap(k))
      all.sum / 2.0
    }

    lazy val cardsMap: Map[String, Int] =
      (for (c <- cards) yield c.name -> c.count).toMap.withDefaultValue(0)
  }

  object Deck {
    def parse(content: Iterable[String]): List[Card] =
      content.toList.flatMap(Card.parse)
  }

  case class Card(count: Int, name: String) {
    override def toString = s"${count}x $name"
  }

  object Card {
    val re = """(\d)\s*x\s*(.*)""".r
    def parse(s: String) = s match {
      case re(count, name) => Some(Card(count.toInt, name))
      case _ => None
    }
  }
}
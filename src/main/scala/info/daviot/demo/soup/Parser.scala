package info.daviot.demo.soup

import org.jsoup.Jsoup
import scala.collection.JavaConversions._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import grizzled.slf4j.Logging
import org.jsoup.nodes.Document
import dispatch._
import org.jsoup.nodes.Element
// check also : http://www.gosugamers.net/hearthstone/decks
//See http://stackoverflow.com/questions/14253515/use-dispatch-0-9-5-behind-proxy for proxy
object Parser extends App with Logging {
  //  val decks = Await.result(
  //    for {
  //      urls <- HstdUrlExtract.extractFrom("http://www.hearthstonetopdecks.com/deck-category/deck-class/druid/")
  //      decks <- Future.sequence(urls.map(HstdDeckExtract.extractFrom))
  //    } yield decks, 1000.seconds)

  val decks = Await.result(
    for {
      urls <- GgUrlExtract.extractFrom("http://www.gosugamers.net/hearthstone/decks?name=&textMode=0&filter=Filter&class=16")
      decks <- Future.sequence(urls.map(GgDeckExtract.extractFrom))
    } yield decks, 1000.seconds)

  println(decks)

  val distances = decks.toArray.map { deck1 => decks.toArray.map { _.distance(deck1) } }

  println(distances.map(_.mkString(" | ")).mkString("\n"))

  val names = for (d <- decks.toArray) yield d.name
  ShowClusters.show(distances, names)

  trait DataExtract[T] {
    def extractFrom(url: String): Future[T]
  }

  def readUrl(urlStr: String): Future[Document] = {
    debug(s"reading from $url")
    val svc = url(urlStr)
    for (data <- Http(svc OK as.String)) yield Jsoup.parse(data)
  }

  object GgUrlExtract extends DataExtract[List[String]] {
    def extractFrom(url: String): Future[List[String]] =
      for {
        doc <- readUrl(url)
      } yield (for {
        link <- doc.getElementsByAttributeValueStarting("href", "/hearthstone/decks/").toList
      } yield "http://www.gosugamers.net" + link.attr("href")).distinct
  }

  object HstdUrlExtract extends DataExtract[List[String]] {
    def extractFrom(url: String): Future[List[String]] =
      for {
        doc <- readUrl(url)
      } yield (for {
        link <- doc.getElementsByAttributeValueStarting("href", "http://www.hearthstonetopdecks.com/decks/").toList
      } yield link.attr("href")).distinct
  }

  object HstdDeckExtract extends DataExtract[Deck] {
    def extractFrom(url: String): Future[Deck] = for {
      doc <- readUrl(url)
      content = doc.select("#neutral , #classes").select("li").map(_.text)
      cl = doc.select("span.deck-info").head.text.replaceAll("Class:", "").replaceAll("- Type.*", "").trim
    } yield Deck(doc.title, cl, Deck.parse(content), url)
  }

  object GgDeckExtract extends DataExtract[Deck] {
    val classRe = """.*Class: (\w+).*""".r

    def parseCards(content: Element) = for {
      a <- content.select("a")
      name = a.select("span.name").head.text
      count = a.select("span.count").headOption.map(_.text.toInt).getOrElse(1)
    } yield s"${count}x $name"

    def extractFrom(url: String): Future[Deck] = for {
      doc <- readUrl(url)
      content = doc.select("div.deck").head
      classRe(cl) = doc.select("div.deck-stat-summary").head.text
    } yield Deck(doc.title, cl, Deck.parse(parseCards(content)), url)
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
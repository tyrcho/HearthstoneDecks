package info.daviot.demo.soup

import org.jsoup.Jsoup
import scala.collection.JavaConversions._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

object Parser extends App {
  Await.ready(
    for {
      urls <- UrlExtract.extractFrom("http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-10/")
      decks <- Future.sequence(urls.map(DeckExtract.extractFrom))
    } yield decks.foreach(println), 10.seconds)

  trait DataExtract[T] {
    def extractFrom(url: String): Future[T]
  }

  object UrlExtract extends DataExtract[List[String]] {
    def extractFrom(url: String): Future[List[String]] = Future {
      val doc = Jsoup.connect(url).get
      for {
        link <- doc.getElementsByAttributeValueStarting("href", "http://www.hearthstonetopdecks.com/decks/").toList take 3
      } yield link.attr("href")
    }
  }

  object DeckExtract extends DataExtract[Deck] {
    def extractFrom(url: String): Future[Deck] = Future {
      val doc = Jsoup.connect(url).get
      val content = doc.select("#neutral , #classes").select("li").map(_.text).mkString("\n")
      Deck(doc.title, content)
    }
  }

  case class Deck(title: String, content: String)
}
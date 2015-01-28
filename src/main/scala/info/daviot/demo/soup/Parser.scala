package info.daviot.demo.soup

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import dispatch.Http
import dispatch.as
import dispatch.implyRequestHandlerTuple
import dispatch.url
import grizzled.slf4j.Logging
import info.daviot.soup.hearthstone.GosuGamersScraper
import info.daviot.soup.hearthstone.HsTopDecksScraper
//See http://stackoverflow.com/questions/14253515/use-dispatch-0-9-5-behind-proxy for proxy
object Parser extends App with Logging {

  val ggDecks = Await.result(new GosuGamersScraper(Seq("http://www.gosugamers.net/hearthstone/decks?name=&textMode=0&filter=Filter&class=16")).collectedData, 1000.seconds).values

  val decks=Await.result(new HsTopDecksScraper(Seq("http://www.hearthstonetopdecks.com/deck-category/deck-class/druid/")).collectedData, 1000.seconds).values
  decks.foreach(println)

  val distances = decks.toArray.map { deck1 => decks.toArray.map { _.distance(deck1) } }

  println(distances.map(_.mkString(" | ")).mkString("\n"))

  val names = for (d <- decks.toArray) yield d.name
  ShowClusters.show(distances, names)

  def readUrl(urlStr: String): Future[Document] = {
    debug(s"reading from $url")
    val svc = url(urlStr)
    for (data <- Http(svc OK as.String)) yield Jsoup.parse(data)
  }

}
package info.daviot.demo.soup

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

import grizzled.slf4j.Logging
import info.daviot.soup.hearthstone.HsTopDecksScraper
//See http://stackoverflow.com/questions/14253515/use-dispatch-0-9-5-behind-proxy for proxy
object Parser extends App with Logging {

  val cacheFolder = "d:/temp"

  val hstdScraper = new HsTopDecksScraper(
    Seq(
      "http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-21/",
      "http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-22/"),
    cacheFolder,
    21, 22)
  val hstdDecks = Await.result(hstdScraper.collectedData, 1000.seconds).values

  val decks = (hstdDecks).groupBy(_.name).map(_._2.head) // remove duplicate names
  decks.groupBy(_.hero).foreach(println)

}
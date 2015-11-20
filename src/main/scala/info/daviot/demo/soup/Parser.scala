package info.daviot.demo.soup

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import grizzled.slf4j.Logging
import info.daviot.demo.deckClustering.ClusterDecks
import info.daviot.soup.hearthstone.{ GosuGamersScraper, HsTopDecksScraper }
import net.hearthstats.core.HeroClass
import java.nio.file.Files
//See http://stackoverflow.com/questions/14253515/use-dispatch-0-9-5-behind-proxy for proxy
object Parser extends App with Logging {

  val cacheFolder = "c:/temp"
  //  val ggScraper = new GosuGamersScraper(Seq("http://www.gosugamers.net/hearthstone/decks?name=&textMode=0&filter=Filter&class=13"), cacheFolder)
  //  val ggDecks = Await.result(ggScraper.collectedData, 1000.seconds).values

  val hstdScraper = new HsTopDecksScraper(
    Seq(
      "http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-18/",
      "http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-19/",
      "http://www.hearthstonetopdecks.com/deck-category/constructed-seasons/season-20/"),
    cacheFolder,
    18, 19, 20)
  val hstdDecks = Await.result(hstdScraper.collectedData, 1000.seconds).values

  val decks = (hstdDecks).groupBy(_.name).map(_._2.head) // remove duplicate names
  decks.foreach(println)

  val reportFolder = Files.createTempDirectory("report")
  for {
    cl <- HeroClass.values.toSet - HeroClass.UNDETECTED
    decks = hstdDecks.filter(_.hero == cl.toString)
  } ClusterDecks(decks).cluster(cl, reportFolder)

}
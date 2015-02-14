package info.daviot.demo.soup

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import grizzled.slf4j.Logging
import info.daviot.demo.deckClustering.ClusterDecks
import info.daviot.soup.hearthstone.{GosuGamersScraper, HsTopDecksScraper}
import net.hearthstats.core.HeroClass
//See http://stackoverflow.com/questions/14253515/use-dispatch-0-9-5-behind-proxy for proxy
object Parser extends App with Logging {

  val cacheFolder = "c:/temp"
  val ggScraper = new GosuGamersScraper(Seq("http://www.gosugamers.net/hearthstone/decks?name=&textMode=0&filter=Filter&class=13"), cacheFolder)
  val ggDecks = Await.result(ggScraper.collectedData, 1000.seconds).values

  val hstdScraper = new HsTopDecksScraper(Seq("http://www.hearthstonetopdecks.com/deck-category/deck-class/druid/"), cacheFolder, "10")
  val hstdDecks = Await.result(hstdScraper.collectedData, 1000.seconds).values

  val decks = (hstdDecks ++ ggDecks).groupBy(_.name).map(_._2.head) // remove duplicate names
  decks.foreach(println)

  ClusterDecks.cluster(decks, HeroClass.DRUID)

}
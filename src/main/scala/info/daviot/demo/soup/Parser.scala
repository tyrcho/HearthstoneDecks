package info.daviot.demo.soup

import scala.concurrent.Await
import scala.collection.JavaConversions._
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
import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm
import com.apporiented.algorithm.clustering.CompleteLinkageStrategy
import com.apporiented.algorithm.clustering.Cluster
import info.daviot.cards.Deck
//See http://stackoverflow.com/questions/14253515/use-dispatch-0-9-5-behind-proxy for proxy
object Parser extends App with Logging {

  val cacheFolder = "c:/temp"
  val ggScraper = new GosuGamersScraper(Seq("http://www.gosugamers.net/hearthstone/decks?name=&textMode=0&filter=Filter&class=13"), cacheFolder)
  val ggDecks = Await.result(ggScraper.collectedData, 1000.seconds).values

  val hstdScraper = new HsTopDecksScraper(Seq("http://www.hearthstonetopdecks.com/deck-category/deck-class/druid/"), cacheFolder, "10")
  val hstdDecks = Await.result(hstdScraper.collectedData, 1000.seconds).values

  val decks = (hstdDecks ++ ggDecks).groupBy(_.name).map(_._2.head) // remove duplicate names
  decks.foreach(println)

  val distances = decks.toArray.map { deck1 => decks.toArray.map { _.distance(deck1) } }

  println(distances.map(_.mkString(" | ")).mkString("\n"))

  val names = for (d <- decks.toArray) yield d.name

  val alg = new DefaultClusteringAlgorithm
  val cluster = alg.performClustering(distances, names, new CompleteLinkageStrategy)
  for (cl <- cluster.topClusters(15)) {
    println(cl.getName)
    for ((card, count) <- cl.averageDeck(decks)) {
      println(f"$count%.1f  $card")
    }
  }
  ShowClusters.show(distances, names, 15)

  implicit class ClusterOp(cluster: Cluster) {
    def topClusters(maxDistance: Double): List[Cluster] = {
      val children = cluster.getChildren.toList
      if (cluster.getTotalDistance < maxDistance) List(cluster)
      else if (children.forall { _.getTotalDistance > maxDistance }) children.flatMap(_.topClusters(maxDistance))
      else children
    }

    def averageDeck(allDecks: Iterable[Deck]) = {
      def all(c: Cluster): List[Deck] =
        c.getChildren.toList.flatMap { ch =>
          if (ch.isLeaf) allDecks.filter(_.name == ch.getName)
          else all(ch)
        }

      val decks = all(cluster)
      val allCards = decks.flatMap(_.cards.map(_.name)).distinct
      val size = decks.size
      (for {
        card <- allCards
        sum = decks.map(_.cardsMap(card)).sum
        avg = sum / size.toFloat
        if avg > 0.5
      } yield {
        card -> avg
      }).sortBy(- _._2)
    }
  }

}
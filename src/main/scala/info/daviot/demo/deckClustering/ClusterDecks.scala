package info.daviot.demo.deckClustering

import info.daviot.cards.Deck
import info.daviot.demo.soup.ShowClusters
import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm
import com.apporiented.algorithm.clustering.CompleteLinkageStrategy
import scala.collection.JavaConversions._
import com.apporiented.algorithm.clustering.Cluster
import java.io.File
import java.nio.file.Files
import net.hearthstats.core.HeroClass
import java.io.BufferedWriter
import java.io.PrintWriter
import com.apporiented.algorithm.clustering.WeightedLinkageStrategy
import info.daviot.demo.cards.DeckTemplate
import info.daviot.cards.Card
import java.awt.Desktop

object ClusterDecks {
  val reportFolder = Files.createTempDirectory("report")

  def report(klass: HeroClass) = {
    val p = Files.createFile(reportFolder.resolve(s"$klass.html"))
    val bw = Files.newBufferedWriter(p)
    println(s"Writing report in $p")
    (p, new PrintWriter(bw))
  }

  def clusterToDeck(implicit allDecks: Iterable[Deck], c: Cluster, klass: HeroClass): Deck = {
    val cards = for ((card, count) <- c.averageDeck) yield Card((count * 10).toInt, card)
    Deck(c.name, klass.toString, cards.toList, "", c.weight)
  }

  def cluster(implicit allDecks: Iterable[Deck], klass: HeroClass) {
    val maxDist = 10
    val (file, writer) = report(klass)
    val distances = allDecks.toArray.map { deck1 => allDecks.toArray.map { _.distance(deck1) } }
    val weights = allDecks.toArray.map(_.weight.toDouble)

    val ids = for (d <- allDecks.toArray) yield d.url

    val alg = new DefaultClusteringAlgorithm
    val cluster = alg.performWeightedClustering(distances, ids, weights, new WeightedLinkageStrategy)
    val decks = for (cl <- cluster.topClusters(maxDist).sortBy(-_.weight))
      yield clusterToDeck(allDecks, cl, klass)

    writer.println(DeckTemplate(decks).html)
    writer.close()
    Desktop.getDesktop.browse(file.toUri)
    cluster.debug(30)
  }

  implicit class ClusterOp(cluster: Cluster)(implicit allDecks: Iterable[Deck]) {
    def topClusters(maxDistance: Double): List[Cluster] = {
      val children = cluster.getChildren.toList
      if (cluster.distance < maxDistance) List(cluster)
      else children.flatMap(_.topClusters(maxDistance))
    }

    def distance = cluster.getDistance.getDistance

    lazy val weight: Int = decks.map(_.weight).sum

    lazy val decks: Iterable[Deck] =
      if (cluster.isLeaf) allDecks.filter(_.url == cluster.getName)
      else cluster.getChildren.flatMap(_.decks)

    def name: String = {
      if (cluster.isLeaf) allDecks.filter(_.url == cluster.getName).head.name
      else cluster.getChildren.head.name
    }

    def averageDeck = {
      val decks = cluster.decks
      val allCards = decks.toList.flatMap(_.cards.map(_.name)).distinct
      val size = decks.size
      (for {
        card <- allCards
        sum = decks.map(_.cardsMap(card)).sum
        avg = sum / size.toFloat
        if avg > 0.35
      } yield {
        card -> avg
      }).sortBy(-_._2)
    }

    def debug(maxDist: Double, indent: Int = 0) {
      import cluster._
      if (distance < maxDist) {
        print("  " * indent)
        val leaf = if (isLeaf) "(leaf)" else distance
        println(s"$getName $leaf")
      }
      for (c <- getChildren) c.debug(maxDist, indent + 1)
    }
  }
}
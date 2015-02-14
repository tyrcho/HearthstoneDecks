package info.daviot.demo.deckClustering

import info.daviot.cards.Deck
import info.daviot.demo.soup.ShowClusters
import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm
import com.apporiented.algorithm.clustering.CompleteLinkageStrategy
import scala.collection.JavaConversions._
import com.apporiented.algorithm.clustering.Cluster
import com.apporiented.algorithm.clustering.Cluster
import java.io.File
import java.nio.file.Files
import net.hearthstats.core.HeroClass
import java.io.BufferedWriter
import java.io.PrintWriter

object ClusterDecks {
  val reportFolder = Files.createTempDirectory("report")

  def report(klass: HeroClass): PrintWriter = {
    val p = Files.createFile(reportFolder.resolve(s"$klass.txt"))
    val bw = Files.newBufferedWriter(p)
    println(s"Writing report in $p")
    new PrintWriter(bw)
  }

  def cluster(decks: Iterable[Deck], klass: HeroClass) {
    val writer = report(klass)
    val distances = decks.toArray.map { deck1 => decks.toArray.map { _.distance(deck1) } }

    val ids = for (d <- decks.toArray) yield d.url

    val alg = new DefaultClusteringAlgorithm
    val cluster = alg.performClustering(distances, ids, new CompleteLinkageStrategy)
    for (cl <- cluster.topClusters(15)) {
      writer.println(cl.getName)
      for ((card, count) <- cl.averageDeck(decks)) {
        writer.println(f"$count%.1f  $card")
      }
      writer.println()
    }
    writer.close()
    ShowClusters.show(distances, ids, 15)
  }

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
          if (ch.isLeaf) allDecks.filter(_.url == ch.getName)
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
      }).sortBy(-_._2)
    }
  }
}
package info.daviot.demo.deckClustering

import info.daviot.cards.Deck
import info.daviot.demo.soup.ShowClusters
import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm
import com.apporiented.algorithm.clustering.CompleteLinkageStrategy
import scala.collection.JavaConversions._
import com.apporiented.algorithm.clustering.Cluster
import com.apporiented.algorithm.clustering.Cluster

object ClusterDecks {
  def cluster(decks: Iterable[Deck]) {
    val distances = decks.toArray.map { deck1 => decks.toArray.map { _.distance(deck1) } }

    println(distances.map(_.mkString(" | ")).mkString("\n"))

    val ids = for (d <- decks.toArray) yield d.url

    val alg = new DefaultClusteringAlgorithm
    val cluster = alg.performClustering(distances, ids, new CompleteLinkageStrategy)
    for (cl <- cluster.topClusters(15)) {
      println(cl.getName)
      for ((card, count) <- cl.averageDeck(decks)) {
        println(f"$count%.1f  $card")
      }
    }
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
      }).sortBy(-_._2)
    }
  }
}
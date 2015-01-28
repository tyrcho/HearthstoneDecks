package info.daviot.demo.soup

import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm
import com.apporiented.algorithm.clustering.AverageLinkageStrategy
import scala.collection.JavaConversions._
import com.apporiented.algorithm.clustering.visualization.DendrogramPanel
import javax.swing.JFrame
import scala.util.Random
import javax.tools.Diagnostic
import com.apporiented.algorithm.clustering.Cluster
import com.apporiented.algorithm.clustering.CompleteLinkageStrategy

//see https://github.com/lbehnke/hierarchical-clustering-java
object ClusteringDemo extends App {
  val size = 200
  val names = (1 to size).map(_.toString).toArray

  val distances = Array.fill(size)(Array.fill(size)(Random.nextDouble))

  ShowClusters.show(distances, names, 0)
}

object ShowClusters {
  def show(distances: Array[Array[Double]], names: Array[String], maxDist: Double): Unit = {
    val alg = new DefaultClusteringAlgorithm

    val start = System.nanoTime / 1000 / 1000
    val cluster = alg.performClustering(distances, names, new CompleteLinkageStrategy)
    cluster.debug(0, maxDist)
    val end = System.nanoTime / 1000 / 1000
    val duration = end - start
    println(s"${names.size} in $duration ms")

    val dp = new DendrogramPanel
    dp.setModel(cluster)

    val f = new JFrame
    f.setContentPane(dp)
    f.pack()
    f.setVisible(true)
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  }

  implicit class ClusterOp(cluster: Cluster) {
    def debug(indent: Int = 0, maxDist: Double) {
      import cluster._
      if (getTotalDistance < maxDist) {
        print("  " * indent)
        val leaf = if (isLeaf) " (leaf)" else ""
        println(s"$getName $leaf $getTotalDistance")
      }
      for (c <- getChildren) c.debug(indent + 1, maxDist)
    }
  }
}
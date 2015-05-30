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
import com.apporiented.algorithm.clustering.WeightedLinkageStrategy

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
    val cluster = alg.performClustering(distances, names, new WeightedLinkageStrategy)
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

}
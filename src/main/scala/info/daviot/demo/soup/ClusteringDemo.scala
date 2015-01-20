package info.daviot.demo.soup

import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm
import com.apporiented.algorithm.clustering.AverageLinkageStrategy
import scala.collection.JavaConversions._
import com.apporiented.algorithm.clustering.visualization.DendrogramPanel
import javax.swing.JFrame
import scala.util.Random

//see https://github.com/lbehnke/hierarchical-clustering-java
object ClusteringDemo extends App {
  val size = 2000
  val names = (1 to size).map(_.toString).toArray

  val distances = Array.fill(size)(Array.fill(size)(Random.nextDouble))

  val alg = new DefaultClusteringAlgorithm

  val start = System.nanoTime / 1000 / 1000
  val cluster = alg.performClustering(distances, names, new AverageLinkageStrategy)
  val end = System.nanoTime / 1000 / 1000
  val duration = end - start
  println(s"$size in $duration ms")

  val dp = new DendrogramPanel
  dp.setModel(cluster)

  val f = new JFrame
  f.setContentPane(dp)
  f.setVisible(true)
  f.pack()
}
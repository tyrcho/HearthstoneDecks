package info.daviot.demo.soup

import com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm
import com.apporiented.algorithm.clustering.AverageLinkageStrategy
import scala.collection.JavaConversions._
import com.apporiented.algorithm.clustering.visualization.DendrogramPanel
import javax.swing.JFrame

//see https://github.com/lbehnke/hierarchical-clustering-java
object ClusteringDemo extends App {
  val names = Array("O1", "O2", "O3", "O4", "O5", "O6")

  val distances = Array[Array[Double]](
    Array(0, 1, 9, 7, 11, 14),
    Array(1, 0, 4, 3, 8, 10),
    Array(9, 4, 0, 9, 2, 8),
    Array(7, 3, 9, 0, 6, 13),
    Array(11, 8, 2, 6, 0, 10),
    Array(14, 10, 8, 13, 10, 0))

  val alg = new DefaultClusteringAlgorithm

  val cluster = alg.performClustering(distances, names, new AverageLinkageStrategy)

  val dp = new DendrogramPanel
  dp.setModel(cluster)

  val f = new JFrame
  f.setContentPane(dp)
  f.setVisible(true)
  f.pack()
}
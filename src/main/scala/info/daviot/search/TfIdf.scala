package info.daviot.search

case class TfIdf(documents: List[List[String]]) {
  var idfCache = collection.mutable.Map.empty[String, Double]

  // inverse frequency : words which are less frequent in the corpus have higher value
  def idf(word: String) =
    idfCache.getOrElseUpdate(word, math.log(1 / frequency(word)))

  def frequency(word: String): Double =
    (documents.count(_.contains(word)) + 1) / documents.size.toDouble
}
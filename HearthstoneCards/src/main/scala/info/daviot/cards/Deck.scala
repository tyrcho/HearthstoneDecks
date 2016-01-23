package info.daviot.cards

case class Deck(
  name: String,
  hero: String,
  cards: List[Card],
  url: String,
  weight: Int = 1) {
  //manhattan
  def distance(d: Deck): Double = {
    val keys = cardsMap.keySet ++ d.cardsMap.keySet
    val all = for (k <- keys.toList) yield math.abs(cardsMap(k) - d.cardsMap(k))
    all.sum / 2.0
  }

  lazy val cardsMap: Map[String, Int] =
    (for (c <- cards) yield c.name -> c.count).toMap.withDefaultValue(0)
}

object Deck {
  def parse(content: Iterable[String]): List[Card] =
    content.toList.flatMap(Card.parse)
}
package info.daviot.cards

case class Card(count: Int, name: String) {
  override def toString = s"${count}x $name"
}

object Card {
  val re = """(\d)\s*x\s*(.*)""".r
  def parse(s: String) = s match {
    case re(count, name) => Some(Card(count.toInt, name))
    case _               => None
  }
}
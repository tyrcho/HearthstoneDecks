package info.daviot.cards

import java.util.NoSuchElementException
import rapture.json.jsonBackends.jawn._
import rapture.json._

case class CardData(
  id: String,
  name: String,
  `type`: String,
  rarity: Option[String],
  cost: Option[Int],
  attack: Option[Int],
  health: Option[Int],
  text: Option[String],
  collectible: Option[Boolean],
  playerClass: Option[String],
  mechanics: Option[List[String]])

object CardData {
  val is = getClass.getResourceAsStream("cards.json")
  lazy val json = Json.parse(io.Source.fromInputStream(is, "UTF-8").getLines.mkString).as[List[CardData]]

  lazy val allCards: List[CardData] =
    json

  lazy val collectible: List[CardData] =
    allCards.filter(_.collectible == Some(true))

  lazy val heroPowers: List[CardData] =
    allCards.filter(_.`type` == "Hero Power")

  def byId(id: String): CardData =
    try { allCards.filter(_.id == id).head }
    catch { case e: NoSuchElementException => throw new IllegalArgumentException(id) }

}

object CardDataDemo extends App{
  CardData.collectible.take(10).foreach(println)
}
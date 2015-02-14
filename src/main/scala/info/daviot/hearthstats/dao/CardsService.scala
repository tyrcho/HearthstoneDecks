package info.daviot.hearthstats.dao

import scala.slick.driver.MySQLDriver.simple._
import net.hearthstats.core.HeroClass
import info.daviot.hearthstats.model.Tables._
import net.hearthstats.core.Card

class CardsService(implicit session: Session) {
  def collectibleCards: Map[Int, Card] = {
    val q = for {
      c <- Cards
      if c.collectible
    } yield c

    (for (c <- q.list)
      yield c.id -> Card(
      id = c.id,
      originalName = c.name.getOrElse("unkown card"))).toMap
  }

}
package info.daviot.hearthstats.dao

import scala.slick.driver.MySQLDriver.simple._
import info.daviot.hearthstats.model.Tables._

class CardsService(implicit session: Session) {
  def collectibleCards: Map[Int, HsCard] = {
    val q = for {
      c <- Cards
      if c.collectible
    } yield c

    (for (c <- q.list)
      yield c.id -> HsCard(
      id = c.id,
      originalName = c.name.getOrElse("unkown card"))).toMap
  }

}
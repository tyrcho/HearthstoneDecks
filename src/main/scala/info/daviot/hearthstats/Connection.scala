package info.daviot.hearthstats

import scala.slick.driver.MySQLDriver.simple.Database
import com.mysql.jdbc.Driver
import info.daviot.demo.deckClustering.ClusterDecks
import info.daviot.hearthstats.dao.DecksService
import net.hearthstats.core.HeroClass

object Connection extends App {
  val dbUrl = "jdbc:mysql://54.191.40.169:3306/hearthstats_production"
  val user = "michel"
  val maxRows = 500

  val klass=HeroClass.WARLOCK
  
  val db = Database.forURL(
    url = dbUrl,
    driver = classOf[Driver].getName,
    user = user,
    password = args(0))

  db.withSession {
    implicit session =>
      val decksService=new DecksService
      val decks = decksService.decksForClass(
        klassId = klass.ordinal,
        minMatches = 20,
        maxRows = 300)
      ClusterDecks.cluster(decks, klass)
  }
}
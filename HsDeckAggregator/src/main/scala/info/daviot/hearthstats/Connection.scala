package info.daviot.hearthstats

import scala.slick.driver.MySQLDriver.simple.Database
import com.mysql.jdbc.Driver
import info.daviot.demo.deckClustering.ClusterDecks
import info.daviot.hearthstats.dao.DecksService
import info.daviot.hearthstats.dao.MatchFilter
import java.nio.file.Files
import info.daviot.demo.deckClustering.HeroClass

object Connection extends App {
  val prodDB = "hearthprod.cf7zalaj5nzl.us-west-2.rds.amazonaws.com"
  val copyDB = "54.191.40.169"

  val dbUrl = s"jdbc:mysql://$prodDB:3306/hearthstats_production"
  val user = "michel"
  val maxRows = 500

  val db = Database.forURL(
    url = dbUrl,
    driver = classOf[Driver].getName,
    user = user,
    password = args(0))

  db.withSession {
    implicit session =>
      val decksService = new DecksService
      val reportFolder = Files.createTempDirectory("report")

      for {
        cl <- HeroClass.values.diff(List(HeroClass.UNDETECTED))
        decks = decksService.decks(MatchFilter(
          cl.ordinal,
          rankRange = 6 to 15,
          includeLegend = false))
        if decks.size > 0
      } ClusterDecks(decks).cluster(cl, reportFolder)
  }
}
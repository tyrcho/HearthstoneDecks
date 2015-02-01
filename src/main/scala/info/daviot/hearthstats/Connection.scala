package info.daviot.hearthstats

import scala.slick.driver.MySQLDriver.simple._
import com.mysql.jdbc.Driver
import info.daviot.hearthstats.model.Tables._

object Connection extends App {
  Database.forURL("jdbc:mysql://hearthprod.cf7zalaj5nzl.us-west-2.rds.amazonaws.com:3306/hearthstats_production",
    driver = classOf[Driver].getName,
    user = "michel",
    password = args(0)) withSession {
      implicit session =>

        val q = for {
          d <- Decks
          if d.cardstring.nonEmpty
          if d.userNumMatches > 10
        } yield d

        q.sortBy(_.userNumMatches.desc).take(20).foreach(println)
    }
}
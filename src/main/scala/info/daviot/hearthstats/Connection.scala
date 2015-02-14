package info.daviot.hearthstats

import scala.slick.driver.MySQLDriver.simple._
import com.mysql.jdbc.Driver
import info.daviot.hearthstats.model.Tables._
import info.daviot.cards.Card
import info.daviot.cards.Deck
import net.hearthstats.core.HeroClass
import info.daviot.demo.deckClustering.ClusterDecks

object Connection extends App {
  val dbUrl = "jdbc:mysql://54.191.40.169:3306/hearthstats_production"
  val user = "michel"
  val maxRows = 100

  Database.forURL(dbUrl,
    driver = classOf[Driver].getName,
    user = user,
    password = args(0)) withSession {
      implicit session =>

        val q = for {
          d <- Decks
          if d.cardstring.nonEmpty && (d.cardstring =!= "")
          if d.slug.nonEmpty
          if d.klassId === 1
          if d.userNumMatches > 10
        } yield d

        val decks = for {
          d <- q.sortBy(_.userNumMatches.desc).take(maxRows).list
        } yield Deck(
          d.name.getOrElse("undefined"),
          HeroClass.stringWithId(d.klassId.get),
          cards(d),
          d.slug.get)

        decks.foreach(println)

        ClusterDecks.cluster(decks)

    }

  lazy val hsCardRe = """(\d+)_(\d)""".r
  def cards(d: DecksRow): List[Card] = d.cardstring.map { cs =>
    for {
      cardString <- cs.split(",").toList
      parsed <- cardString match {
        case hsCardRe(name, count) => Some(Card(count.toInt, name))
        case _                     => None
      }
    } yield parsed
  }.getOrElse(Nil)

}
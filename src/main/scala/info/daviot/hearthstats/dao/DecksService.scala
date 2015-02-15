package info.daviot.hearthstats.dao

import scala.slick.driver.MySQLDriver.simple._
import info.daviot.cards._
import net.hearthstats.core.HeroClass
import info.daviot.hearthstats.model.Tables._
import com.github.nscala_time.time.Imports._
import java.sql.Timestamp

class DecksService(implicit session: Session) {
  val cardsService = new CardsService
  lazy val collectibleCards = cardsService.collectibleCards

  def matchesInCurrentSeason(d: Decks, season: Int, rank: Int): Column[Int] = {
    val q = for {
      md <- MatchDecks
      if md.deckId === d.id
      m <- Matches
      if m.id === md.matchId
      mr <- MatchRanks
      if mr.matchId === md.matchId
      if mr.rankId <= rank
    } yield md.id
    q.size
  }

  //for current season
  def decksForClass(klassId: Int, minMatches: Int = 10, maxRows: Int = 1000): List[Deck] = {
    val q = for {
      d <- Decks
      if d.cardstring.nonEmpty && (d.cardstring =!= "")
      if d.slug.nonEmpty
      if d.klassId === klassId
      m = matchesInCurrentSeason(d, season = 15, rank = 2)
      if m >= minMatches
    } yield (d, m)

    for {
      (d, m) <- q.sortBy(_._2.desc).take(maxRows).list
    } yield Deck(
      d.name.getOrElse("undefined"),
      HeroClass.stringWithId(d.klassId.get),
      cards(d),
      d.slug.get,
      m)
  }

  lazy val hsCardRe = """(\d+)_(\d)""".r
  def cardFromString(s: String) =
    s match {
      case hsCardRe(name, count) =>
        collectibleCards.get(name.toInt).map(c =>
          Card(count.toInt, c.originalName))
      case _ => None
    }

  private def cards(d: DecksRow): List[Card] = d.cardstring.map { cs =>
    for {
      cardString <- cs.split(",").toList
      card <- cardFromString(cardString)
    } yield card
  }.getOrElse(Nil)

}
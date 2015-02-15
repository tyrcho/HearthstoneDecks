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

  def matchesInCurrentSeason(d: Decks, mf: MatchFilter): Column[Int] = {
    val q = for {
      md <- MatchDecks
      if md.deckId === d.id
      m <- Matches
      if m.id === md.matchId
      if m.seasonId === mf.season
      mr <- MatchRanks
      if mr.matchId === md.matchId
      if (mr.rankId === 26 && mf.includeLegend) || (mr.rankId.between(mf.rankRange.start, mf.rankRange.end))
    } yield md.id
    q.size
  }

  def decks(mf: MatchFilter): List[Deck] = {
    val q = for {
      d <- Decks
      if d.cardstring.nonEmpty && d.cardstring =!= ""
      if d.slug.nonEmpty
      if d.klassId === mf.klassId
      m = matchesInCurrentSeason(d, mf)
      if m > 0
    } yield (d, m)

    for {
      (d, m) <- q.sortBy(_._2.desc).take(mf.maxRows).list
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

case class MatchFilter(
  klassId: Int,
  season: Int = 15,
  rankRange: Range = 1 to 5,
  includeLegend: Boolean = true,
  maxRows: Int = 750)
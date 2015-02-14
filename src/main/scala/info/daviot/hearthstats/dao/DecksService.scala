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

  def deckPlayedInCurrentSeason(d: Decks): Column[Boolean] = {
    val beginMonth = DateTime.now.withDayOfMonth(1)
    val bm = new Timestamp(beginMonth.millis.get)
    val q = for {
      m <- MatchDecks
      if m.deckId === d.id
      if m.createdAt > bm
    } yield m.id
    q.exists
  }

  //for current season
  def decksForClass(klassId: Int, minMatches: Int = 10, maxRows: Int = 1000): List[Deck] = {
    val q = for {
      d <- Decks
      if d.cardstring.nonEmpty && (d.cardstring =!= "")
      if d.slug.nonEmpty
      if d.klassId === klassId
      if d.userNumMatches > minMatches
      if deckPlayedInCurrentSeason(d)
    } yield d

    for {
      d <- q.sortBy(_.userNumMatches.desc).take(maxRows).list
    } yield Deck(
      d.name.getOrElse("undefined"),
      HeroClass.stringWithId(d.klassId.get),
      cards(d),
      d.slug.get)
  }

  lazy val hsCardRe = """(\d+)_(\d)""".r
  private def cards(d: DecksRow): List[Card] = d.cardstring.map { cs =>
    for {
      cardString <- cs.split(",").toList
      parsed <- cardString match {
        case hsCardRe(name, count) => Some(Card(count.toInt, collectibleCards(name.toInt).originalName))
        case _                     => None
      }
    } yield parsed
  }.getOrElse(Nil)

}
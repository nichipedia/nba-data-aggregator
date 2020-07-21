package tech.housemoran.realgood.models.api

import java.util.Date

/** ************************************************
 * * Created by Nicholas on 7/9/2020.               **
 * *************************************************/
class Game(val homeTeam: String, val awayTeam: String, val homeScore: Int, val awayScore: Int, date: Date) {
  val winner = () => {
    if (homeScore>awayScore) {
      homeTeam
    } else  {
      awayTeam
    }
  }

  override def toString: String = s"$homeScore : $awayScore ---- $homeTeam vs. $awayTeam"
}

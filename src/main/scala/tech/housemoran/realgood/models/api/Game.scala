package tech.housemoran.realgood.models.api

import java.util.Date

/** ************************************************
 * * Created by Nicholas on 7/9/2020.               **
 * *************************************************/
class Game(val homeTeam: Team, val awayTeam: Team, val homeScore: Int, val awayScore: Int, date: Date) {
  val winner = () => {
    if (homeScore>awayScore) {
      homeTeam
    } else  {
      awayTeam
    }
  }
}

package tech.housemoran.realgood.models.nba

import tech.housemoran.realgood.models.api.{Athlete, League, Team}

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
class NBATeam(name: String, activePlayers: List[Athlete]) extends Team(name, League("NBA"), activePlayers) {
  override def toString: String = {
    s"${league.name}: $name"
  }
}

package tech.housemoran.realgood.models.nba

import tech.housemoran.realgood.models.api.{League, Team}

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
class NBATeam(name: String) extends Team(name, League("NBA")) {
  override def toString: String = {
    s"${league.name}: $name"
  }
}

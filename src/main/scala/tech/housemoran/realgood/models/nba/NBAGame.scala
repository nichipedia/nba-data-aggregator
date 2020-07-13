package tech.housemoran.realgood.models.nba

import java.util.Date

import tech.housemoran.realgood.models.api.Game

class NBAGame(homeTeam: NBATeam, awayTeam: NBATeam, homeScore: Int, awayScore: Int, date: Date) extends Game(homeTeam, awayTeam, homeScore, awayScore, date) {

}

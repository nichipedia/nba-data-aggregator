package tech.housemoran.realgood.models.persistence


import java.util.Date

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, JoinColumn, ManyToOne, OneToOne, Table}

import scala.beans.BeanProperty

/** ************************************************
 * * Created by Nicholas on 7/21/2020.               **
 * *************************************************/
@Entity("GAME_ROW")
@Table("GAMES")
class GameRow {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  var id: String = _

  @Column(name = "HOME_TEAM", unique = false, nullable = false)
  var homeTeam: String = _

  @Column(name = "AWAY_TEAM", unique = false, nullable = false)
  var awayTeam: String = _

  @Column(name = "HOME_SCORE", unique = false, nullable = false)
  var homeScore: Int = _

  @Column(name = "AWAY_SCORE", unique = false, nullable = false)
  var awayScore: Int = _

  @Column(name = "SEASON", unique = false, nullable = false)
  var season: String = _

  @Column(name = "DATE", unique = false, nullable = false)
  var date: Date = _

  @Column(name = "LEAGUE", unique = false, nullable = false)
  var league: String = _

  @OneToOne
  @JoinColumn(name = "LINE_ID")
  var line: LineRow = _

  @ManyToOne
  @JoinColumn(name = "HOME_TEAM_ID")
  var homeTeamEntity: TeamRow = _

  @ManyToOne
  @JoinColumn(name = "AWAY_TEAM_ID")
  var awayTeamEntity: TeamRow = _
}
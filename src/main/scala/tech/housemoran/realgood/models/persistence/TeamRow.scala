package tech.housemoran.realgood.models.persistence

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, JoinColumn, OneToMany, Table}

import scala.beans.BeanProperty

/** ************************************************
 * * Created by Nicholas on 7/21/2020.               **
 * *************************************************/
@Entity(name="TEAM_ROW")
@Table(name = "TEAMS")
class TeamRow {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  var id: Int = _

  @Column(name = "TEAM_NAME", unique = false, nullable = false, length = 50)
  var name: String = _

  @Column(name = "LEAGUE", unique = false, nullable = false, length = 20)
  var league: String = _

  @OneToMany(mappedBy = "homeTeamEntity")
  var homeGames: java.util.Set[GameRow] = _

  @OneToMany(mappedBy = "awayTeamEntity")
  var awayGames: java.util.Set[GameRow] = _
}
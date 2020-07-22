package tech.housemoran.realgood.models.persistence

import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, OneToOne, Table}

/** ************************************************
 * * Created by Nicholas on 7/21/2020.               **
 * *************************************************/
@Entity("LINE_ROW")
@Table("LINES")
class LineRow {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", unique = true, nullable = false)
  var id: Int = _

  @Column(name = "HOME_SPREAD", unique = false, nullable = false)
  var homeSpread: Float = _

  @Column(name = "AWAY_SPREAD", unique = false, nullable = false)
  var awaySpread: Float = _

  @Column(name = "TOTAL_POINTS", unique = false, nullable = false)
  var total: Int = _

  @Column(name = "OVER_PRICE", unique = false, nullable = false)
  var overPrice: Int = _

  @Column(name = "UNDER_PRICE", unique = false, nullable = false)
  var underPrice: Int = _

  @Column(name = "DOG_PRICE", unique = false, nullable = false)
  var dogPrice: Int = _

  @Column(name = "FAVORITE_PRICE", unique = false, nullable = false)
  var favoritePrice: Int = _

  @OneToOne(mappedBy = "line")
  var game: GameRow = _
}

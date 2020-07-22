package tech.housemoran.realgood.models.api

/** ************************************************
 * * Created by Nicholas on 7/20/2020.               **
 * *************************************************/
class Spread(val homeTeam:Float, val awayTeam:Float, val homePrice: Int, val awayPrice: Int) {
  def payout(money: Float, pick: Pick.type): Unit = {
    pick match {
      case Pick.underDog => money * (homePrice / 100)
      case Pick.favorite => money / (awayPrice / 100)
      case _ => 0
    }
  }
}

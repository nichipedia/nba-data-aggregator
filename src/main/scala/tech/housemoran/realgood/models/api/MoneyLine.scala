package tech.housemoran.realgood.models.api

/** ************************************************
 * * Created by Nicholas on 7/20/2020.               **
 * *************************************************/
class MoneyLine(val homeTeam: Int, val awayTeam: Int) {

  // I need to play with this some more....
  def payout(money: Float, pick: Pick.type): Unit = {
    pick match {
      case Pick.underDog => money * (homeTeam / 100)
      case Pick.favorite => money / (awayTeam / 100)
      case _ => 0
    }
  }
}

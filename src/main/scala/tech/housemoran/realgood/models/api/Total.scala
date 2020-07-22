package tech.housemoran.realgood.models.api

/** ************************************************
 * * Created by Nicholas on 7/20/2020.               **
 * *************************************************/
class Total(val total: Int, val overPrice: Int, val underPrice: Int) {
  def payout(money: Float, pick: Pick.type): Unit = {
    pick match {
      case Pick.underDog => money * (overPrice / 100)
      case Pick.favorite => money / (underPrice / 100)
      case _ => 0
    }
  }
}

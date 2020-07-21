package tech.housemoran.realgood.models.api

import java.util.Date

/** ************************************************
 * * Created by Nicholas on 7/9/2020.               **
 * *************************************************/
class Bet(val propType: PropType, val date: Date, val game: Game, val line: Line) {

  def toDBRow {
      throw new NotImplementedError("To DataBase row has yet to be implemented!")
  }

  def payout(money: Float, pick: Pick.type): Float = {
    pick match {
      case Pick.underDog => money * (line.favorite / 100)
      case Pick.favorite => money / (line.underDog / 100)
      case _ => 0
    }
  }

}
package tech.housemoran.realgood.models.api

import java.util.Date

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
class Athlete(val name: String, val position: Position, val height: Height, val weight: Int, val number: Int, val bday: String) {
  override def toString: String = {
    s"$name, ${position.name}, ${height.feet}'${height.inchs}, $weight, $bday"
  }
}

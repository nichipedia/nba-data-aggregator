package tech.housemoran.realgood.models.nba

import tech.housemoran.realgood.models.api.{Athlete, Height, Position}

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
class NBAPlayer(name: String, number: Int, position: Position, height: Height, weight: Int, bday: String) extends Athlete(name, position, height, weight, number, bday) {

}

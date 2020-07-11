package tech.housemoran.realgood.scrapers.api

import java.util.Date

import tech.housemoran.realgood.models.api.{Game, Season}

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
trait GameScraper {
  def getGames(season: Season): List[Game]
  def getGames(date: Date): List[Game]
}

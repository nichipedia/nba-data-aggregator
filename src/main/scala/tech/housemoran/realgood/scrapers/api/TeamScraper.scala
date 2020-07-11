package tech.housemoran.realgood.scrapers.api

import tech.housemoran.realgood.models.api.Team

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
trait TeamScraper {
  def getTeams: List[Team]
}

package tech.housemoran.realgood.scrapers.api

import tech.housemoran.realgood.models.api.Line

trait BetScraper {
  def getCurrentBets: List[Line]
}
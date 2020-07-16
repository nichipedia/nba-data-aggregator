package tech.housemoran.realgood.scrapers.api

import tech.housemoran.realgood.models.api.Bet

trait BetScraper {
  def getCurrentBets: List[Bet]
}
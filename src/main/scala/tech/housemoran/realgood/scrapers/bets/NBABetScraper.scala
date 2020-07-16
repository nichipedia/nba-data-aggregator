package tech.housemoran.realgood.scrapers.bets

import org.jsoup.Jsoup
import tech.housemoran.realgood.models.api.Bet
import tech.housemoran.realgood.scrapers.api.BetScraper

class NBABetScraper extends BetScraper {
  private val urlBase = "https://nj.foxbet.com/#/basketball/competitions"

  override def getCurrentBets: List[Bet] = {
    val resp = Jsoup.connect(urlBase).execute
    val document = Jsoup.parse(resp.body)
    println(document.body())
    List.empty
  }
}

package tech.housemoran.realgood.scrapers.bets

import com.fasterxml.jackson.databind.ObjectMapper
import org.jsoup.Jsoup
import tech.housemoran.realgood.models.api.Bet
import tech.housemoran.realgood.scrapers.api.BetScraper
import java.util.Map

import scala.collection.JavaConverters._

class NBABetScraper extends BetScraper {
  private val urlBase = "https://www.oddsshark.com/nba/odds"
  private val mapper = new ObjectMapper

  override def getCurrentBets: List[Bet] = {
    val resp = Jsoup.connect(urlBase).execute
    val doc = Jsoup.parse(resp.body)
    doc.select("div[class=slidee] div[class=op-item-wrapper]")
        .asScala
        .foreach(line => {
          val jsonDataString = line.child(0).child(0).attr("data-op-info")
          val spreadData = mapper.readValue(jsonDataString, classOf[Map[String, String]]).asScala
          println(spreadData.get("fullgame"))
        })



    List.empty
  }
}

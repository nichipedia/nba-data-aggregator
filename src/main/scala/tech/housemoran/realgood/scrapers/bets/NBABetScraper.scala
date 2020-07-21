package tech.housemoran.realgood.scrapers.bets

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import org.jsoup.Jsoup
import tech.housemoran.realgood.models.api.Line
import tech.housemoran.realgood.scrapers.api.BetScraper
import java.util.Map

import scala.collection.JavaConverters._

class NBABetScraper extends BetScraper {
  private val urlBase = "https://www.oddsshark.com/nba/odds"
  private val mapper = new ObjectMapper



  override def getCurrentBets: List[Line] = {
    val resp = Jsoup.connect(urlBase).execute
    val doc = Jsoup.parse(resp.body)
    val getLineData = (index:  Int) => {
      val line = doc.select("div[class=slidee] div[class=op-item-row-wrapper not-futures]")
        .asScala(index)

      val firstRowSpread = mapper.readValue(line.child(0).child(0).child(0).attr("data-op-info"), classOf[util.Map[String, String]]).asScala
      val firstRowTotal = mapper.readValue(line.child(0).child(0).child(0).attr("data-op-total"), classOf[util.Map[String, String]]).asScala
      val firstRowMoneyLine = mapper.readValue(line.child(0).child(0).child(0).attr("data-op-moneyline"), classOf[util.Map[String, String]]).asScala
      val firstRowSpreadPrice = mapper.readValue(line.child(0).child(0).child(1).attr("data-op-info"), classOf[util.Map[String, String]]).asScala
      val overPrice = mapper.readValue(line.child(0).child(0).child(1).attr("data-op-overprice"), classOf[util.Map[String, String]]).asScala
      val secondRowSpread = mapper.readValue(line.child(0).child(1).child(0).attr("data-op-info"), classOf[util.Map[String, String]]).asScala
      val secondRowTotal = mapper.readValue(line.child(0).child(1).child(0).attr("data-op-total"), classOf[util.Map[String, String]]).asScala
      val secondRowMoneyLine = mapper.readValue(line.child(0).child(1).child(0).attr("data-op-moneyline"), classOf[util.Map[String, String]]).asScala
      val secondRowSpreadPrice = mapper.readValue(line.child(0).child(1).child(1).attr("data-op-info"), classOf[util.Map[String, String]]).asScala
      val underPrice = mapper.readValue(line.child(0).child(1).child(1).attr("data-op-underprice"), classOf[util.Map[String, String]]).asScala

      scala.collection.Map(
        "first_row_spread" -> firstRowSpread("fullgame"),
        "first_row_total" -> firstRowTotal("fullgame"),
        "first_row_moneyline" -> firstRowMoneyLine("fullgame"),
        "first_row_spreadprice" -> firstRowSpreadPrice("fullgame"),
        "overprice" -> overPrice("fullgame"),
        "second_row_spread" -> secondRowSpread("fullgame"),
        "second_row_total" -> secondRowTotal("fullgame"),
        "second_row_moneyline" -> secondRowMoneyLine("fullgame"),
        "second_row_spreadprice" -> secondRowSpreadPrice("fullgame"),
        "underprice" -> underPrice("fullgame")
      )
    }
    val gameInfo = doc.select("div[class=op-content-wrapper] div[class=op-team-data-wrapper not-futures]")
      .asScala
      .head
      .children
      .asScala
    var i = 0
    var game = 0
    var gameDate = ""
    while (i < gameInfo.length) {
      if (gameInfo(i).hasAttr("data-op-date")) {
        gameDate = gameInfo(i).text
        i += 1
      }
      val time = gameInfo(i).child(0).text
      val homeTeam = gameInfo(i).child(2).child(0).text
      val awayTeam = gameInfo(i).child(2).child(1).text
      val betData = getLineData(game)
      println(betData)
      i += 2
    }
    List.empty
  }
}
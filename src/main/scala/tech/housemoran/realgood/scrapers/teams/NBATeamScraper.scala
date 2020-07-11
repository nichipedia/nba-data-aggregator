package tech.housemoran.realgood.scrapers.teams

import java.lang.System.Logger

import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.jsoup.Jsoup
import tech.housemoran.realgood.models.api.{Height, Position, Team}
import tech.housemoran.realgood.models.nba.{NBAPlayer, NBATeam}
import tech.housemoran.realgood.scrapers.api.TeamScraper

import scala.collection.JavaConverters._

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
class NBATeamScraper extends TeamScraper {
  private val baseUrl = "https://www.basketball-reference.com"
  private val log = LoggerFactory.logger(classOf[NBATeamScraper])
  override def getTeams: List[Team] = {
    log.info("Getting NBA Teams!")
    val resp = Jsoup.connect(s"$baseUrl/teams").execute
    val document = Jsoup.parse(resp.body)
    document
      .select("table[id=teams_active] tr[class=full_table]")
      .asScala
      .map(th => {
        val name = th.select("th").get(0)
        val seasonsPageURI = name.child(0).attr("href")
        val seasonsPageData = Jsoup.connect(s"$baseUrl/$seasonsPageURI").execute
        val seasonsDoc = Jsoup.parse(seasonsPageData.body)
        val yearRefUri = seasonsDoc.select("tbody tr th[data-stat=season] a").get(0).attr("href")
        val currentYearData = Jsoup.connect(s"$baseUrl/$yearRefUri").execute
        val currentYearDoc = Jsoup.parse(currentYearData.body)
        val players = currentYearDoc
          .select("table[id=roster] tbody tr")
          .asScala
          .map(tr => {
            val number = tr.child(0).text match {
              case id if id.length == 0 => 0
              case id if id.length > 0 => id.toInt
            }
            val name = tr.child(1).text
            val position = Position(tr.child(2).text)
            val height = Height(tr.child(3).text.split("-")(0).toInt, tr.child(3).text.split("-")(1).toInt)
            val weight = tr.child(4).text.toInt
            val dateString = tr.child(5).text
            new NBAPlayer(name, number, position, height, weight, dateString)
          }).toList
        new NBATeam(name.text, players)
      }).toList
  }
}

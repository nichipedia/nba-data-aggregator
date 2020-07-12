package tech.housemoran.realgood.scrapers.games

import java.util.Date

import scala.collection.JavaConverters._
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import tech.housemoran.realgood.models.api.{Game, Season}
import tech.housemoran.realgood.scrapers.api.GameScraper

class NBAGameScraper extends GameScraper {

  private val baseUrl = "https://www.basketball-reference.com"

  override def getGames(season: Season): List[Game] = {
    val teamsPageUrl = s"$baseUrl/teams"
    val teamsResp = Jsoup.connect(teamsPageUrl).execute
    val teamsDoc = Jsoup.parse(teamsResp.body)
    val games = List[Game]
    teamsDoc
      .select("table[id=teams_active] tr[class=full_table]")
      .asScala
      .map(th => {
        val name = th.select("th").get(0)
        val seasonsPageURI = name.child(0).attr("href")
        val seasonsPageData = Jsoup.connect(s"$baseUrl/$seasonsPageURI").execute
        val seasonsDoc = Jsoup.parse(seasonsPageData.body)
        val yearRefUri = seasonsDoc
          .select("tbody tr th[data-stat=season] a")
          .asScala
          .filter((a: Element) => a.attr("href").split("-")(0).equals(season.year.toString))
          .head
          .attr("href")
        val currentYearData = Jsoup.connect(s"$baseUrl/$yearRefUri").execute
        val currentYearDoc = Jsoup.parse(currentYearData.body)
        val gamesPageUri = currentYearDoc
          .select("div[id=bottom_nav_container] li a")
          .get(1)
          .attr("href")
        val gamesPageResp = Jsoup.connect(s"$baseUrl/$gamesPageUri").execute
        val gamesPageDoc = Jsoup.parse(gamesPageResp.body)
        gamesPageDoc
          .select(" table[id=games] tbody tr")
          .asScala
          .map(tr => {
            val td = tr.select("td")
            val gameDate = td.get(1).text
            val
          })

      })
  }

  override def getGames(date: Date): List[Game] = ???
}

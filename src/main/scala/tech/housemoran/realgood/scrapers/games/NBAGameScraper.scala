package tech.housemoran.realgood.scrapers.games

import java.text.SimpleDateFormat
import java.util.Date
import scala.collection.JavaConverters._
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import tech.housemoran.realgood.models.api.{Game, Season}
import tech.housemoran.realgood.models.nba.{NBAGame, NBATeam}
import tech.housemoran.realgood.scrapers.api.GameScraper
import scala.collection.mutable.ListBuffer

class NBAGameScraper extends GameScraper {

  private val baseUrl = "https://www.basketball-reference.com"

  override def getGames(season: Season): List[Game] = {
    val teamsPageUrl = s"$baseUrl/teams"
    val dateString = "EEE, MMM dd, YYYY"
    val dateFormatter = new SimpleDateFormat(dateString)
    val teamsResp = Jsoup.connect(teamsPageUrl).execute
    val teamsDoc = Jsoup.parse(teamsResp.body)
    val games = new ListBuffer[Game]
    teamsDoc
      .select("table[id=teams_active] tr[class=full_table]")
      .asScala
      .foreach(th => {
        val name = th.select("th").get(0)
        val seasonsPageURI = name.child(0).attr("href")
        val seasonsPageData = Jsoup.connect(s"$baseUrl/$seasonsPageURI").execute
        val seasonsDoc = Jsoup.parse(seasonsPageData.body)
        val yearRefUri = seasonsDoc
          .select("tbody tr th[data-stat=season] a")
          .asScala
          .filter((a: Element) => a.text.split("-")(0).equals(season.year.toString))
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
          .select(" table[id=games] tbody tr:not(tr.thead)")
          .asScala
          .foreach(tr => {
            val td = tr.select("td")
            val gameDate = dateFormatter.parse(td.get(0).text)
            val opponent = td.get(5).text
            val homeCourt = td.get(4).text
            val homeTeam = new NBATeam(name.text, List.empty)
            val awayTeam = new NBATeam(opponent, List.empty)
            if (homeCourt.equals("@")) {
              try {
                val homeScore = td.get(8).text.toInt
                val opponentScore = td.get(9).text.toInt
                games.append(new NBAGame(homeTeam, awayTeam, homeScore, opponentScore, gameDate))
              } catch {
                case e: Throwable => println(s"Could not parse score I think...$e")
              }
            }
          })

      })
    games.toList
  }

  override def getGames(date: Date): List[Game] = ???
}

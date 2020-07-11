package tech.housemoran.realgood.scrapers.teams

import java.lang.System.Logger

import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.jsoup.Jsoup
import tech.housemoran.realgood.models.api.Team
import tech.housemoran.realgood.models.nba.NBATeam
import tech.housemoran.realgood.scrapers.api.TeamScraper

import scala.collection.JavaConverters._

/** ************************************************
 * * Created by Nicholas on 7/10/2020.               **
 * *************************************************/
class NBATeamScraper extends TeamScraper {
  private val url = "https://www.basketball-reference.com/teams/"
  private val log = LoggerFactory.logger(classOf[NBATeamScraper])
  override def getTeams: List[Team] = {
    log.info("Getting NBA Teams!")
    val resp = Jsoup.connect(this.url).execute
    val document = Jsoup.parse(resp.body)
    document
      .select("tr[class=full_table]")
      .asScala
      .map(th => {
        val name = th.select("th").get(0).text
        new NBATeam(name)
      }).toList
  }
}

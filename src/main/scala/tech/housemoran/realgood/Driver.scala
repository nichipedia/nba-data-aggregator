package tech.housemoran.realgood

import org.hibernate.annotations.common.util.impl.LoggerFactory
import tech.housemoran.realgood.scrapers.teams.NBATeamScraper

/** ************************************************
 * * Created by Nicholas on 7/9/2020.               **
 * *************************************************/
class Driver {

}

object Driver {
  val log = LoggerFactory.logger(classOf[Driver])

  def main(args: Array[String]): Unit = {
    log.info("NBA Data Aggregator!!")
    val scraper = new NBATeamScraper
    scraper
      .getTeams
      .foreach(println)
  }
}

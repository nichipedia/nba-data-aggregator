package tech.housemoran.realgood

import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.jboss.logging.Logger
import tech.housemoran.realgood.scrapers.teams.NBATeamScraper

/** ************************************************
 * * Created by Nicholas on 7/9/2020.               **
 * *************************************************/
class Driver {

}

object Driver {
  val log: Logger = LoggerFactory.logger(classOf[Driver])

  def main(args: Array[String]): Unit = {
    log.info("Sports! Data Aggregator!!")
    val scraper = new NBATeamScraper
    scraper
      .getTeams.head
      .activePlayers
      .foreach(println)
  }
}

package tech.housemoran.realgood

import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.jboss.logging.Logger
import tech.housemoran.realgood.models.api.Season
import tech.housemoran.realgood.scrapers.games.NBAGameScraper
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
    val scraper = new NBAGameScraper
    scraper.getGames(Season(2019))
      .foreach(println)

  }
}

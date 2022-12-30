package dev.pucksmart.extract.nhlapi;

import dev.pucksmart.extract.nhlapi.stats.ResponsePlayByPlay;
import dev.pucksmart.extract.nhlapi.stats.ResponseSchedule;
import dev.pucksmart.extract.nhlapi.stats.ResponseSeasons;
import dev.pucksmart.extract.nhlapi.stats.ResponseTeamRoster;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface StatsApi {
  @GetExchange("/api/v1/seasons")
  ResponseSeasons listSeasons();

  @GetExchange("/api/v1/schedule")
  ResponseSchedule getScheduleForDate(@RequestParam("date") String date);

  @GetExchange("/api/v1/teams/{teamId}/roster")
  ResponseTeamRoster getTeamRoster(
      @PathVariable("teamId") long teamId, @RequestParam("season") String seasonId);

  @GetExchange("/api/v1/game/{gameId}/feed/live")
  ResponsePlayByPlay getGamePlayByPlayEvents(@PathVariable("gameId") long gameId);
}

package dev.pucksmart.extract.nhlapi.stats;

import java.time.Instant;
import lombok.Data;

@Data
public class ScheduleGame {
  String gamePk;
  String gameType;
  String season;
  Instant gameDate;
  ScheduleGameStatus status;
  ScheduleGameTeams teams;
  ScheduleGameVenue venue;
}

package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

import java.time.Instant;

@Data
public class ScheduleGame {
  Long gamePk;
  String gameType;
  String season;
  Instant gameDate;
  ScheduleGameStatus status;
  ScheduleGameTeams teams;
  ScheduleGameVenue venue;
}

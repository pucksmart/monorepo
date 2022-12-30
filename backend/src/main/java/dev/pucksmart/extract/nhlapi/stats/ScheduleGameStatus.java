package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

@Data
public class ScheduleGameStatus {
  String abstractGameState;
  String codedGameState;
  String detailedState;
  String statusCode;
  Boolean startTimeTBD;
}

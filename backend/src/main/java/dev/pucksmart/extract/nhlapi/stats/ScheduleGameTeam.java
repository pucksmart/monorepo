package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

@Data
public class ScheduleGameTeam {
  LeagueRecord leagueRecord;
  int score;
  Team team;

  @Data
  public static class LeagueRecord {
    int wins;
    int losses;
    int ties;
    String type;
  }

  @Data
  public static class Team {
    long id;
    String name;
  }
}

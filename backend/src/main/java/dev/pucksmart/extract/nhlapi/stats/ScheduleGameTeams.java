package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

@Data
public class ScheduleGameTeams {
  ScheduleGameTeam away;
  ScheduleGameTeam home;
}

package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

import java.util.List;

@Data
public class ResponseTeamRoster {
  List<RosterPlayer> roster;
}

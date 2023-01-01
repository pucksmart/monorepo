package dev.pucksmart.extract.nhlapi.stats;

import java.util.List;
import lombok.Data;

@Data
public class PlayByPlayPlay {
  List<PlayerEntry> players;
  EventResult result;
  EventMetadata about;
  Coordinates coordinates;
  Team team;

  @Data
  public static class PlayerEntry {
    String playerType;
    Player player;
  }

  @Data
  public static class Player {
    String id;
  }

  @Data
  public static class EventResult {
    String eventTypeId;
    String description;
    String secondaryType;
    Boolean gameWinningGoal;
    Boolean emptyNet;
    Integer penaltyMinutes;
    String penaltySeverity;
  }

  @Data
  public static class EventMetadata {
    long eventIdx;
    long eventId;
    int period;
    String periodType;
    String periodTime;
  }

  @Data
  public static class Coordinates {
    Double x;
    Double y;
  }

  @Data
  public static class Team {
    long String;
  }
}

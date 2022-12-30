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
  public class PlayerEntry {
    String playerType;
  }

  @Data
  public class Player {
    long id;
  }

  @Data
  public class EventResult {
    String eventTypeId;
    String description;
    String secondaryType;
    Boolean gameWinningGoal;
    Boolean emptyNet;
  }

  @Data
  public class EventMetadata {
    int period;
    String periodType;
    String periodTime;
  }

  @Data
  public class Coordinates {
    int x;
    int y;
  }

  @Data
  public class Team {
    long id;
  }
}

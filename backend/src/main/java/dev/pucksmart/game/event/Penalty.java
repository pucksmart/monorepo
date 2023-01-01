package dev.pucksmart.game.event;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Entity
@Table(indexes = {@Index(columnList = "gameId"), @Index(columnList = "playerId")})
public class Penalty {
  @Id
  @Setter(AccessLevel.PROTECTED)
  String id;

  String gameId;
  String playerId;
  String drawnByPlayerId;

  int period;
  String periodTime;
  int minuteLength;
  String infraction;

  Double xCoordinate;
  Double yCoordinate;

  public static String id(String gameId, long eventIndex) {
    return gameId + "-" + eventIndex;
  }

  public Penalty(String gameId, long eventIndex) {
    this.id = id(gameId, eventIndex);
  }
}

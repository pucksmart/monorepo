package dev.pucksmart.game.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Entity
@Table(indexes = {@Index(columnList = "gameId"), @Index(columnList = "shooterPlayerId")})
public class ShotAttempt {
  @Id String id;

  String gameId;
  int period;
  String time;
  Integer timeSeconds;
  RelativeStrength relativeStrength;

  String shotResult;
  String shooterTeamId;
  String shooterPlayerId;
  String goaliePlayerId;
  String blockerPlayerId;

  Double xCoordinate;
  Double yCoordinate;

  public static String id(String gameId, long eventIndex) {
    return gameId + "-" + eventIndex;
  }

  public ShotAttempt(String gameId, long eventIndex) {
    this.id = id(gameId, eventIndex);
  }
}

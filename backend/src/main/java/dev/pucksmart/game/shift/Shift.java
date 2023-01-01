package dev.pucksmart.game.shift;

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
@Table(indexes = {@Index(columnList = "gameId"), @Index(columnList = "playerId")})
public class Shift {
  @Id String id;
  Long shiftId;
  String gameId;
  int eventNumber;

  String duration;
  int period;
  String startTime;
  String endTime;

  String playerId;
  String teamId;

  public static String id(String gameId, long shiftId) {
    return gameId + "-" + shiftId;
  }

  public Shift(String gameId, long shiftId) {
    this.id = id(gameId, shiftId);
  }
}

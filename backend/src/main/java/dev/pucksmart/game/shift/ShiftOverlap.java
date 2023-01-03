package dev.pucksmart.game.shift;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode
@Entity
public class ShiftOverlap {
  @Id
  @Setter(AccessLevel.PACKAGE)
  String id;

  String gameId;
  int period;

  String shift1Id;
  String shift1PlayerId;
  String shift1TeamId;
  Integer shift1StartTimeSeconds;
  Integer shift1EndTimeSeconds;

  String shift2Id;
  String shift2PlayerId;
  String shift2TeamId;
  Integer shift2StartTimeSeconds;
  Integer shift2EndTimeSeconds;

  Integer startTimeSeconds;
  Integer endTimeSeconds;
  Integer duration;

  public static String id(String shift1Id, String shift2Id) {
    return shift1Id + "-" + shift2Id;
  }

  public ShiftOverlap(String shift1Id, String shift2Id) {
    this.id = id(shift1Id, shift2Id);
    this.shift1Id = shift1Id;
    this.shift2Id = shift2Id;
  }
}

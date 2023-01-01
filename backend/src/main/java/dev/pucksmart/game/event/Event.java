package dev.pucksmart.game.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode
@Entity
public class Event {
  @Id
  @GeneratedValue
  @Setter(AccessLevel.PACKAGE)
  Long id;

  String gameId;
}

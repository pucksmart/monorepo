package dev.pucksmart.game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.Instant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Game {
  @Id String gameId;

  GameType gameType;
  String seasonId;
  Instant startAt;
  String venue;
  GameStatus gameStatus;

  String awayTeamId;
  int awayScore;
  String homeTeamId;
  int homeScore;
}

package dev.pucksmart.game;

public enum GameStatus {
  SCHEDULED("Scheduled"),
  PRE_GAME("Pre-Game"),
  IN_PROGRESS("In Progress"),
  IN_PROGRESS_CRITICAL("In Progress - Critical"),
  FINAL("Final"),
  POSTPONED("Postponed");

  private final String description;

  GameStatus(String description) {
    this.description = description;
  }

  public static GameStatus fromDescription(String description) {
    for (GameStatus gameStatus : GameStatus.values()) {
      if (gameStatus.description.equalsIgnoreCase(description)) {
        return gameStatus;
      }
    }
    throw new IllegalArgumentException("No constant with description " + description + " found");
  }
}

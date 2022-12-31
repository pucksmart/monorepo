package dev.pucksmart.game;

public enum GameStatus {
  SCHEDULED("Scheduled"),
  IN_PROGRESS("In Progress"),
  FINAL("Final");

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

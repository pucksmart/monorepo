package dev.pucksmart.game;

public enum GameType {
  PRESEASON("?"),
  REGULAR_SEASON("R"),
  POSTSEASON("P"),
  ALL_STAR("A");

  private final String letter;

  GameType(String letter) {
    this.letter = letter;
  }

  public static GameType fromLetter(String letter) {
    for (GameType gameType : GameType.values()) {
      if (gameType.letter.equalsIgnoreCase(letter)) {
        return gameType;
      }
    }
    throw new IllegalArgumentException("No constant with letter " + letter + " found");
  }
}

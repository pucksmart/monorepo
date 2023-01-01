package dev.pucksmart.game;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GameStatusConverter implements AttributeConverter<GameStatus, String> {
  @Override
  public String convertToDatabaseColumn(GameStatus attribute) {
    return attribute.name();
  }

  @Override
  public GameStatus convertToEntityAttribute(String dbData) {
    return GameStatus.valueOf(dbData);
  }
}

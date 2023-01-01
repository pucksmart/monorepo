package dev.pucksmart.game;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GameTypeConverter implements AttributeConverter<GameType, String> {
  @Override
  public String convertToDatabaseColumn(GameType attribute) {
    return attribute.name();
  }

  @Override
  public GameType convertToEntityAttribute(String dbData) {
    return GameType.valueOf(dbData);
  }
}

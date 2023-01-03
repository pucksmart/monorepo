package dev.pucksmart.game.event;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RelativeStrengthConverter implements AttributeConverter<RelativeStrength, String> {
  @Override
  public String convertToDatabaseColumn(RelativeStrength attribute) {
    if (attribute != null) {
      return attribute.name();
    }
    return null;
  }

  @Override
  public RelativeStrength convertToEntityAttribute(String dbData) {
    if (dbData != null) {
      return RelativeStrength.valueOf(dbData);
    }
    return null;
  }
}

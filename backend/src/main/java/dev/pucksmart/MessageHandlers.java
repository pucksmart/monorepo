package dev.pucksmart;

import dev.pucksmart.extract.nhlapi.stats.StatsSeason;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageHandlers {
  @KafkaListener(topics = "seasons")
  public void handleSeason(StatsSeason season) {
    log.info(season.getSeasonId());
  }
}

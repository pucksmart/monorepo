package dev.pucksmart;

import dev.pucksmart.extract.nhlapi.StatsApi;
import dev.pucksmart.extract.nhlapi.stats.StatsSeason;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
public class BackendController {

    final KafkaTemplate<String, StatsSeason> kafkaTemplate;
    final StatsApi statsApi;
    final SeasonRepository seasonRepository;
    
    public BackendController(KafkaTemplate<String, StatsSeason> kafkaTemplate, StatsApi statsApi, SeasonRepository seasonRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.statsApi = statsApi;
        this.seasonRepository = seasonRepository;
    }

    @GetMapping("/stats/list-seasons")
    String listSeasons() {
        List<StatsSeason> seasons = Objects.requireNonNull(statsApi.listSeasons()).getSeasons();
        for (StatsSeason season : seasons) {
            Season newSeason = new Season();
            newSeason.setId(season.getSeasonId());
            seasonRepository.save(newSeason);
            kafkaTemplate.send("seasons", season);
        }
        return "OK";
    }
}

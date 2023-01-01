package dev.pucksmart;

import dev.pucksmart.extract.nhlapi.StatsApi;
import dev.pucksmart.extract.nhlapi.stats.StatsSeason;
import dev.pucksmart.season.Season;
import dev.pucksmart.season.SeasonRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController {

  final KafkaTemplate<String, String> kafkaTemplate;
  final StatsApi statsApi;
  final SeasonRepository seasonRepository;

  public BackendController(
      KafkaTemplate<String, String> kafkaTemplate,
      StatsApi statsApi,
      SeasonRepository seasonRepository) {
    this.kafkaTemplate = kafkaTemplate;
    this.statsApi = statsApi;
    this.seasonRepository = seasonRepository;
  }

  @GetMapping("/stats/list-seasons")
  String listSeasons() {
    List<StatsSeason> seasons = Objects.requireNonNull(statsApi.listSeasons()).getSeasons();
    for (StatsSeason season : seasons) {
      Season newSeason = seasonRepository.findById(season.getSeasonId()).orElseGet(Season::new);
      newSeason.setId(season.getSeasonId());
      newSeason.setRegularSeasonStartDate(season.getRegularSeasonStartDate());
      newSeason.setRegularSeasonEndDate(season.getRegularSeasonEndDate());
      newSeason.setSeasonEndDate(season.getSeasonEndDate());
      newSeason.setNumberOfGames(season.getNumberOfGames());
      newSeason.setTiesInUse(season.getTiesInUse());
      newSeason.setOlympicsParticipation(season.getOlympicsParticipation());
      newSeason.setConferencesInUse(season.getConferencesInUse());
      newSeason.setDivisionsInUse(season.getDivisionsInUse());
      newSeason.setWildCardInUse(season.getWildCardInUse());
      seasonRepository.save(newSeason);
      kafkaTemplate.send("seasons", season.getSeasonId());
    }
    return "OK";
  }

  @GetMapping("/stats/game/{gameId}")
  String scanGame(@PathVariable String gameId) {
    kafkaTemplate.send("games", gameId);
    return "OK";
  }
}

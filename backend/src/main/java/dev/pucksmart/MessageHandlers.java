package dev.pucksmart;

import dev.pucksmart.extract.nhlapi.StatsApi;
import dev.pucksmart.extract.nhlapi.stats.ResponseSchedule;
import dev.pucksmart.extract.nhlapi.stats.ScheduleDate;
import dev.pucksmart.extract.nhlapi.stats.ScheduleGame;
import dev.pucksmart.extract.nhlapi.stats.StatsSeason;
import dev.pucksmart.game.Game;
import dev.pucksmart.game.GameRepository;
import dev.pucksmart.game.GameStatus;
import dev.pucksmart.game.GameType;
import java.time.LocalDate;

import dev.pucksmart.season.Season;
import dev.pucksmart.season.SeasonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageHandlers {
  final KafkaTemplate<String, String> stringKafkaTemplate;
  final StatsApi statsApi;
  final SeasonRepository seasonRepository;
  final GameRepository gameRepository;

  @KafkaListener(topics = "seasons")
  public void handleSeason(String seasonId) {
    Season season = seasonRepository.findById(seasonId).orElseThrow();
    LocalDate temp = season.getRegularSeasonStartDate();
    stringKafkaTemplate.send("gamedays", temp.toString());
    while (!temp.plusDays(1).isAfter(LocalDate.now())
        && !temp.plusDays(1).isAfter(season.getSeasonEndDate())) {
      temp = temp.plusDays(1);
      stringKafkaTemplate.send("gamedays", temp.toString());
    }
  }

  @KafkaListener(topics = "gamedays")
  public void handleGameday(String date) {
    ResponseSchedule response = statsApi.getScheduleForDate(date);
    if (!response.getDates().isEmpty()) {
      ScheduleDate scheduleDate = response.getDates().get(0);
      for (ScheduleGame scheduleGame : scheduleDate.getGames()) {
        Game newGame = gameRepository.findById(scheduleGame.getGamePk()).orElseGet(Game::new);
        newGame.setGameId(scheduleGame.getGamePk());
        newGame.setGameType(GameType.fromLetter(scheduleGame.getGameType()));
        newGame.setSeasonId(scheduleGame.getSeason());
        newGame.setStartAt(scheduleGame.getGameDate());
        newGame.setVenue(scheduleGame.getVenue().getName());
        newGame.setGameStatus(
            GameStatus.fromDescription(scheduleGame.getStatus().getDetailedState()));
        newGame.setAwayTeamId(scheduleGame.getTeams().getAway().getTeam().getId());
        newGame.setAwayScore(scheduleGame.getTeams().getAway().getScore());
        newGame.setHomeTeamId(scheduleGame.getTeams().getHome().getTeam().getId());
        newGame.setHomeScore(scheduleGame.getTeams().getHome().getScore());
        gameRepository.save(newGame);
      }
    }
  }
}

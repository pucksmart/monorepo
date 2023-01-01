package dev.pucksmart;

import dev.pucksmart.extract.nhlapi.ShiftsApi;
import dev.pucksmart.extract.nhlapi.StatsApi;
import dev.pucksmart.extract.nhlapi.shifts.ResponseShifts;
import dev.pucksmart.extract.nhlapi.shifts.ShiftsShift;
import dev.pucksmart.extract.nhlapi.stats.*;
import dev.pucksmart.game.Game;
import dev.pucksmart.game.GameRepository;
import dev.pucksmart.game.GameStatus;
import dev.pucksmart.game.GameType;
import dev.pucksmart.game.event.Penalty;
import dev.pucksmart.game.event.PenaltyRepository;
import dev.pucksmart.game.shift.Shift;
import dev.pucksmart.game.shift.ShiftRepository;
import dev.pucksmart.season.Season;
import dev.pucksmart.season.SeasonRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
  final ShiftsApi shiftsApi;
  final StatsApi statsApi;
  final SeasonRepository seasonRepository;
  final GameRepository gameRepository;
  final ShiftRepository shiftRepository;
  final PenaltyRepository penaltyRepository;

  @KafkaListener(topics = "seasons")
  public void handleSeason(String seasonId) {
    log.info("seasons({})", seasonId);
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
    log.info("gamedays({})", date);
    ResponseSchedule response = statsApi.getScheduleForDate(date);
    if (!response.getDates().isEmpty()) {
      ScheduleDate scheduleDate = response.getDates().get(0);
      for (ScheduleGame scheduleGame : scheduleDate.getGames()) {
        try {
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
          stringKafkaTemplate.send("games", newGame.getGameId());
        } catch (Exception e) {
          log.error("Error while handling game {}", scheduleGame.toString(), e);
        }
      }
    }
  }

  @KafkaListener(topics = "games")
  public void handleGames(String gameId) {
    log.info("games({})", gameId);
    stringKafkaTemplate.send("game-shifts", gameId);
    stringKafkaTemplate.send("game-events", gameId);
  }

  @KafkaListener(topics = "game-shifts")
  public void handleGameShifts(String gameId) {
    log.info("game-shifts({})", gameId);
    ResponseShifts responseShifts = shiftsApi.getGameShifts(gameId);
    List<Shift> shifts = new ArrayList<>(responseShifts.getData().size());
    for (ShiftsShift shiftsShift : responseShifts.getData()) {
      Shift shift =
          shiftRepository
              .findById(Shift.id(gameId, shiftsShift.getId()))
              .orElseGet(() -> new Shift(gameId, shiftsShift.getId()));
      shift.setShiftId(shiftsShift.getId());
      shift.setGameId(gameId);
      shift.setEventNumber(shiftsShift.getEventNumber());
      shift.setDuration(shiftsShift.getDuration());
      shift.setPeriod(shiftsShift.getPeriod());
      shift.setStartTime(shiftsShift.getStartTime());
      shift.setEndTime(shiftsShift.getEndTime());
      shift.setPlayerId(shiftsShift.getPlayerId());
      shift.setTeamId(shiftsShift.getTeamId());
      shifts.add(shift);
    }
    shiftRepository.saveAll(shifts);
  }

  @KafkaListener(topics = "game-events")
  public void handleGameEvents(String gameId) {
    log.info("game-events({})", gameId);
    ResponsePlayByPlay responsePlayByPlay = statsApi.getGamePlayByPlayEvents(gameId);
    List<Penalty> penalties = new ArrayList<>();
    for (PlayByPlayPlay playByPlayPlay :
        responsePlayByPlay.getLiveData().getPlays().getAllPlays()) {
      switch (playByPlayPlay.getResult().getEventTypeId()) {
        case "PENALTY" -> {
          Penalty penalty =
              penaltyRepository
                  .findById(Penalty.id(gameId, playByPlayPlay.getAbout().getEventIdx()))
                  .orElseGet(() -> new Penalty(gameId, playByPlayPlay.getAbout().getEventIdx()));
          penalty.setGameId(gameId);
          if (playByPlayPlay.getPlayers() != null) {
            playByPlayPlay
                .getPlayers()
                .forEach(
                    player -> {
                      if (player.getPlayerType().equals("PenaltyOn")) {
                        penalty.setPlayerId(player.getPlayer().getId());
                      } else if (player.getPlayerType().equals("DrewBy")) {
                        penalty.setDrawnByPlayerId(player.getPlayer().getId());
                      }
                    });
          }
          penalty.setPeriod(playByPlayPlay.getAbout().getPeriod());
          penalty.setPeriodTime(playByPlayPlay.getAbout().getPeriodTime());
          penalty.setMinuteLength(playByPlayPlay.getResult().getPenaltyMinutes());
          penalty.setInfraction(playByPlayPlay.getResult().getSecondaryType());
          penalty.setXCoordinate(playByPlayPlay.getCoordinates().getX());
          penalty.setYCoordinate(playByPlayPlay.getCoordinates().getY());
          penalties.add(penalty);
        }
        case "FACEOFF" -> {}
        default -> {}
      }
    }
    penaltyRepository.saveAll(penalties);
  }
}

package dev.pucksmart;

import dev.pucksmart.game.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DevJobRunner implements ApplicationRunner {
  final GameRepository gameRepository;
  final MessageHandlers messageHandlers;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    String gameId = "2022020002";
    messageHandlers.handleGameEvents(gameId);
    messageHandlers.handleGameShifts(gameId);
    messageHandlers.handlePostGameShifts(gameId);
    System.exit(0);
  }
}

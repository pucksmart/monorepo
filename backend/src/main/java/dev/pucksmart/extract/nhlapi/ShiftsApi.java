package dev.pucksmart.extract.nhlapi;

import dev.pucksmart.extract.nhlapi.shifts.ResponseShifts;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface ShiftsApi {
    @GetExchange("/stats/rest/en/shiftcharts?cayenneExp=gameId={gameId}")
    ResponseShifts getGameShifts(@PathVariable long gameId);
}

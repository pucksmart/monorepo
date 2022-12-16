package dev.pucksmart.nhlapi;

import dev.pucksmart.nhlapi.shifts.ResponseShifts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ShiftsApi {
    @GET("/stats/rest/en/shiftcharts?cayenneExp=gameId={gameId}")
    Call<ResponseShifts> getGameShifts(@Path("gameId") long gameId);
}

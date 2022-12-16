package dev.pucksmart.nhlapi;

import dev.pucksmart.nhlapi.stats.ResponsePlayByPlay;
import dev.pucksmart.nhlapi.stats.ResponseSchedule;
import dev.pucksmart.nhlapi.stats.ResponseSeasons;
import dev.pucksmart.nhlapi.stats.ResponseTeamRoster;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StatsApi {
    @GET("/api/v1/seasons")
    Call<ResponseSeasons> listSeasons();

    @GET("/api/v1/schedule")
    Call<ResponseSchedule> getScheduleForDate(@Query("date") String date);

    @GET("/api/v1/teams/{teamId}/roster")
    Call<ResponseTeamRoster> getTeamRoster(
            @Path("teamId") long teamId, @Query("season") String seasonId);

    @GET("/api/v1/game/{gameId}/feed/live")
    Call<ResponsePlayByPlay> getGamePlayByPlayEvents(@Path("gameId") long gameId);
}

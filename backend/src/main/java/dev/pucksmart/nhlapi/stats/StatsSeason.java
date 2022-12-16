package dev.pucksmart.nhlapi.stats;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StatsSeason {
    String seasonId;
    LocalDate regularSeasonStartDate;
    LocalDate regularSeasonEndDate;
    LocalDate seasonEndDate;
    Integer numberOfGames;
    Boolean tiesInUse;
    Boolean olympicsParticipation;
    Boolean conferencesInUse;
    Boolean divisionsInUse;
    Boolean wildCardInUse;
}

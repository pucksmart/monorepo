package dev.pucksmart.nhlapi.stats;

import lombok.Data;

@Data
public class RosterPlayer {
    RosterPlayerInfo person;
    String jerseyNumber;
    RosterPlayerPosition position;
}

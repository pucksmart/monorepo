package dev.pucksmart.nhlapi.shifts;

import lombok.Data;

@Data
public class Shift {
    long id;
    String duration;
    String endTime;
    int eventNumber;
    int period;
    long playerId;
    String startTime;
    long teamId;
}

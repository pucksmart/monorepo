package dev.pucksmart.extract.nhlapi.shifts;

import lombok.Data;

@Data
public class ShiftsShift {
  Long id;
  String duration;
  String endTime;
  int eventNumber;
  int period;
  String playerId;
  String startTime;
  String teamId;
}

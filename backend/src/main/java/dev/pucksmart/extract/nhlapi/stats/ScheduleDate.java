package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ScheduleDate {
  LocalDate date;
  List<ScheduleGame> games;
}

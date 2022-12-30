package dev.pucksmart.extract.nhlapi.shifts;

import lombok.Data;

import java.util.List;

@Data
public class ResponseShifts {
  List<Shift> data;
}

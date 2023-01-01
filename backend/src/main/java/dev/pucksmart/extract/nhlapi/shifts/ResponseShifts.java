package dev.pucksmart.extract.nhlapi.shifts;

import java.util.List;
import lombok.Data;

@Data
public class ResponseShifts {
  List<ShiftsShift> data;
}

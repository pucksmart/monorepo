package dev.pucksmart.nhlapi.shifts;

import lombok.Data;

import java.util.List;

@Data
public class ResponseShifts {
    List<Shift> data;
}

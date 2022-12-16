package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseSeasons {
    List<StatsSeason> seasons = new ArrayList<>();
}

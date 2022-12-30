package dev.pucksmart.extract.nhlapi.stats;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ResponseSeasons {
  List<StatsSeason> seasons = new ArrayList<>();
}

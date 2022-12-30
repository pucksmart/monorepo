package dev.pucksmart.extract.nhlapi.stats;

import java.util.List;
import lombok.Data;

@Data
public class PlayByPlayPlays {
  List<PlayByPlayPlay> allPlays;
}

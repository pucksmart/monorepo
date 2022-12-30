package dev.pucksmart.extract.nhlapi.stats;

import lombok.Data;

@Data
public class ResponsePlayByPlay {
  long gamePk;
  PlayByPlayLiveData liveData;
}

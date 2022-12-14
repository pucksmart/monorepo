package dev.pucksmart.season;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Season implements Comparable<Season> {
  @Id String id;
  LocalDate regularSeasonStartDate;
  LocalDate regularSeasonEndDate;
  LocalDate seasonEndDate;
  Integer numberOfGames;
  Boolean tiesInUse;
  Boolean olympicsParticipation;
  Boolean conferencesInUse;
  Boolean divisionsInUse;
  Boolean wildCardInUse;

  @Override
  public int compareTo(Season o) {
    return regularSeasonStartDate.compareTo(o.regularSeasonStartDate);
  }
}

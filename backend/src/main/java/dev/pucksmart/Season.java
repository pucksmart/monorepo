package dev.pucksmart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Season {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

//    @Column
    //    LocalDate regularSeasonStartDate; --need type conversions
    //    LocalDate regularSeasonEndDate; --need type conversions
    //    LocalDate seasonEndDate; --need type conversions
    //    Integer numberOfGames;
    //    Boolean tiesInUse;
    //    Boolean olympicsParticipation;
    //    Boolean conferencesInUse;
    //    Boolean divisionsInUse;
    //    Boolean wildCardInUse;
}

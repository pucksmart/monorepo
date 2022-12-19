package dev.pucksmart;

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
}

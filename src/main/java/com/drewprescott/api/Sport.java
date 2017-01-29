package com.drewprescott.api;


import com.drewprescott.core.SportEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public class Sport {
    private long id;
    private String name;

    public Sport() {
        // Jackson deserialization
    }

    public Sport(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    static public Sport mapToSport(SportEntity sportEntity) {
        Sport sport = new Sport(
                sportEntity.getId(),
                sportEntity.getName()
        );
        return sport;
    }

    static public List<Sport> mapToSportList(List<SportEntity> sportEntityList) {
        List<Sport> sportList = new ArrayList<Sport>();
        for (SportEntity sportEntity: sportEntityList) {
            sportList.add(mapToSport(sportEntity));
        }
        return sportList;
    }
}

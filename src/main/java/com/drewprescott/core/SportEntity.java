package com.drewprescott.core;

import com.drewprescott.api.Sport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sport")
public class SportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "sportId", fetch = FetchType.LAZY)
    private Set<EventTagEntity> evenTags;

    @OneToMany(mappedBy = "sportId", fetch = FetchType.LAZY)
    private Set<TeamEntity> teams;

    public SportEntity() {
        //Serialization
    }

    public SportEntity(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SportEntity that = (SportEntity) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    static public SportEntity mapToEntity(Sport sport) {
        SportEntity sportEntity = new SportEntity(
                sport.getName()
        );
        return sportEntity;
    }

    static public List<SportEntity> mapToEntityList(List<Sport> sportList) {
        List<SportEntity> sportEntityList = new ArrayList<SportEntity>();
        for (Sport sport: sportList) {
            sportEntityList.add(mapToEntity(sport));
        }
        return sportEntityList;
    }
}

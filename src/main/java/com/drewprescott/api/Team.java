package com.drewprescott.api;

import com.drewprescott.core.TeamEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private long id;
    private String name;
    private long sportId;
    private Instant createdTimeStamp;
    private long createdUserId;

    public Team() {
        // Jackson deserialization
    }

    public Team(long id, String name, long sportId, Instant createdTimeStamp, long createdUserId) {
        this.id = id;
        this.name = name;
        this.sportId = sportId;
        this.createdTimeStamp = createdTimeStamp;
        this.createdUserId = createdUserId;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public long getSportId() {
        return sportId;
    }

    @JsonProperty
    public Instant getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @JsonProperty
    public long getCreatedUserId() {
        return createdUserId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sportId=" + sportId +
                ", createdUserId=" + createdUserId +
                '}';
    }

    static public Team mapToTeam(TeamEntity teamEntity) {
        Team team = new Team(
                teamEntity.getId(),
                teamEntity.getName(),
                teamEntity.getSportId(),
                teamEntity.getCreatedTimeStamp().toInstant(),
                teamEntity.getCreatedUserId()
        );
        return team;
    }

    static public List<Team> mapToTeamList(List<TeamEntity> teamEntityList) {
        List<Team> teamList = new ArrayList<Team>();
        for (TeamEntity teamEntity: teamEntityList) {
            teamList.add(mapToTeam(teamEntity));
        }
        return teamList;
    }
}

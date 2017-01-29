package com.drewprescott.core;

import com.drewprescott.api.Team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "team")
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sport_id", nullable = false)
    private long sportId;

    @Column(name="created_ts", nullable = false)
    private Timestamp createdTimeStamp;

    @Column(name="created_user_id", nullable = false)
    private long createdUserId;

    @ManyToOne
    @JoinColumn(name = "sport_id", insertable = false, updatable = false)
    private SportEntity sport;

    @ManyToOne
    @JoinColumn(name = "created_user_id", insertable = false, updatable = false)
    private UserEntity createdUser;

    @OneToMany(mappedBy = "teamId", fetch = FetchType.LAZY)
    private Set<EventEntity> events;

    @OneToMany(mappedBy = "teamId", fetch = FetchType.LAZY)
    private Set<EventTagEntity> eventTags;

    @OneToMany(mappedBy = "teamId", fetch = FetchType.LAZY)
    private Set<UserTeamRelEntity> userTeamRels;

    public TeamEntity() {
        //Serialization
    }

    public TeamEntity(String name, long sportId, long createdUserId) {
        this.name = name;
        this.sportId = sportId;
        this.createdUserId = createdUserId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getSportId() {
        return sportId;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public long getCreatedUserId() {
        return createdUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamEntity that = (TeamEntity) o;

        if (id != that.id) return false;
        if (sportId != that.sportId) return false;
        if (createdUserId != that.createdUserId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return createdTimeStamp != null ? createdTimeStamp.equals(that.createdTimeStamp) : that.createdTimeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (sportId ^ (sportId >>> 32));
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        result = 31 * result + (int) (createdUserId ^ (createdUserId >>> 32));
        return result;
    }

    static public TeamEntity mapToEntity(Team team) {
        TeamEntity teamEntity = new TeamEntity(
                team.getName(),
                team.getSportId(),
                team.getCreatedUserId()
        );
        return teamEntity;
    }

    static public List<TeamEntity> mapToEntityList(List<Team> teamList) {
        List<TeamEntity> teamEntityList = new ArrayList<TeamEntity>();
        for (Team team: teamList) {
            teamEntityList.add(mapToEntity(team));
        }
        return teamEntityList;
    }
}

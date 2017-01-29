package com.drewprescott.core;

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
import java.sql.Timestamp;

@Entity
@Table(name = "user_team_rel")
public class UserTeamRelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "team_id", nullable = false)
    private long teamId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "role_id", nullable = false)
    private long roleId;

    @Column(name="accepted_ts", nullable = true)
    private Timestamp acceptedTimeStamp;

    @Column(name="created_ts", nullable = false)
    private Timestamp createdTimeStamp;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private TeamEntity team;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private RoleEntity role;

    public UserTeamRelEntity() {
        //Serialization
    }

    public UserTeamRelEntity(long teamId, long userId, long roleId, Timestamp acceptedTimeStamp) {
        this.teamId = teamId;
        this.userId = userId;
        this.roleId = roleId;
        this.acceptedTimeStamp = acceptedTimeStamp;
    }

    public long getId() {
        return id;
    }

    public long getTeamId() {
        return teamId;
    }

    public long getUserId() {
        return userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public Timestamp getAcceptedTimeStamp() {
        return acceptedTimeStamp;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTeamRelEntity that = (UserTeamRelEntity) o;

        if (id != that.id) return false;
        if (teamId != that.teamId) return false;
        if (userId != that.userId) return false;
        if (roleId != that.roleId) return false;
        if (acceptedTimeStamp != null ? !acceptedTimeStamp.equals(that.acceptedTimeStamp) : that.acceptedTimeStamp != null)
            return false;
        return createdTimeStamp != null ? createdTimeStamp.equals(that.createdTimeStamp) : that.createdTimeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (teamId ^ (teamId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (acceptedTimeStamp != null ? acceptedTimeStamp.hashCode() : 0);
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        return result;
    }
}

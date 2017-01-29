package com.drewprescott.core;

import com.drewprescott.api.EventTag;

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
@Table(name = "event_tag")
public class EventTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @Column(name = "sport_id", nullable = true)
    private Long sportId;

    @Column(name = "team_id", nullable = true)
    private Long teamId;

    @Column(name="created_user_id", nullable = true)
    private Long createdUserId;

    @Column(name="created_ts", nullable = false, insertable = false, updatable = false)
    private Timestamp createdTimeStamp;

    @ManyToOne
    @JoinColumn(name = "sport_id", insertable = false, updatable = false)
    private SportEntity sport;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private TeamEntity team;

    @ManyToOne
    @JoinColumn(name = "created_user_id", insertable = false, updatable = false)
    private UserEntity createdUser;

    @OneToMany(mappedBy = "eventId", fetch = FetchType.LAZY)
    private Set<EventTagRelEntity> eventTagRels;

    public EventTagEntity() {
        //Serialization
    }

    public EventTagEntity(String tagName, Long sportId, Long teamId, Long createdUserId) {
        this.tagName = tagName;
        this.sportId = sportId;
        this.teamId = teamId;
        this.createdUserId = createdUserId;
    }

    public long getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public Long getSportId() {
        return sportId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public Long getCreatedUserId() {
        return createdUserId;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventTagEntity that = (EventTagEntity) o;

        if (id != that.id) return false;
        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;
        if (sportId != null ? !sportId.equals(that.sportId) : that.sportId != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (createdUserId != null ? !createdUserId.equals(that.createdUserId) : that.createdUserId != null)
            return false;
        return createdTimeStamp != null ? createdTimeStamp.equals(that.createdTimeStamp) : that.createdTimeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + (sportId != null ? sportId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (createdUserId != null ? createdUserId.hashCode() : 0);
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        return result;
    }

    static public EventTagEntity mapToEntity(EventTag eventTag) {
        EventTagEntity eventTagEntity = new EventTagEntity(
                eventTag.getTagName(),
                eventTag.getSportId(),
                eventTag.getTeamId(),
                eventTag.getCreatedUserId()
        );
        return eventTagEntity;
    }

    static public List<EventTagEntity> mapToEntityList(List<EventTag> eventTagList) {
        List<EventTagEntity> eventTagEntityList = new ArrayList<EventTagEntity>();
        for (EventTag eventTag: eventTagList) {
            eventTagEntityList.add(mapToEntity(eventTag));
        }
        return eventTagEntityList;
    }
}

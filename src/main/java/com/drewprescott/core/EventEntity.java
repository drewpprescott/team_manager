package com.drewprescott.core;

import com.drewprescott.api.Event;

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
@Table(name = "event")
@NamedQueries({
        @NamedQuery(
                name = "com.drewprescott.core.Event.findAll",
                query = "SELECT pe FROM EventEntity pe"
        ),
        @NamedQuery(
                name = "com.drewprescott.core.Event.findByTitle",
                query = "SELECT pe FROM EventEntity pe "
                        + "where pe.title like :title"
        )
})
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "start_date", nullable = true)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = true)
    private Timestamp endDate;

    @Column(name="description", nullable = true)
    private String description;

    @Column(name="location", nullable = true)
    private String location;

    @Column(name="created_user_id", nullable = false)
    private long createdUserId;

    @Column(name="team_id", nullable = true)
    private Long teamId;

    @Column(name="created_ts", nullable = false, insertable = false, updatable = false)
    private Timestamp createdTimeStamp;

    @Column(name="updated_ts", nullable = false, insertable = false, updatable = false)
    private Timestamp updatedTimeStamp;

    @ManyToOne
    @JoinColumn(name = "created_user_id", insertable = false, updatable = false)
    private UserEntity createdUser;

    @ManyToOne
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private TeamEntity team;

    @OneToMany(mappedBy = "masterEventId", fetch = FetchType.LAZY)
    private Set<EventEventRelEntity> masterEventRelationships;

    @OneToMany(mappedBy = "subEventId", fetch = FetchType.LAZY)
    private Set<EventEventRelEntity> subEventRelSet;

    public EventEntity() {
        //Serialization
    }

    public EventEntity(String title, Timestamp startDate, Timestamp endDate, String description, String location, long createdUserId, Long teamId) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.location = location;
        this.createdUserId = createdUserId;
        this.teamId = teamId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public long getCreatedUserId() {
        return createdUserId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    public Timestamp getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        if (id != that.id) return false;
        if (createdUserId != that.createdUserId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;
        if (createdTimeStamp != null ? !createdTimeStamp.equals(that.createdTimeStamp) : that.createdTimeStamp != null)
            return false;
        return updatedTimeStamp != null ? updatedTimeStamp.equals(that.updatedTimeStamp) : that.updatedTimeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (int) (createdUserId ^ (createdUserId >>> 32));
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        result = 31 * result + (updatedTimeStamp != null ? updatedTimeStamp.hashCode() : 0);
        return result;
    }

    static public EventEntity mapToEntity(Event event) {
        EventEntity eventEntity = new EventEntity(
                event.getTitle(),
                Timestamp.from(event.getStartDate()),
                Timestamp.from(event.getEndDate()),
                event.getDescription(),
                event.getLocation(),
                event.getCreatedUserId(),
                event.getTeamId()
        );
        return eventEntity;
    }

    static public List<EventEntity> mapToEntityList(List<Event> eventList) {
        List<EventEntity> eventEntityList = new ArrayList<EventEntity>();
        for (Event event: eventList) {
            eventEntityList.add(mapToEntity(event));
        }
        return eventEntityList;
    }
}

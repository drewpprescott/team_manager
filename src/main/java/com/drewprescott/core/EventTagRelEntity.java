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
@Table(name = "event_tag_rel")
public class EventTagRelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "event_id", nullable = false)
    private long eventId;

    @Column(name = "tag_id", nullable = false)
    private long tagId;

    @Column(name="created_ts", nullable = false)
    private Timestamp createdTimeStamp;

    @Column(name = "created_user_id", nullable = false)
    private long createdUserId;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private EventTagEntity eventTag;

    @ManyToOne
    @JoinColumn(name = "created_user_id", insertable = false, updatable = false)
     private UserEntity createdUser;

    public EventTagRelEntity() {
        //Serialization
    }

    public EventTagRelEntity(long eventId, long tagId, long createdUserId) {
        this.eventId = eventId;
        this.tagId = tagId;
        this.createdUserId = createdUserId;
    }

    public long getId() {
        return id;
    }

    public long getEventId() {
        return eventId;
    }

    public long getTagId() {
        return tagId;
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

        EventTagRelEntity that = (EventTagRelEntity) o;

        if (id != that.id) return false;
        if (eventId != that.eventId) return false;
        if (tagId != that.tagId) return false;
        if (createdUserId != that.createdUserId) return false;
        return createdTimeStamp != null ? createdTimeStamp.equals(that.createdTimeStamp) : that.createdTimeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (eventId ^ (eventId >>> 32));
        result = 31 * result + (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        result = 31 * result + (int) (createdUserId ^ (createdUserId >>> 32));
        return result;
    }
}

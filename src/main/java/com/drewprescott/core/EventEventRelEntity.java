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
@Table(name = "event_event_rel")
public class EventEventRelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "master_event_id", nullable = true)
    private Long masterEventId;

    @Column(name = "sub_event_id", nullable = true)
    private Long subEventId;

    @Column(name = "created_user_id", nullable = false)
    private long createdUserId;

    @Column(name="created_ts", nullable = false, insertable = false, updatable = false)
    private Timestamp createdTimeStamp;

    @ManyToOne
    @JoinColumn(name = "master_event_id", insertable = false, updatable = false)
    private EventEntity masterEvent;

    @ManyToOne
    @JoinColumn(name = "sub_event_id", insertable = false, updatable = false)
    private EventEntity subEvent;

    @ManyToOne
    @JoinColumn(name = "created_user_id", insertable = false, updatable = false)
    private UserEntity createdUser;

    public EventEventRelEntity() {
        //Serialization
    }

    public EventEventRelEntity(Long masterEventId, Long subEventId, long createdUserId) {
        this.masterEventId = masterEventId;
        this.subEventId = subEventId;
        this.createdUserId = createdUserId;
    }

    public long getId() {
        return id;
    }

    public Long getMasterEventId() {
        return masterEventId;
    }

    public Long getSubEventId() {
        return subEventId;
    }

    public long getCreatedUserId() {
        return createdUserId;
    }

    public Timestamp getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEventRelEntity that = (EventEventRelEntity) o;

        if (id != that.id) return false;
        if (createdUserId != that.createdUserId) return false;
        if (masterEventId != null ? !masterEventId.equals(that.masterEventId) : that.masterEventId != null)
            return false;
        if (subEventId != null ? !subEventId.equals(that.subEventId) : that.subEventId != null) return false;
        return createdTimeStamp != null ? createdTimeStamp.equals(that.createdTimeStamp) : that.createdTimeStamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (masterEventId != null ? masterEventId.hashCode() : 0);
        result = 31 * result + (subEventId != null ? subEventId.hashCode() : 0);
        result = 31 * result + (int) (createdUserId ^ (createdUserId >>> 32));
        result = 31 * result + (createdTimeStamp != null ? createdTimeStamp.hashCode() : 0);
        return result;
    }
}

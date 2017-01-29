package com.drewprescott.api;


import com.drewprescott.core.EventTagEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class EventTag {
    private long id;
    private String tagName;
    private Long sportId;
    private Long teamId;
    private Long createdUserId;
    private Instant createdTimeStamp;

    public EventTag() {
        // Jackson deserialization
    }

    public EventTag(long id, String tagName, Long sportId, Long teamId, Long createdUserId, Instant createdTimeStamp) {
        this.id = id;
        this.tagName = tagName;
        this.sportId = sportId;
        this.teamId = teamId;
        this.createdUserId = createdUserId;
        this.createdTimeStamp = createdTimeStamp;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getTagName() {
        return tagName;
    }

    @JsonProperty
    public Long getSportId() {
        return sportId;
    }

    @JsonProperty
    public Long getTeamId() {
        return teamId;
    }

    @JsonProperty
    public Long getCreatedUserId() {
        return createdUserId;
    }

    @JsonProperty
    public Instant getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @Override
    public String toString() {
        return "EventTag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", sportId=" + sportId +
                ", teamId=" + teamId +
                ", createdUserId=" + createdUserId +
                ", createdTimeStamp=" + createdTimeStamp +
                '}';
    }

    static public EventTag mapToEventTag(EventTagEntity eventTagEntity) {
        EventTag eventTag = new EventTag(
                eventTagEntity.getId(),
                eventTagEntity.getTagName(),
                eventTagEntity.getSportId(),
                eventTagEntity.getTeamId(),
                eventTagEntity.getCreatedUserId(),
                eventTagEntity.getCreatedTimeStamp().toInstant()
        );
        return eventTag;
    }

    static public List<EventTag> mapToEventTagList(List<EventTagEntity> eventTagEntityList) {
        List<EventTag> eventTagList = new ArrayList<EventTag>();
        for (EventTagEntity eventTagEntity: eventTagEntityList) {
            eventTagList.add(mapToEventTag(eventTagEntity));
        }
        return eventTagList;
    }
}

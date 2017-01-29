package com.drewprescott.api;

import com.drewprescott.core.EventEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private long id;
    private String title;
    private Instant startDate;
    private Instant endDate;
    private String description;
    private String location;
    private long createdUserId;
    private Long teamId;
    private Instant createdTimeStamp;
    private Instant updatedTimeStamp;

    public Event() {
        // Jackson deserialization
    }

    public Event(long id, String title, Instant startDate, Instant endDate, String description, String location, long createdUserId, Long teamId, Instant createdTimeStamp, Instant updatedTimeStamp) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.location = location;
        this.createdUserId = createdUserId;
        this.teamId = teamId;
        this.createdTimeStamp = createdTimeStamp;
        this.updatedTimeStamp = updatedTimeStamp;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public Instant getStartDate() {
        return startDate;
    }

    @JsonProperty
    public Instant getEndDate() {
        return endDate;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public String getLocation() {
        return location;
    }

    @JsonProperty
    public long getCreatedUserId() {
        return createdUserId;
    }

    @JsonProperty
    public Long getTeamId() {
        return teamId;
    }

    @JsonProperty
    public Instant getCreatedTimeStamp() {
        return createdTimeStamp;
    }

    @JsonProperty
    public Instant getUpdatedTimeStamp() {
        return updatedTimeStamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", createdUserId=" + createdUserId +
                ", teamId=" + teamId +
                ", createdTimeStamp=" + createdTimeStamp +
                ", updatedTimeStamp=" + updatedTimeStamp +
                '}';
    }

    static public Event mapToEvent(EventEntity eventEntity) {
        Event event = new Event(
                eventEntity.getId(),
                eventEntity.getTitle(),
                eventEntity.getStartDate() != null ? eventEntity.getStartDate().toInstant() : null,
                eventEntity.getEndDate() != null ? eventEntity.getEndDate().toInstant() : null,
                eventEntity.getDescription(),
                eventEntity.getLocation(),
                eventEntity.getCreatedUserId(),
                eventEntity.getTeamId(),
                eventEntity.getCreatedTimeStamp().toInstant(),
                eventEntity.getUpdatedTimeStamp().toInstant()
        );
        return event;
    }

    static public List<Event> mapToEventList(List<EventEntity> eventEntityList) {
        List<Event> eventList = new ArrayList<Event>();
        for (EventEntity eventEntity: eventEntityList) {
            eventList.add(mapToEvent(eventEntity));
        }
        return eventList;
    }
}

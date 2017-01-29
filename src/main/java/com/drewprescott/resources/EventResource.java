package com.drewprescott.resources;

import com.drewprescott.api.Event;
import com.codahale.metrics.annotation.Timed;
import com.drewprescott.core.EventEntity;
import com.drewprescott.db.EventDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.Optional;
import java.util.List;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {

    private EventDAO eventDAO;

    public EventResource(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Event> findByTitle(@QueryParam("title") Optional<String> title) {
        if (title.isPresent()) {
            return Event.mapToEventList(eventDAO.findByTitle(title.get()));
        } else {
            return Event.mapToEventList(eventDAO.findAll());
        }
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    public Event findById(@PathParam("id") LongParam id) {
        return findSafelyById(id.get());
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    public Event addPracticeEvent(Event event) {
        EventEntity newEventEntity = eventDAO.create(EventEntity.mapToEntity(event));
        return Event.mapToEvent(newEventEntity);
    }

    private Event findSafelyById(long eventId) {
        EventEntity eventEntity = eventDAO.findById(eventId).orElseThrow(() -> new NotFoundException("No such event."));
        return Event.mapToEvent(eventEntity);
    }
}

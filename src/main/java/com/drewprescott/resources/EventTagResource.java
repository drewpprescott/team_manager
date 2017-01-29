package com.drewprescott.resources;

import com.drewprescott.db.EventTagDAO;
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

@Path("/event-tag")
@Consumes(MediaType.APPLICATION_JSON)
public class EventTagResource {
    private EventTagDAO eventTagDAO;

    public EventTagResource(EventTagDAO eventTagDAO) {
        this.eventTagDAO = eventTagDAO;
    }


}

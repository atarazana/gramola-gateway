package com.redhat.gramola.gateway.restclient;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.gramola.gateway.beans.Event;
import com.redhat.gramola.gateway.beans.Status;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/")
@RegisterRestClient(configKey="events-api")
public interface EventsService {

    @GET
    @Path("events")
    @Produces("application/json")
    List<Event> allEvents();

    @GET
    @Path("events/{country}/{city}")
    @Produces("application/json")
    List<Event> eventsByCountryAndCity(@PathParam("country") String country, @PathParam("city") String city);

    @POST
    @Path("events")
    @Produces("application/json")
    Response saveEvent(Event Event);

    @Path("images/{fileName}")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    Response filesGet(@PathParam("fileName") String fileId);

    @GET
    @Path("q/health")
    @Produces("application/json")
    Status health();
}
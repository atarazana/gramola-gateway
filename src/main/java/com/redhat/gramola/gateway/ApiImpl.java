package com.redhat.gramola.gateway;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.redhat.gramola.gateway.beans.Check;
import com.redhat.gramola.gateway.beans.Config;
import com.redhat.gramola.gateway.beans.Event;
import com.redhat.gramola.gateway.beans.FileResponse;
import com.redhat.gramola.gateway.beans.FileUpload;
import com.redhat.gramola.gateway.beans.Status;
import com.redhat.gramola.gateway.beans.TimelineEntry;
import com.redhat.gramola.gateway.restclient.EventsService;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

public class ApiImpl implements ApiResource {
    Logger logger = Logger.getLogger(ApiImpl.class);

    @ConfigProperty(name = "config.service.name", defaultValue = "gramola-gateway-svc")
    String configServiceName;

    @ConfigProperty(name = "gramola.welcome-message", defaultValue = "Welcome")
    String welcome;
    
    @Inject
    @RestClient
    EventsService eventsService;
    
    @Override
    public List<Event> eventsGetAll() {
        logger.debug("eventsGetAll was called!");
        return eventsService.allEvents();
    }

    @Override
    public Response eventsPost(Event data) {
        return eventsService.saveEvent(data);
    }

    @Override
    public List<Event> eventsGetByCountryAndCity(String country, String city) {
        return eventsService.eventsByCountryAndCity(country, city);
    }

    @Override
    public Response filesGet(String fileId) {
        return eventsService.filesGet(fileId);
    }

    @Override
    public FileResponse filesPost(FileUpload data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TimelineEntry timelinePost(TimelineEntry data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TimelineEntry> timelineGetByEventIdAndUserId(String eventId, String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Config configGet() {
        Check[] checks = new Check[0];
        String status = "OPERATIONAL";
        try {
            Status eventServiceStatus = eventsService.health();
            logger.debug(eventServiceStatus);
            checks = eventServiceStatus.getChecks();
            if (eventServiceStatus.getStatus() != "UP") {
                status = "DEGRADED";
            }
        } catch (Exception e) {
            status = "DEGRADED";
            checks = new Check[1];
            checks[0] = new Check("event-service", "DOWN");
        }
        
        return new Config(configServiceName, new Status(status, checks));
    }
    
}
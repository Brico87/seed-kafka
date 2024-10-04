package com.ft.demo.controller;

import com.ft.demo.service.MessagingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import java.time.OffsetDateTime;

@Path("/emit")
public class MessagingController {

    private static final Logger LOGGER = Logger.getLogger(MessagingController.class);

    @Inject
    MessagingService messagingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String emit() {
        try {
            OffsetDateTime date = messagingService.emitData();
            return "emitted - " + date;
        } catch (Exception e) {
            LOGGER.error("Error while emitting message", e);
            return "error - " + e.getMessage();
        }
    }
}

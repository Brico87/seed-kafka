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

    @Inject
    MessagingService messagingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String emit() {
        OffsetDateTime date = messagingService.emitData();
        return "emitted - " + date;
    }
}

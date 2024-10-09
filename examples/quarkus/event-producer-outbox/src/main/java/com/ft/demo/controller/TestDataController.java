package com.ft.demo.controller;

import com.ft.demo.service.TestDataService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/event")
public class TestDataController {

    @Inject
    TestDataService testDataService;

    @GET
    public String emitEvent() {
        return testDataService.execute();
    }
}

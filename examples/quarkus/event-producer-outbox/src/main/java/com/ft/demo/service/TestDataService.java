package com.ft.demo.service;

import com.ft.demo.generated.model.TestDataPayload;
import com.ft.demo.model.TestDataEvent;
import io.debezium.outbox.quarkus.ExportedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class TestDataService {

    @Inject
    Event<ExportedEvent<?, ?>> emitter;

    @Transactional
    public String execute() {
        TestDataPayload payload = new TestDataPayload();
        payload.setText("test data");
        String id = UUID.randomUUID().toString();
        TestDataEvent event = TestDataEvent.build(id, payload);
        emitter.fire(event);
        return id;
    }
}

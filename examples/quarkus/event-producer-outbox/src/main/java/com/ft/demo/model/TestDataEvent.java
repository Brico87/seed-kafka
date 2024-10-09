package com.ft.demo.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ft.demo.generated.model.TestDataPayload;
import io.debezium.outbox.quarkus.ExportedEvent;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class TestDataEvent implements ExportedEvent<String, JsonNode> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final String id;
    private final JsonNode jsonNode;
    private final Instant timestamp;

    private TestDataEvent(String id, JsonNode jsonNode) {
        this.id = id;
        this.jsonNode = jsonNode;
        this.timestamp = Instant.now(Clock.system(ZoneId.of("UTC")));
    }

    public static TestDataEvent build(String id, TestDataPayload payload) {
        ObjectNode objectNode = MAPPER.createObjectNode()
                .put("id", id)
                .putPOJO("data", payload);
        return new TestDataEvent(id, objectNode);
    }

    @Override
    public String getAggregateId() {
        return id;
    }

    @Override
    public String getAggregateType() {
        return "TestDataPayload";
    }

    @Override
    public String getType() {
        return "TestData";
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }

    @Override
    public JsonNode getPayload() {
        return jsonNode;
    }
}

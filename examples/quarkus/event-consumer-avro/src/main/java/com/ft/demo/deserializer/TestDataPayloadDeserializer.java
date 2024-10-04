package com.ft.demo.deserializer;


import com.ft.demo.TestDataPayload;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class TestDataPayloadDeserializer extends ObjectMapperDeserializer<TestDataPayload> {
    public TestDataPayloadDeserializer() {
        super(TestDataPayload.class);
    }
}

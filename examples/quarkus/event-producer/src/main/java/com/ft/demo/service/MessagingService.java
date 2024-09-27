package com.ft.demo.service;

import com.ft.demo.generated.model.TestDataPayload;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.eclipse.microprofile.reactive.messaging.Message.LOGGER;

// Main doc: https://quarkus.io/guides/kafka
@ApplicationScoped
public class MessagingService {

    @Inject
    @Channel("data")
    Emitter<TestDataPayload> emitter;

    // A regarder: https://quarkus.io/guides/kafka#commit-strategies
    // A regarder: https://quarkus.io/guides/kafka#kafka-bare-clients
    // A regarder: https://quarkus.io/guides/kafka#avro-serialization
    // A regarder: https://quarkus.io/guides/kafka-schema-registry-avro
    public OffsetDateTime emitData() {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneId.of("UTC"));
        LOGGER.info("Emit data at time " + timestamp);
        OutgoingKafkaRecordMetadata<String> metadata = buildRecordMetadata(timestamp);
        TestDataPayload payload = new TestDataPayload();
        payload.setText("test-data");
        Message<TestDataPayload> message = Message.of(payload)
                .addMetadata(metadata);
        emitter.send(message);
        return timestamp;
    }

    // PRIVATE METHODS

    private OutgoingKafkaRecordMetadata<String> buildRecordMetadata(OffsetDateTime timestamp) {
        return OutgoingKafkaRecordMetadata.<String>builder()
                .withKey(UUID.randomUUID().toString())
                .withHeaders(new RecordHeaders().add("timestamp", timestamp.toString().getBytes()))
                .build();
    }
}

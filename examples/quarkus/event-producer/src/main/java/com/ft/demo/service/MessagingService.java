package com.ft.demo.service;

import io.smallrye.reactive.messaging.kafka.Record;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.eclipse.microprofile.reactive.messaging.Message.LOGGER;

// Main doc: https://quarkus.io/guides/kafka
@ApplicationScoped
public class MessagingService {

    @Inject
    @Channel("data")
    Emitter<String> emitter;

    // A regarder: https://quarkus.io/guides/kafka#commit-strategies
    // A regarder: https://quarkus.io/guides/kafka#kafka-bare-clients
    // A regarder: https://quarkus.io/guides/kafka#avro-serialization
    // A regarder: https://quarkus.io/guides/kafka-schema-registry-avro
    public OffsetDateTime emitData() {
        OffsetDateTime timestamp = OffsetDateTime.now(ZoneId.of("UTC"));
        LOGGER.info("Emit data at time " + timestamp);
        OutgoingKafkaRecordMetadata<String> metadata = OutgoingKafkaRecordMetadata.<String>builder()
                .withKey(UUID.randomUUID().toString())
                .withHeaders(new RecordHeaders().add("timestamp", timestamp.toString().getBytes()))
                .build();
        Message<String> message = Message.of("test-data")
                .addMetadata(metadata);
        emitter.send(message);
        return timestamp;
    }
}

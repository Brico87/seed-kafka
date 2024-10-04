package com.ft.demo.service;

import com.ft.demo.generated.model.TestDataPayload;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.eclipse.microprofile.reactive.messaging.Message.LOGGER;

// Main doc: https://quarkus.io/guides/kafka
@ApplicationScoped
public class MessagingService {

    // A regarder: https://quarkus.io/guides/kafka#commit-strategies
    // A regarder: https://quarkus.io/guides/kafka-schema-registry-avro
    @Incoming("data")
    public void receiveData(ConsumerRecord<String, TestDataPayload> record) {
        LOGGER.info("Received data: " + record);
    }

}

package com.ft.demo.service;

import com.ft.demo.TestDataPayload;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;

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

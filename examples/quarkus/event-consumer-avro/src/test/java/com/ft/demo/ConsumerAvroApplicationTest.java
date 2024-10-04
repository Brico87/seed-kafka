package com.ft.demo;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ConsumerAvroApplicationTest {

    @Inject
    ConsumerAvroApplication application;

    @Test
    void test() {
        // NO TESTS YET
    }
}

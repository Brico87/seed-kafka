package com.ft.demo;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class ProducerOutboxApplication {

    public static void main(String ... args) {
        System.out.println("Running main method");
        Quarkus.run(args);
    }
}

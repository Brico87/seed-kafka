package com.ft.demo;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import java.util.stream.Stream;

@QuarkusMain
public class MyMessagingApplication {

    public static void main(String ... args) {
        System.out.println("Running main method");
        Quarkus.run(args);
    }
}

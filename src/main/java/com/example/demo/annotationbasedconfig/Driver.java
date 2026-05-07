package com.example.demo.annotationbasedconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Driver {
    private Engine engine;

    //we can use @Primary annotation on one of the Engine implementations to avoid using @Qualifier here
    @Autowired
    public Driver(@Qualifier("petrolEngine") Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
        System.out.println("Driving the vehicle...");
    }
}

package com.example.demo.beanlifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "user2")
public class User {

    @Autowired
    Order order;

        public User() {
            System.out.println("Initializing User bean...");
        }

        @PostConstruct
        public void init() {
            System.out.println("User bean initialized with Order dependency: " + order);
        }

        @PreDestroy
        public void cleanup() {
            System.out.println("Cleaning up User bean...");
        }
}

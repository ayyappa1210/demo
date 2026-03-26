package com.example.demo.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "nosql", name = "enabled", havingValue = "true", matchIfMissing = false)
public class NoSqlConnection {

    public NoSqlConnection() {
        System.out.println("NoSQL Connection established.");
    }
}

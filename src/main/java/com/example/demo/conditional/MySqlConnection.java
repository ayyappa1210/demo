package com.example.demo.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "mysql", name = "enabled", havingValue = "true", matchIfMissing = false)
public class MySqlConnection {

    public MySqlConnection() {
        System.out.println("MySQL Connection established.");
    }
}

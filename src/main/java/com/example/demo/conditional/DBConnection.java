package com.example.demo.conditional;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBConnection {

    @Autowired(required = false)
    MySqlConnection mySqlConnection;
    @Autowired(required = false)
    NoSqlConnection noSqlConnection;

    @PostConstruct
    public void init() {
            System.out.println("DBConnection initialized with MySQL and NoSQL connections.");
            System.out.println("MySQL Connection: " + (mySqlConnection != null ? "Available" : "Not Available"));
            System.out.println("NoSQL Connection: " + (noSqlConnection != null ? "Available" : "Not Available"));
    }
}

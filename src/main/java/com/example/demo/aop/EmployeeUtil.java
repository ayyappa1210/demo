package com.example.demo.aop;

import org.springframework.stereotype.Service;

@Service
public class EmployeeUtil {
    public void logEmployeeDetails() {
        System.out.println("Logging employee details");
    }
}

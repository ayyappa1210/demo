package com.example.demo.annotationbasedconfig;

import org.springframework.stereotype.Component;

@Component
public class PetrolEngine implements Engine{
    @Override
    public void start() {
        System.out.println("Starting petrol engine...");
    }
}

package com.example.demo.value;

public class OfflineOrder implements Order{
    @Override
    public void createOrder() {
        System.out.println("Creating offline order...");
    }
}

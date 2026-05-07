package com.example.demo.value;

public class OnlineOrder implements Order{
    @Override
    public void createOrder() {
        System.out.println("Creating online order");
    }
}

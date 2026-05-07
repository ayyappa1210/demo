package com.example.demo.javabasedconfig;

public class Traveler {
    private Vehicle vehicle;

    public Traveler(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void travel() {
        vehicle.drive();
    }
}

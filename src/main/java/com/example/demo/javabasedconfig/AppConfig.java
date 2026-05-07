package com.example.demo.javabasedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    Vehicle car() {
        return new Car();
    }

    @Bean
    Vehicle bike() {
        return new Bike();
    }

    @Bean
    Traveler traveler() {
        return new Traveler(bike());
    }




}

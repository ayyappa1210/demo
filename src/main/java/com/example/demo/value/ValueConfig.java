package com.example.demo.value;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValueConfig {

    @Bean
    Order createOrder(@Value("${isOnlineOrder}") boolean isOnlineOrder) {
        return isOnlineOrder ? new OnlineOrder() : new OfflineOrder();

    }
}

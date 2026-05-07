package com.example.demo.beanlifecycle;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class Order {
    public Order() {
        System.out.println("Inside Order constructor");
    }
}

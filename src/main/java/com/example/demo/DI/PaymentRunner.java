package com.example.demo.DI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PaymentRunner implements CommandLineRunner {

     @Autowired
    private OrderConstructor orderConstructor;
     @Autowired
     private OrderField orderField;
     @Autowired
     private OrderSetter orderSetter;

    @Override
    public void run(String... args) throws Exception {
        orderConstructor.placeOrder();
        orderField.processOrder();
        orderSetter.processOrder();

    }
}

package com.example.demo.DI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderField {

    @Autowired
    @Qualifier(value = "upiPayment")
    private PaymentService paymentService;

    public void processOrder() {
        System.out.println("Processing order...");
        paymentService.pay();
        System.out.println("Order processed using field injection");
    }
}

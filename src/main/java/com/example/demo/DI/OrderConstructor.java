package com.example.demo.DI;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderConstructor {

    private PaymentService paymentService;

    public OrderConstructor(@Qualifier(value = "creditCardPayment")PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        System.out.println("Placing order...");
        paymentService.pay();
        System.out.println("Order processed using constructor injection");
    }
}

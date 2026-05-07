package com.example.demo.DI;

import org.springframework.stereotype.Component;

@Component(value = "creditCardPayment")
public class CreditCardPayment implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Payment made using credit card.");
    }
}

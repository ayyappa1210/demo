package com.example.demo.DI;

import org.springframework.stereotype.Component;

@Component("upiPayment")
public class UpiPayment implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Payment made using UPI.");
    }
}

package com.example.demo.DI;

import org.springframework.stereotype.Component;

@Component(value = "netBankingPayment")
public class NetBankingPayment implements PaymentService{

    @Override
    public void pay() {
        System.out.println("Payment made using Net Banking");
    }
}

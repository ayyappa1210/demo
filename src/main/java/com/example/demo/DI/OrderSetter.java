package com.example.demo.DI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderSetter {

    private PaymentService paymentService;

    @Autowired
    @Qualifier(value = "netBankingPayment")
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processOrder() {
        System.out.println("Processing order...");
        paymentService.pay();
        System.out.println("Order processed using setter injection");
    }
}

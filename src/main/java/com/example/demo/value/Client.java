package com.example.demo.value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
public class Client {

    @Autowired
    Order order;

    @PostMapping(path = "/createOrder")
   public ResponseEntity<String>  createOrder() {
        order.createOrder();
        return ResponseEntity.ok("Order created successfully");
    }

}

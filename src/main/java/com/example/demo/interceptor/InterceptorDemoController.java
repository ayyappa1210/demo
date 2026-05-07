package com.example.demo.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InterceptorDemoController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println(">>> Inside Controller: hello() method");
        return "Hello from Interceptor Demo!";
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        System.out.println(">>> Inside Controller: greet() method for " + name);
        return "Hello " + name + "! This request was intercepted.";
    }

    @GetMapping("/process")
    public String process(@RequestParam(defaultValue = "default") String message) {
        System.out.println(">>> Inside Controller: process() method with message: " + message);

        // Simulate some processing
        try {
            Thread.sleep(500); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Processed: " + message;
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String data) {
        System.out.println(">>> Inside Controller: submit() method with data: " + data);
        return "Data submitted successfully: " + data;
    }

    @GetMapping("/error")
    public String causeError() throws Exception {
        System.out.println(">>> Inside Controller: causeError() method");
        throw new Exception("Intentional error to test exception handling in interceptor");
    }
}


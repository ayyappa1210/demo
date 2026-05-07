package com.example.demo.beanscope;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "user3")
@Scope("prototype")
public class User {

        public User() {
            System.out.println("User object created");
        }

        @PostConstruct
        public void init() {
            System.out.println("user bean hascode: " + this.hashCode());
        }

}

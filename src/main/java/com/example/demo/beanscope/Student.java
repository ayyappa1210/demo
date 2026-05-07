package com.example.demo.beanscope;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    @Autowired
    User user;

        public Student() {
            System.out.println("Student object created");
        }

        @PostConstruct
        public void displayUserInfo() {
            System.out.println("Student's User hashcode: " + user.hashCode()+ " Student hashcode: " + this.hashCode());
        }


}

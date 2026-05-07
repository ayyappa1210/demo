package com.example.demo.beanscope;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestController1 {

    @Autowired
    User user;

    @Autowired
    Student student;

    public TestController1() {
        System.out.println("TestController1 instance initialized");
    }

    @PostConstruct
    public void init() {
        System.out.println("TestController1 "+this.hashCode() +" "+this.user.hashCode());
    }

    @GetMapping(path = "getUserInfo1")
    public ResponseEntity<String> getUserInfo() {
        System.out.println("Handling request in TestController1...");
        return ResponseEntity.status(HttpStatus.OK).body("");
    }


}

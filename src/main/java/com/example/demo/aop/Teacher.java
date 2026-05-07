package com.example.demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@RestController
@RequestMapping(path = "/api")
public class Teacher {

    @Autowired
    EmployeeUtil employeeUtil;

     public Teacher(EmployeeUtil employeeUtil) {
        this.employeeUtil = employeeUtil;
    }

    @GetMapping(path = "/teacherInfo")
    public String getTeacherInfo() {
        System.out.println("Executing getTeacherInfo method...");
        return "Teacher information";
    }

    @GetMapping(path = "/Info")
    public String fetchTeacherInfo() {
        System.out.println("Executing fetchTeacherInfo method...");
        employeeUtil.logEmployeeDetails();
        return "fetching Teacher information";
    }


}

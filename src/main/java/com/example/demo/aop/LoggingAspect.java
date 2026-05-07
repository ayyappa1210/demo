package com.example.demo.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.demo.aop.Teacher.getTeacherInfo(..))")
    public void logBefore() {
        System.out.println("Logging before method execution...");
    }

    @After("execution(* com.example.demo.aop.Teacher.getTeacherInfo(..))")
    public void logAfter() {
        System.out.println("Logging after method execution...");
    }

    @Before("@within(org.springframework.stereotype.Service)")
    public void logbeforeEmployee() {
        System.out.println("Logging before employee method execution...");
    }


}

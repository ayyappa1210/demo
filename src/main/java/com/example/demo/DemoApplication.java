package com.example.demo;

import com.example.demo.annotationbasedconfig.Driver;
import com.example.demo.javabasedconfig.Traveler;
import com.example.demo.stereotype.DemoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context=SpringApplication.run(DemoApplication.class, args);

		//Java based configuration
		Traveler t=(Traveler)context.getBean("traveler");
		t.travel();
		String appName=context.getApplicationName();
		System.out.println(appName);
		String beans[]=context.getBeanDefinitionNames();
		for(String bean:beans) {
			System.out.println(bean);
		}
		int count=context.getBeanDefinitionCount();
		System.out.println("Total Beans: "+count);

		//Annotation based configuration
		Driver d=(Driver)context.getBean("driver");
		d.drive();

		//Stereotype annotations: @Component, @Service, @Repository, @Controller

		DemoController demo=(DemoController)context.getBean("demoController");
		demo.handleRequest();



	}

}

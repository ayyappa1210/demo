package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /**
     * Alternative way to register filters programmatically with explicit ordering
     * This approach gives more control over filter registration
     */

    // Note: The @Order annotation on the filter classes takes precedence
    // But you can also use FilterRegistrationBean for more control

    @Bean
    public FilterRegistrationBean<ThirdFilter> thirdFilterRegistration() {
        FilterRegistrationBean<ThirdFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new ThirdFilter());
        registration.addUrlPatterns("/api/*"); // Only apply to /api/* paths
        registration.setOrder(3); // Explicit order setting
        registration.setName("thirdFilter");
        return registration;
    }

    @Bean
    public FilterRegistrationBean<FourthFilter> fourthFilterRegistration() {
        FilterRegistrationBean<FourthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new FourthFilter());
        registration.addUrlPatterns("/api/*"); // Only apply to /api/* paths
        registration.setOrder(4); // Explicit order setting
        registration.setName("fourthFilter");
        return registration;
    }
}


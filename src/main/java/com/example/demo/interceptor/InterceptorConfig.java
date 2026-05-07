package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * Register custom interceptors
     * This method is called during Spring configuration to register interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register the custom interceptor for all paths
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/**") // Apply to all paths
                .excludePathPatterns("/css/**", "/js/**", "/images/**"); // Exclude static resources if needed
    }
}


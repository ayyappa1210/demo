package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1) // This filter will execute first
public class FirstFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=== FIRST FILTER: INITIALIZED ===");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("\n=== FIRST FILTER: DO FILTER ===");
        System.out.println("Request URL: " + httpRequest.getRequestURI());
        System.out.println("Request Method: " + httpRequest.getMethod());
        System.out.println("Filter Order: 1 (First)");

        // Add custom header to track filter execution
        httpRequest.setAttribute("firstFilterExecuted", System.currentTimeMillis());

        // Add response header
        httpResponse.setHeader("X-First-Filter", "Executed");

        System.out.println("--- First filter processing complete, passing to next filter ---");

        // Continue the chain
        chain.doFilter(request, response);

        System.out.println("=== FIRST FILTER: RESPONSE PROCESSING ===");
        System.out.println("Response Status: " + httpResponse.getStatus());
        System.out.println("--- First filter response processing complete ---");
    }

    @Override
    public void destroy() {
        System.out.println("=== FIRST FILTER: DESTROYED ===");
    }
}

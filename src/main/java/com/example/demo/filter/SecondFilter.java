package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2) // This filter will execute second
public class SecondFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=== SECOND FILTER: INITIALIZED ===");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("\n=== SECOND FILTER: DO FILTER ===");
        System.out.println("Request URL: " + httpRequest.getRequestURI());
        System.out.println("Request Method: " + httpRequest.getMethod());
        System.out.println("Filter Order: 2 (Second)");

        // Check if first filter was executed
        Long firstFilterTime = (Long) httpRequest.getAttribute("firstFilterExecuted");
        if (firstFilterTime != null) {
            System.out.println("First filter executed " + (System.currentTimeMillis() - firstFilterTime) + "ms ago");
        }

        // Add custom header to track filter execution
        httpRequest.setAttribute("secondFilterExecuted", System.currentTimeMillis());

        // Add response header
        httpResponse.setHeader("X-Second-Filter", "Executed");

        System.out.println("--- Second filter processing complete, passing to next in chain ---");

        // Continue the chain
        chain.doFilter(request, response);

        System.out.println("=== SECOND FILTER: RESPONSE PROCESSING ===");
        System.out.println("Response Status: " + httpResponse.getStatus());
        System.out.println("--- Second filter response processing complete ---");
    }

    @Override
    public void destroy() {
        System.out.println("=== SECOND FILTER: DESTROYED ===");
    }
}


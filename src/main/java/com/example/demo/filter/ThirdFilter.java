package com.example.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ThirdFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("=== THIRD FILTER: INITIALIZED ===");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("\n=== THIRD FILTER: DO FILTER ===");
        System.out.println("Request URL: " + httpRequest.getRequestURI());
        System.out.println("Filter Order: 3 (Third - Programmatic Registration)");

        // Add response header
        httpResponse.setHeader("X-Third-Filter", "Executed");

        System.out.println("--- Third filter processing complete ---");

        // Continue the chain
        chain.doFilter(request, response);

        System.out.println("=== THIRD FILTER: RESPONSE PROCESSING ===");
    }

    @Override
    public void destroy() {
        System.out.println("=== THIRD FILTER: DESTROYED ===");
    }
}


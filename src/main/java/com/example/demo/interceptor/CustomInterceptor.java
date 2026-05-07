package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomInterceptor implements HandlerInterceptor {

    /**
     * Called BEFORE the controller is invoked
     * Return true to continue the request, false to stop it
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=== INTERCEPTOR: PRE-HANDLE ===");
        System.out.println("Request URL: " + request.getRequestURI());
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Handler: " + handler.getClass().getName());

        // Add custom header tracking
        request.setAttribute("startTime", System.currentTimeMillis());
        
        System.out.println("--- Request about to reach controller ---");
        return true; // Continue the request
    }

    /**
     * Called AFTER the controller is invoked but BEFORE the view is rendered
     * Use this to modify the ModelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("\n=== INTERCEPTOR: POST-HANDLE ===");
        System.out.println("Controller processing completed");
        System.out.println("Response Status: " + response.getStatus());
        System.out.println("--- Controller execution finished, view about to render ---");
    }

    /**
     * Called AFTER the view is rendered or response is sent
     * Useful for cleanup operations
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;
        
        System.out.println("\n=== INTERCEPTOR: AFTER-COMPLETION ===");
        System.out.println("Total Request Duration: " + duration + "ms");
        
        if (ex != null) {
            System.out.println("Exception occurred: " + ex.getMessage());
        } else {
            System.out.println("Request completed successfully");
        }
        System.out.println("--- Request-response cycle complete ---\n");
    }
}


package com.example.demo.stereotype;

import org.springframework.stereotype.Controller;

@Controller
public class DemoController {
        private final DemoService demoService;

        public DemoController(DemoService demoService) {
            this.demoService = demoService;
        }

        public void handleRequest() {
            System.out.println("Handling request in DemoController...");
            demoService.performService();
        }
}

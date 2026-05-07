package com.example.demo.stereotype;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public void performService() {
        System.out.println("Performing service in DemoService...");
        demoRepository.saveData("Sample data");
    }
}

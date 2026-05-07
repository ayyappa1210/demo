package com.example.demo.stereotype;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {
    public void saveData(String sampleData) {

        System.out.println("Saving data: " + sampleData);
    }
}

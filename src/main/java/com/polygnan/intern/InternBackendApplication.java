package com.polygnan.intern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class InternBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternBackendApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "Clean Intern Backend is running successfully!";
    }
}
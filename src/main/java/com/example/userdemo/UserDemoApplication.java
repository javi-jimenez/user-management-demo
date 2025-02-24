package com.example.userdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.userdemo.*")
public class UserDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserDemoApplication.class, args);
    }
}
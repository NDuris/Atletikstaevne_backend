package com.example.atletikstaevne_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.atletikstaevne_backend.models"})
public class AtletikstaevneBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtletikstaevneBackendApplication.class, args);
    }

}

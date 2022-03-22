package com.example.check;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheckApplication.class, args);
    }

}

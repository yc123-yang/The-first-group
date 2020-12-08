package com.sicnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//开启定时注解
@EnableScheduling
public class ScientificmangementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScientificmangementApplication.class, args);
    }

}

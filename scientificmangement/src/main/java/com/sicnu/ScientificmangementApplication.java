package com.sicnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling//开启定时功能
public class ScientificmangementApplication {
//0 */1 * * * ? 每  隔1分钟执行一次
    public static void main(String[] args) {
        SpringApplication.run(ScientificmangementApplication.class, args);
    }

}

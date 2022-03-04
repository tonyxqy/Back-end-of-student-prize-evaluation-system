package com.ryfl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ryfl")
@SpringBootApplication
public class RyflApplication {

    public static void main(String[] args) {
        SpringApplication.run(RyflApplication.class, args);
    }

}

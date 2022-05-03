package com.rewardsystem.honor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rewardsystem.honor.mapper")
public class HonorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HonorApplication.class, args);
    }

}

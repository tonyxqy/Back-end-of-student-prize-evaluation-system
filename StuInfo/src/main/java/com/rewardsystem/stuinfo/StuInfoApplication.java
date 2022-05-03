package com.rewardsystem.stuinfo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rewardsystem.stuinfo.mapper")
public class StuInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuInfoApplication.class, args);
    }

}

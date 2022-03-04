package com.xkjs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xkjs")
@SpringBootApplication
public class XkjsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XkjsApplication.class, args);
    }

}

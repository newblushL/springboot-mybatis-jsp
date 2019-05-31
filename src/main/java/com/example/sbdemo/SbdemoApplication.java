package com.example.sbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@MapperScan("com.example.sbdemo.dao")
public class SbdemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SbdemoApplication.class, args);
    }
}

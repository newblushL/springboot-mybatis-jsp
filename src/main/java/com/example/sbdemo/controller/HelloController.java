package com.example.sbdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloTest")
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return  "hello it is spring boot!";
    }
}

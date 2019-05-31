package com.example.sbdemo.controller;

import com.example.sbdemo.entity.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${ip}")//application.yml配置文件中名为ip的value值
    private String ip;
    @Value("${port}")
    private Integer port;
    @Value("${content}")
    private String content;

    @RequestMapping("/config")
    public String config(){
        return "ip:"+ip+","+"port:"+port;
    }

    @RequestMapping("/config2")
    public String config2(){
        return content;
    }

    @Autowired
    private ConfigBean configBean;

    @RequestMapping("/config3")
    public String config3(){
        return "ip:"+configBean.getIp()+",port:"+configBean.getPort();
    }
}

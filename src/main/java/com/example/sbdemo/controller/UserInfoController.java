package com.example.sbdemo.controller;

import com.example.sbdemo.entity.UserInfo;
import com.example.sbdemo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    private static Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/showUser")
    @ResponseBody
    public UserInfo showUserInfo(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        return this.userInfoService.findUserInfoById(id);
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "page/login";
    }
    @RequestMapping("/index")
    public String index(){
        return "page/index";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username,String password,String code){
        System.out.println(username+password+code);
        return null;
    }
}

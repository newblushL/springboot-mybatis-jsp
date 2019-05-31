package com.example.sbdemo.controller;

import com.example.sbdemo.entity.UserInfo;
import com.example.sbdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/showUser")
    @ResponseBody
    public UserInfo showUserInfo(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        return this.userInfoService.findUserInfoById(id);
    }

    @RequestMapping("/login")
    public String tologin(){
        return "page/login";
    }
}

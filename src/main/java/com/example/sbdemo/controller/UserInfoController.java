package com.example.sbdemo.controller;

import com.example.sbdemo.entity.UserInfo;
import com.example.sbdemo.exception.ReException;
import com.example.sbdemo.service.UserInfoService;
import com.example.sbdemo.utils.ResultUtil;
import com.example.sbdemo.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    public ResultUtil login(String username, String password){
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new ReException("用户名不能为空");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResultUtil.error(e.getMessage());
        } catch (IncorrectCredentialsException e){
            return ResultUtil.error(e.getMessage());
        } catch (LockedAccountException e){
            return ResultUtil.error(e.getMessage());
        } catch (AuthenticationException e){
            return ResultUtil.error("用户验证失败");
        }
        return ResultUtil.ok();
    }
}

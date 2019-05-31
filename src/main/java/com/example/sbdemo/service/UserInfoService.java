package com.example.sbdemo.service;

import com.example.sbdemo.entity.UserInfo;
import org.springframework.stereotype.Service;

public interface UserInfoService {

    UserInfo findUserInfoById(int id);
}

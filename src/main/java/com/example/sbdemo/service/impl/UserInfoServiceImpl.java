package com.example.sbdemo.service.impl;

import com.example.sbdemo.dao.UserInfoDao;
import com.example.sbdemo.entity.UserInfo;
import com.example.sbdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findUserInfoById(int id) {
        return userInfoDao.selectByPrimaryKey(id);
    }

    @Override
    public UserInfo findUserByName(String username) {
        return userInfoDao.selectUserByName(username);
    }
}

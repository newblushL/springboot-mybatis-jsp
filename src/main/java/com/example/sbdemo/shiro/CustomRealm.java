package com.example.sbdemo.shiro;

import com.example.sbdemo.entity.UserInfo;
import com.example.sbdemo.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName CustomRealm
 * @Description TODO
 * @Author LL
 * @Date 2019-06-05 14:42
 * @Version 1.0
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;
    /**
     * 权限处理
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证处理
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        UserInfo userInfo = userInfoService.findUserByName(username);
        if(userInfo == null){
            throw new UnknownAccountException("账号不存在");
        }
        if(!password.equals(userInfo.getPassword())){
            throw new IncorrectCredentialsException("密码不正确");
        }
        password = new Md5Hash(password).toString();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo,password,this.getClass().getName());
        return info;
    }
}
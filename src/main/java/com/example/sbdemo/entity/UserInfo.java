package com.example.sbdemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 6621226500564684870L;

    //用户id
    @Id
    @GeneratedValue
    private Integer id;

    //用户名
    @Column(unique = true)
    private String username;

    //名称(昵称或者真实姓名)
    private String name;

    //密码
    private String password;

    //年龄
    private Integer age;

    //加密密码的盐
    private String salt;

    //用户状态,0:创建未认证(比如没有激活,没有输入验证码等等)--等待验证的用户;1:正常状态;2:用户被锁定
    private Byte state;

    //一个用户具有多个角色
    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", salt='" + salt + '\'' +
                ", state=" + state +
                ", roleList=" + roleList +
                '}';
    }

    /**
     * 密码盐
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
package com.springboot.dao;


import com.springboot.domain.SysUser;

public interface UserDao {
    public SysUser findByUserName(String username);
}

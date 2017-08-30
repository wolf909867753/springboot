package com.springboot.dao;


import com.springboot.domain.SysUser;

/**
 * 用户dao
 */
public interface UserDao {
    public SysUser findByUserName(String username);
}

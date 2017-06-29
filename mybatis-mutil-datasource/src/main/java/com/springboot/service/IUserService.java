package com.springboot.service;

import com.springboot.domain.User;

/**
 * Created by wanglu-jf on 17/6/28.
 */
public interface IUserService {
    /**
     * 根据用户id获取用户信息，包括从库的地址信息
     * @param userId
     * @return
     */
    public User queryById(int userId);
}

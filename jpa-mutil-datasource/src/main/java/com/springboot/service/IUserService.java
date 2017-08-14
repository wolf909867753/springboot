package com.springboot.service;

import com.springboot.domain.City;
import com.springboot.domain.User;

/**
 * Created by wanglu-jf on 17/8/14.
 */
public interface IUserService {

    public void save(City city,User user);
}

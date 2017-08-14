package com.springboot.service.impl;

import com.springboot.dao.cluster.IUserDao;
import com.springboot.dao.master.ICityDao;
import com.springboot.domain.City;
import com.springboot.domain.User;
import com.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private ICityDao cityDao;

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional
    public void save(City city,User user) {
        this.userDao.save(user);
        this.cityDao.save(city);
    }
}

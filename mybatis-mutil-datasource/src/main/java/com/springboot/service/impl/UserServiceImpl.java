package com.springboot.service.impl;

import com.springboot.dao.cluster.IUserDao;
import com.springboot.dao.master.ICityDao;
import com.springboot.domain.City;
import com.springboot.domain.User;
import com.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao; // user数据源
    @Autowired
    private ICityDao cityDao; //city数据源

    /**
     * 根据用户id获取用户信息，包括从库的地址信息
     * @param id
     * @return
     */
    @Override
    public User queryById(int id) {
        User user = this.userDao.queryByUserId(id);
        City city = this.cityDao.queryById(id);
        if(null != user && null != city){
            user.setCity(city);
        }
        return user;
    }
}

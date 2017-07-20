package com.springboot.service.impl;

import com.springboot.dao.ICityDao;
import com.springboot.domain.po.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanglu-jf on 17/7/19.
 */
@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private ICityDao cityDao;
    @Override
    public City getCityById(String id) {
        return this.cityDao.getCityById(id);
    }
}

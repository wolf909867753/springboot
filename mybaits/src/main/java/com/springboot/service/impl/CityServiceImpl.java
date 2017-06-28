package com.springboot.service.impl;

import com.springboot.dao.CityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 * Created by wanglu-jf on 17/6/27.
 */
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public City queryCityByName(String cityName) {
        return this.cityDao.queryByName(cityName);
    }
}

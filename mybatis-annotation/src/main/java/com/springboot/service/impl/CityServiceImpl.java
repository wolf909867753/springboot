package com.springboot.service.impl;

import com.springboot.dao.CityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityDao cityDao;
    /**
     * 查询城市信息
     */
    @Override
    public City queryById(int id) {
        return cityDao.queryCityById(id);
    }
}

package com.springboot.service.impl;

import com.springboot.dao.CityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by wl on 2017/7/2.
 */
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public City queryById(int id) {
        return this.cityDao.queryByPrimaryKey(id);
    }

    @Override
    public void save(City city) {
        this.cityDao.save(city);
    }


}

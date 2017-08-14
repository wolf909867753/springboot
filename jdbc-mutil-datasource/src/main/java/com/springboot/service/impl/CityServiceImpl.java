package com.springboot.service.impl;

import com.springboot.dao.ICityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private ICityDao cityDao;

    @Override
    public void save(City city) {
      this.cityDao.save(city);
    }
}

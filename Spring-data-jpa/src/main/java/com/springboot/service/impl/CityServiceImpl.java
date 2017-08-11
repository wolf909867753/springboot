package com.springboot.service.impl;

import com.springboot.dao.ICityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanglu-jf on 17/8/11.
 */
@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private ICityDao cityDao;

    @Override
    public City findById(Long id) {
        return this.cityDao.findOne(id);
    }

    @Override
    @Transactional
    public City saveCity(City city) {
        return this.cityDao.save(city);
    }

    @Override
    public List<City> findAll() {
        Sort sort = new Sort("id");
        return this.cityDao.findAll(sort);
    }

    @Override
    @Transactional
    public City updateById(City city) {
        return this.cityDao.saveAndFlush(city);
    }
}

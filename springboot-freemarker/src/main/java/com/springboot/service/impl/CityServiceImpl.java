package com.springboot.service.impl;

import com.springboot.dao.CityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanglu-jf on 17/7/18.
 */
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityDao cityDao;

    public List<City> findAllCity(){
        return cityDao.findAllCity();
    }

    public City findCityById(Long id) {
        return cityDao.findById(id);
    }

    @Override
    public Long saveCity(City city) {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) {
        return cityDao.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) {
        return cityDao.deleteCity(id);
    }
}

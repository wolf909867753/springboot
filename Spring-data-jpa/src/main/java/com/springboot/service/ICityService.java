package com.springboot.service;

import com.springboot.domain.City;

import java.util.List;

/**
 * Created by wanglu-jf on 17/8/11.
 */
public interface ICityService {

    public City findById(Long id);

    public City saveCity(City city);

    public List<City> findAll();

    public City updateById(City city);
}

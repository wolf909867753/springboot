package com.springboot.service;

import com.springboot.domain.City;

/**
 * Created by wl on 2017/7/2.
 */
public interface ICityService {
    public City queryById(int id);

    public void save(City city);
}

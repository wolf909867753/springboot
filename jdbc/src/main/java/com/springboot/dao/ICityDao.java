package com.springboot.dao;

import com.springboot.domain.City;

/**
 * Created by wanglu-jf on 17/8/10.
 */
public interface ICityDao {
    /**
     * @param city
     */
    void create(City city);

    /**
     * @param cityName
     */
    void deleteBycityName(String cityName);

    /**
     * 获取City总量
     */
    Integer countAllCitys();
}

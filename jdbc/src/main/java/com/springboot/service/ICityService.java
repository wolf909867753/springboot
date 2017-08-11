package com.springboot.service;

import com.springboot.domain.City;

/**
 * 城市业务逻辑接口类
 * Created by wanglu-jf on 17/6/27.
 */
public interface ICityService {
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

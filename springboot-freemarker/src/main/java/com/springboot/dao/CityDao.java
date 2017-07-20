package com.springboot.dao;

import com.springboot.domain.City;

import java.util.List;

/**
 * Created by wanglu-jf on 17/7/18.
 */
public interface CityDao {
    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity();

    /**
     * 根据城市 ID，获取城市信息
     *
     * @param id
     * @return
     */
    City findById(@org.apache.ibatis.annotations.Param("id") Long id);

    Long saveCity(City city);

    Long updateCity(City city);

    Long deleteCity(Long id);
}

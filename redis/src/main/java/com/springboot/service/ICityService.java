package com.springboot.service;

import com.springboot.domain.City;
import com.springboot.utils.customException.CustomerException;

import java.util.List;
import java.util.Map;

/**
 * Created by wanglu-jf on 17/7/18.
 */
public interface ICityService {
    /**
     * 获取城市信息列表
     *
     * @return
     */
    List<City> findAllCity() throws CustomerException;

    List<Map<String,String>> queryAllCity() throws CustomerException;

    /**
     * 根据城市 ID,查询城市信息
     *
     * @param id
     * @return
     */
    City findCityById(Long id)  throws CustomerException;

    /**
     * 新增城市信息
     *
     * @param city
     * @return
     */
    Long saveCity(City city) throws CustomerException;

    /**
     * 更新城市信息
     *
     * @param city
     * @return
     */
    Long updateCity(City city) throws CustomerException;

    /**
     * 根据城市 ID,删除城市信息
     *
     * @param id
     * @return
     */
    Long deleteCity(Long id) throws CustomerException;
}

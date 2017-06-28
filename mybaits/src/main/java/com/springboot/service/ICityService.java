package com.springboot.service;

import com.springboot.domain.City;

/**
 * 城市业务逻辑接口类
 * Created by wanglu-jf on 17/6/27.
 */
public interface ICityService {
    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    public City queryCityByName(String cityName);
}

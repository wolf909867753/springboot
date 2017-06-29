package com.springboot.dao;

import com.springboot.domain.City;
import org.apache.ibatis.annotations.Param;

/**
 * 城市 DAO 接口类
 * Created by wanglu-jf on 17/6/27.
 */
public interface CityDao {
    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    public City queryByName(@Param("cityName") String cityName);
}

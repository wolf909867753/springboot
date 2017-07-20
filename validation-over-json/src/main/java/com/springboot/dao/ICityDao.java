package com.springboot.dao;

import com.springboot.domain.po.City;

/**
 * Created by wanglu-jf on 17/7/19.
 */
public interface ICityDao {

    public City getCityById(@org.apache.ibatis.annotations.Param("id") String id);
}

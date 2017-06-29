package com.springboot.service;

import com.springboot.domain.City;

/**
 * Created by wanglu-jf on 17/6/28.
 */
public interface ICityService {

    /**
     * 查询城市信息
     */
    public City queryById(int id);
}

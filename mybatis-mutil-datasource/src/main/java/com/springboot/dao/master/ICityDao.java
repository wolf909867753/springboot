package com.springboot.dao.master;

import com.springboot.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@Mapper
public interface ICityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityId 城市名
     */
    public City queryById(@Param("cityId") int cityId);
}

package com.springboot.dao;

import com.springboot.domain.City;
import org.apache.ibatis.annotations.*;

/**
 * 城市 DAO 接口类
 * Created by wanglu-jf on 17/6/27.
 * @Mapper 标志接口为 MyBatis Mapper 接口
 * @Select 是 Select 操作语句
 * @Results 标志结果集，以及与库表字段的映射关系
 */
@Mapper
public interface CityDao {
    /**
     * 查询城市信息
     */
    @Select(" SELECT * FROM city WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description")
    })
    public City queryCityById(@Param("id") int id);
}

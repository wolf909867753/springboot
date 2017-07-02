package com.springboot.dao;

import com.springboot.domain.City;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;


/**
 * Created by wl on 2017/7/2.
 */
public interface CityDao {
    /**
     * 根据主建查询
     * @param id
     * @return
     */
   public City queryByPrimaryKey(@Param("id") int id);

    /**
     * 保存
     * @param city
     */
    public void save(City city);
}

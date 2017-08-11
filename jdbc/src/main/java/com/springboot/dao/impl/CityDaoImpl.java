package com.springboot.dao.impl;

import com.springboot.dao.ICityDao;
import com.springboot.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by wanglu-jf on 17/8/10.
 */
@Repository
public class CityDaoImpl implements ICityDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void create(City city) {
        jdbcTemplate.update("INSERT INTO springbootdb.city (id, province_id, city_name, description) " +
                " VALUES(?,?,?,? );",
                city.getId(),city.getProvinceId(),city.getCityName(),city.getDescription());
    }

    @Override
    public void deleteBycityName(String cityName) {

    }

    @Override
    public Integer countAllCitys() {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM CITY;",Integer.class);
    }
}

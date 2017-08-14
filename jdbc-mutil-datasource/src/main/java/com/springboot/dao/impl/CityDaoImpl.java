package com.springboot.dao.impl;

import com.springboot.dao.ICityDao;
import com.springboot.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@Repository
public class CityDaoImpl implements ICityDao {

    @Autowired
    @Qualifier("masterJdbcTemplate")
    private JdbcTemplate masterJdbcTemplate;

    @Autowired
    @Qualifier("clusterJdbcTemplate")
    private JdbcTemplate clusterJdbcTemplate;

    @Override
    public void save(City city) {
        this.masterJdbcTemplate.update("INSERT INTO city (id, province_id, city_name, description) " +
                        " VALUES(?,?,?,? );",
                city.getId(),city.getProvinceId(),city.getCityName(),city.getDescription());

        this.clusterJdbcTemplate.update("INSERT INTO city (id, province_id, city_name, description) " +
                        " VALUES(?,?,?,? );",
                1L,city.getProvinceId(),city.getCityName(),city.getDescription());
    }



}

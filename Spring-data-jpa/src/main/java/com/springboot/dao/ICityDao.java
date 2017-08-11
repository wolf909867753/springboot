package com.springboot.dao;

import com.springboot.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wanglu-jf on 17/8/11.
 */
@Repository
public interface ICityDao extends JpaRepository<City,Long> /*org.springframework.data.repository.Repository<City,Long>*/{


}
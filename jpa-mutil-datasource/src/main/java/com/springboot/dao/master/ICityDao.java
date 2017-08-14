package com.springboot.dao.master;

import com.springboot.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wanglu-jf on 17/8/14.
 */
public interface ICityDao extends JpaRepository<City,Long> {
}

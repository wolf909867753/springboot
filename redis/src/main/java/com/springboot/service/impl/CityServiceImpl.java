package com.springboot.service.impl;

import com.springboot.dao.CityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import com.springboot.service.IRedisService;
import com.springboot.utils.customException.CustomerException;
import com.springboot.utils.customException.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by wanglu-jf on 17/7/18.
 */
@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private IRedisService redisService;

    private static final String cacheKey = "CITY_";

    @Override
    public List<City> queryAllCity() throws CustomerException{
        List<City> allCity = cityDao.findAllCity();
        return allCity;
    }

    @Override
    public City findCityById(Long id)  throws CustomerException {
        City city = redisService.getCity(cacheKey + id);
        if(StringUtils.isEmpty(city)){
            city = cityDao.findById(id);
            if(StringUtils.isEmpty(city)){
                throw new CustomerException(ExceptionEnum.NO_FOUND_DATA.getCode(),ExceptionEnum.NO_FOUND_DATA.getMessage());
            }
            redisService.setCity(cacheKey+id,city);
        }
        return city;
    }

    @Override
    public Long saveCity(City city) throws CustomerException {
        return cityDao.saveCity(city);
    }

    @Override
    public Long updateCity(City city) throws CustomerException {
        return cityDao.updateCity(city);
    }

    @Override
    public Long deleteCity(Long id) throws CustomerException {
        return cityDao.deleteCity(id);
    }
}

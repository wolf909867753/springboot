package com.springboot.service.impl;

import com.springboot.dao.CityDao;
import com.springboot.domain.City;
import com.springboot.service.ICityService;
import com.springboot.service.IRedisService;
import com.springboot.utils.customException.CustomerException;
import com.springboot.utils.customException.ExceptionEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * Created by wanglu-jf on 17/7/18.
 */
@Service
public class CityServiceImpl implements ICityService {

    private static Logger logger = Logger.getLogger(CityServiceImpl.class);


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
    public List<City> findAllCity() throws CustomerException{
        List<City> allCity = cityDao.findAllCity();
        return allCity;
    }

    @Override
    public List<Map<String, String>> queryAllCity() throws CustomerException {
        List<Map<String, String>> results = null;
        Long size = this.redisService.zSetSize(cacheKey + "PC");
        if(0 == size){
            long currentStartTime = System.currentTimeMillis();
            logger.info(">>>>>>>>>>>>>currentStartTime:"+ currentStartTime);
            boolean flag = this.redisService.tryLock(cacheKey);
            if(flag){
                try {
                    results = this.cityDao.queryAllCity();
                    if(null == results || results.isEmpty()){
                        throw new CustomerException(ExceptionEnum.NO_FOUND_DATA.getCode(),ExceptionEnum.NO_FOUND_DATA.getMessage());
                    }
                    Map<String,Double> map = new HashMap<String,Double>();
                    for(int i = 0;i<results.size();i++){
                        Map<String, String> result = results.get(i);
                        String id = String.valueOf(result.get("id"));
                        map.put(String.valueOf(id),Double.valueOf(i));
                        this.redisService.hmSet(cacheKey + id, result);
                    }
                    this.redisService.zadd(cacheKey + "PC",map);
                    long currentEndTime = System.currentTimeMillis();
                    logger.info(">>>>>>>>>>>>>currentEndTime:"+ currentEndTime + ">>>>sub:"+ (currentEndTime-currentStartTime));
                }catch (Exception e){
                    logger.error("=====>>> 刷新缓存异常！", e);
                }finally {
                    this.redisService.unLock(cacheKey);
                }
            }
        }else{
            long currentStartTime = System.currentTimeMillis();
            logger.info(">>>>>>>>>>>>>currentStartTime:"+ currentStartTime);
            Set<String> set = this.redisService.zRange(cacheKey + "PC", 0, -1);
            results = new ArrayList<Map<String, String>>();
            if(null != set){
                for(String str : set){
                    Map<String, String> map = this.redisService.hGetAll(cacheKey + str);
                    results.add(map);
                }
            }
            long currentEndTime = System.currentTimeMillis();
            logger.info(">>>>>>>>>>>>>currentEndTime:"+ currentEndTime + ">>>>sub:"+ (currentEndTime-currentStartTime));
        }
        return results;
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

package com.springboot.service;

import com.springboot.domain.City;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Created by wanglu-jf on 17/7/21.
 */
public interface IRedisService {
    public Jedis getResource();

    public void close(Jedis jedis);

    public void set(String key, String value);

    public String get(String key);

    //==========================下面两个使用Protostuff start序列化==========
    public void setCity(String key,City city);

    public City getCity(String key);

    //==========================下面两个使用Protostuff end=================

    public void hmSet(String key,Map<String,String> map);

    public void hmGet(String key,String...field);

    public Map<String, String> hGetAll(String key);
}

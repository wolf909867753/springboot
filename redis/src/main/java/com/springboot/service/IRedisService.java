package com.springboot.service;

import com.springboot.domain.City;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    public Long zSetSize(String key);

    public void zadd(String s, Map<String,Double> map);

    public Set<String> zRange(String key, int start, int end);


/*===========================redis lock start=====================================*/

    /**
     * 如果锁空闲立即返回，否则阻塞等待
     * @param key
     */
    public void lock(String key);

    /**
     * 获取锁,获取立即返回true，否则返回false
     * @param key
     * @return
     */
    public boolean tryLock(String key);

    /**
     * 等待超时前获取锁成功 返回true， 否则返回false
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public boolean tryLock(String key, long timeout, TimeUnit unit);

    /**
     * 批量获取锁，全部获取成功返回true,否则返回false
     * @param keys
     * @return
     */
    public boolean tryLock(List<String> keys);

    /**
     * 等待超时前获取锁成功 返回true， 否则返回false
     * @param keys
     * @param timeout
     * @param unit
     * @return
     */
    public boolean tryLock(List<String> keys, long timeout, TimeUnit unit);


    /**
     * 释放锁
     * @param key
     */
    public boolean unLock(String key);
    /**
     * 批量释放锁
     * @param keys
     */
    public void unLock(List<String> keys);


/*===========================redis lock end=====================================*/
}

package com.springboot.service.impl;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.springboot.domain.City;
import com.springboot.service.IRedisService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Created by wanglu-jf on 17/7/21.
 */
@Service
public class RedisServiceImpl implements IRedisService {
    private static Logger logger = Logger.getLogger(RedisServiceImpl.class);

//    @Autowired
//    private RedisConfig redisConfig;

    @Autowired
    private JedisPool jedisPool;

    private RuntimeSchema<City> sechema = RuntimeSchema.createFrom(City.class);

    @Override
    public Jedis getResource() {
        return jedisPool.getResource();
//        return redisConfig.getJedis();
    }

    @Override
    public void close(Jedis jedis) {
        if(jedis != null){
            jedis.close();
        }
    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            close(jedis);
        }
    }

    @Override
    public String get(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            close(jedis);
        }
        return result;
    }

    @Override
    public void setCity(String key,City city){
        Jedis jedis = null;
        try{
            jedis = getResource();
            //set  object(seckill) --> 序列化-->byte[]
            byte[] bytes = ProtostuffIOUtil.toByteArray(city, sechema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            jedis.set(key.getBytes(), bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            close(jedis);
        }
    }

    @Override
    public City getCity(String key){
        Jedis jedis=null;
        try{
            jedis = getResource();
            byte[] bytes = jedis.get(key.getBytes());
            //自定义序列化 get ->byte[]->反序列化 ->object(City)
            if(null != bytes){
                City city = sechema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, city, sechema);
                return city;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            close(jedis);
        }
        return null;
    }

    @Override
    public void hmSet(String key,Map<String,String> map){
        Jedis jedis=null;
        try {
            jedis = getResource();
            jedis.hmset(key,map);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(jedis);
        }
    }

    @Override
    public void hmGet(String key,String...field){

    }

    @Override
    public Map<String, String> hGetAll(String key){
        Jedis jedis=null;
        try {
            jedis = getResource();
            Map<String, String> map = jedis.hgetAll(key);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return null;
    }
}

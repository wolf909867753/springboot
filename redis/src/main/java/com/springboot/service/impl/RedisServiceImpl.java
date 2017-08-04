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
import redis.clients.jedis.Tuple;

import java.util.*;
import java.util.concurrent.TimeUnit;

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

    @Override
    public Long zSetSize(String key){
        Jedis jedis=null;
        try {
            jedis = getResource();
            return jedis.zcard(key);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return null;
    }

    public void zadd(String key, Map<String,Double> map){
        Jedis jedis=null;
        try {
            jedis = getResource();
            jedis.zadd(key,map);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(jedis);
        }
    }

    @Override
    public Set<String> zRange(String key, int start, int end){
        Jedis jedis=null;
        Set<String> set = new LinkedHashSet<String>();
        try {
            jedis = getResource();
//            String script = "local result={} " +
////                            " for i,v in ipairs(KEYS) do " +
//                            " result = redis.call('zrange',KEYS[1],0,-1) " +
////                            " end " +
//                            " return result ";
//
//            List<String> value = (ArrayList<String>)jedis.eval(script, 1, key);
//            logger.info(">>>>>>>value is "+value);
//            if(null != value){
//                set.addAll(value);
//            }

            Set<Tuple> tuples = jedis.zrangeWithScores(key, start, end);
            Iterator<Tuple> iterator = tuples.iterator();
            while(iterator.hasNext()){
                Tuple next = iterator.next();
                String element = next.getElement();
                double score = next.getScore();
                logger.info(">>>>>>>>>>>>[element:"+ element +"====score:"+ score +"]");
                set.add(element);
            }
            return set;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return null;
    }



  /*===========================redis lock start=====================================*/
    // 单个锁有效期
    private static final int DEFAULT_SINGLE_EXPIRE = 100;
    // 批量锁有效期
    private static final int DEFAULT_BATCH_EXPIRE = 100;
    // 尝试间隔时间
    private static int sleep = 100;

    @Override
    public void lock(String key) {

    }

    @Override
    public boolean tryLock(String key) {
        return tryLock(key, 0L, null);
    }

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        Jedis jedis=null;
        try {
            jedis = getResource();
            //系统计时器的当前值，以毫微秒为单位。
//            long nano = System.nanoTime();
            long nano = System.currentTimeMillis();
            logger.info(">>>>>>>>[系统计时器的当前值，以毫微秒为单位:"+ nano +"]");
            do {
                logger.debug("try lock key: " + key);
                Long setnx = jedis.setnx(key, key);
                //设置key占位，成功返回ture
                if(1 == setnx){
//                if (redisClient.opsForValue().setIfAbsent(key, key)) {
                    //设置锁失效时间
                    jedis.expire(key, DEFAULT_SINGLE_EXPIRE);
//                    redisClient.expire(key, DEFAULT_SINGLE_EXPIRE, TimeUnit.SECONDS);
                    logger.debug("get lock, key: " + key + " , expire in " + DEFAULT_SINGLE_EXPIRE + " seconds.");
                    //成功获取锁，返回true
                    return Boolean.TRUE;
                } else { // 存在锁,循环等待锁
                    if (logger.isDebugEnabled()) {
                        String desc = jedis.get(key);
//                        String desc = (String) redisClient.opsForValue().get(key);
                        logger.debug("key: " + key + " locked by another business：" + desc);
                    }
                }
                if (timeout <= 0) {
                    //没有设置超时时间，直接退出等待
                    break;
                }
                Thread.currentThread().sleep(sleep);
            } while ((System.currentTimeMillis() - nano) < unit.toMillis(timeout));
        } catch (Exception e) {
            logger.error("获取锁异常！", e);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean tryLock(List<String> keys) {
        return tryLock(keys, 0l, null);
    }

    @Override
    public boolean tryLock(List<String> keys, long timeout, TimeUnit unit) {
        Jedis jedis=null;
        try {
            jedis = getResource();
            //需要的锁
//            Map<String, String> mkeys = new HashMap<>();
            long nano = System.nanoTime();
//            for (String key : keys) {
//                mkeys.put(key, key);
//            }
            do {
                logger.debug("try lock keys: " + keys);
                // 提交redis执行计数,批量处理完成返回
                if(0 == jedis.msetnx((String[])keys.toArray())){
                    for (String key : keys) {
                        //设置锁失效时间
//                        redisClient.expire(key, DEFAULT_BATCH_EXPIRE, TimeUnit.SECONDS);
                        jedis.expire(key,DEFAULT_BATCH_EXPIRE);
                    }
                    return Boolean.TRUE;
                }else{
                    // 资源占用未成功
                    logger.debug("keys: " + keys + " locked by another business：");
                }
//                if (redisClient.opsForValue().multiSetIfAbsent(mkeys)) {
//                    for (String key : keys) {
//                        //设置锁失效时间
//                        redisClient.expire(key, DEFAULT_BATCH_EXPIRE, TimeUnit.SECONDS);
//                    }
//
//                    return Boolean.TRUE;
//                } else {
//                    // 资源占用未成功
//                    logger.debug("keys: " + keys + " locked by another business：");
//                }

                if (timeout <= 0) {
                    break;
                }
                Thread.currentThread().sleep(sleep);
            } while ((System.nanoTime() - nano) < unit.toNanos(timeout));

        }  catch (Exception e) {
            logger.error("批量获取锁异常！", e);
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean unLock(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.del(key);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
        }
//        redisClient.delete(key);
        return Boolean.FALSE;
    }

    @Override
    public void unLock(List<String> keys) {
//        redisClient.delete(keys);
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.del((String[])keys.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        redisClient.delete(key);
    }

      /*===========================redis lock end=====================================*/



}

package com.springboot.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig{
    private static Logger logger = Logger.getLogger(RedisConfig.class);

    @Value("${spring.redis.database:0}")
    private String database;

    @Value("${spring.redis.host:192.168.179.131}")
    private String hostName;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.pool.max-active:8}")
    private int maxActive;

    @Value("${spring.redis.pool.max-wait:-1}")
    private int maxWait;

    @Value("${spring.redis.pool.max-idle:8}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle:0}")
    private int minIdle;

    @Value("${spring.redis.timeout}")
    private int timeout;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 初始化Redis连接池
     * @return
     */
    @Bean
    public JedisPool getJedisPoolConfig(){
        JedisPool jedisPool = null;
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setMinIdle(minIdle);
            config.setTestOnBorrow(true);
            //(GenericObjectPoolConfig poolConfig, String host, int port, int timeout, String password)
            jedisPool = new JedisPool(config,hostName,port);
        } catch (Exception e) {
            logger.info(">>>>>>>>>>>>>create jedis pool fail");
            e.printStackTrace();
        }
        return jedisPool;
    }

    /**
     * 获取Jedis实例
     */
    @Bean("jedis")
    public synchronized Jedis getJedis(){
        Jedis resource = null;
        try {
            JedisPool jedisPool = getJedisPoolConfig();
            resource = jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resource;
    }
}
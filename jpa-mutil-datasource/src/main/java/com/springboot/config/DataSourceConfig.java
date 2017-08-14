package com.springboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "clusterDataSource")
    @Qualifier("clusterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    public DataSource clusterDataSource(){
        return DataSourceBuilder.create().build();
    }
}

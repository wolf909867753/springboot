package com.springboot.datasource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * Created by wanglu-jf on 17/8/14.
 */
@Configuration
public class JdbcDataSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(JdbcDataSourceConfig.class);

    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource(){
        logger.info("[masterDataSource init...]");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterJdbcTemplate")
    public JdbcTemplate masterJdbcTemplate(@Qualifier("masterDataSource") DataSource dataSource) {
        logger.info("[masterDatasource JdbcTemplate init...]");
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "clusterDataSource")
    @Qualifier("clusterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    public DataSource clusterDataSource(){
        logger.info("[clusterDataSource init...]");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "clusterJdbcTemplate")
    public JdbcTemplate clusterJdbcTemplate(@Qualifier("clusterDataSource") DataSource dataSource) {
        logger.info("[clusterDatasource JdbcTemplate init...]");
        return new JdbcTemplate(dataSource);
    }


}

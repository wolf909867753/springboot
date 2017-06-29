package com.springboot.dataSourceConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@Configuration
@MapperScan(basePackages = ClusterConfig.PACKAGE,sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterConfig {

    public static final String PACKAGE = "com.springboot.dao.cluster";

    public static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

//    cluster.datasource.driver-class-name = com.mysql.jdbc.Driver
//    cluster.datasource.url = jdbc:mysql://localhost:3306/springbootdb_cluster?useUnicode=true&characterEncoding=utf8
//    cluster.datasource.username = root
//    cluster.datasource.password = root

    @Value("${cluster.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${cluster.datasource.url}")
    private String url;

    @Value("${cluster.datasource.username}")
    private String username;

    @Value("${cluster.datasource.password}")
    private String password;

    //初始化数据库连接
    @Bean(name="clusterDataSource")
    public DataSource clusterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //数据源事务管理器
    @Bean(name="clusterDataSourceTransactionManager")
    public DataSourceTransactionManager clusterDataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(clusterDataSource());
        return dataSourceTransactionManager;
    }

    //创建Session
    @Bean(name="clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(clusterDataSource);
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(ClusterConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean.getObject();
    }
}

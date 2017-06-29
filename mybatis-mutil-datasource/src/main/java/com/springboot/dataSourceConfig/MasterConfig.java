package com.springboot.dataSourceConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by wanglu-jf on 17/6/28.
 */
@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MasterConfig.PACKAGE,sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterConfig {

    //master dao 所在的包
    public static final String PACKAGE = "com.springboot.dao.master";

    //mapper所在目录
    private static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

//    master.datasource.driver-class-name = com.mysql.jdbc.Driver
//    master.datasource.url = jdbc:mysql://localhost:3306/springbootdb?useUnicode=true&characterEncoding=utf8
//    master.datasource.username = root
//    master.datasource.password = root

    @Value("${master.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String username;

    @Value("${master.datasource.password}")
    private String password;

    //初始化数据库连接
    @Bean(name="masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    //数据源事务管理器
    @Bean(name="masterDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager masterDataSourceTransactionManager(){
        return new DataSourceTransactionManager(masterDataSource());
    }

    //创建Session
    @Bean(name="masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterDataSource);
        //MapperLocations(Resource[] mapperLocations)
        Resource[] mapperLocations = new PathMatchingResourcePatternResolver().getResources(MasterConfig.MAPPER_LOCATION);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        return sqlSessionFactoryBean.getObject();
    }


}

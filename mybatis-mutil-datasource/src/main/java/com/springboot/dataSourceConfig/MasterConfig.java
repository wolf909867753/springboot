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
 * @Primary 标志这个 Bean 如果在多个同类 Bean 候选时，该 Bean 优先被考虑。「多数据源配置的时候注意，必须要有一个主数据源，用 @Primary 标志该 Bean」
 * @MapperScan 扫描 Mapper 接口并容器管理，包路径精确到 master，为了和下面 cluster 数据源做到精确区分
 * @Value 获取全局配置文件 application.properties 的 kv 配置,并自动装配
 * sqlSessionFactoryRef 表示定义了 key ，表示一个唯一 SqlSessionFactory 实例
 *
 * 上面数据配置分别扫描 Mapper 接口，org.spring.springboot.dao.master（对应 xml classpath:mapper/master ） 和 org.spring.springboot.dao.cluster（对应 xml classpath:mapper/cluster ） 包中对应的 UserDAO 和 CityDAO 。
 * 都有 @Mapper 标志为 Mybatis 的并通过容器管理的 Bean。Mybatis 内部会使用反射机制运行去解析相应 SQL。
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

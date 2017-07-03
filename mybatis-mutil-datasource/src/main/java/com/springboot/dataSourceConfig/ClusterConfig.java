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

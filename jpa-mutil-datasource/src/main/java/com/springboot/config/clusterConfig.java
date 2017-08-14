package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by wanglu-jf on 17/8/14.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="clusterEntityManagerFactory",
        transactionManagerRef="clusterTransactionManager",
        basePackages= { "com.springboot.dao.cluster" })
public class clusterConfig {

    @Autowired
    @Qualifier("clusterDataSource")
    private DataSource clusterDataSource;


    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "clusterTransactionManager")
    public PlatformTransactionManager clusterTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(clusterEntityManagerFactory(builder).getObject());
    }

    @Bean(name = "clusterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean clusterEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(clusterDataSource)
                .properties(getVendorProperties(clusterDataSource))
                .packages("com.springboot.domain") //设置实体类所在位置
                .persistenceUnit("clusterPersistenceUnit")
                .build();
    }

    @Bean(name = "clusterEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return clusterEntityManagerFactory(builder).getObject().createEntityManager();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
}

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
        entityManagerFactoryRef="masterEntityManagerFactory",
        transactionManagerRef="masterTransactionManager",
        basePackages= { "com.springboot.dao.master" })
public class MasterConfig {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;


    @Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "masterTransactionManager")
    public PlatformTransactionManager masterTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(masterEntityManagerFactory(builder).getObject());
    }

    @Primary
    @Bean(name = "masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(masterDataSource)
                .properties(getVendorProperties(masterDataSource))
                .packages("com.springboot.domain") //设置实体类所在位置
                .persistenceUnit("masterPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "masterEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return masterEntityManagerFactory(builder).getObject().createEntityManager();
    }

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }
}

package com.sample.config;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class JpaConfig {

    @Autowired
    private Environment env;
    @Autowired
    private DataSource dataSource;

    /*
    @Bean
    public AbstractPlatformTransactionManager transactionManager() {
        Properties p = new Properties();
        p.put("transactionManagerName", "java:/TransactionManager");
        JtaTransactionManager txMgr = new JtaTransactionManager();
        txMgr.setJndiEnvironment(p);
        return txMgr;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJtaDataSource(dataSource);
        Properties props = new Properties();
//		props.setProperty("hibernate.dialect", this.env.getProperty("hibernate.dialect"));
//        props.setProperty("hibernate.hbm2ddl.auto", this.env.getProperty("hibernate.hbm2ddl.auto"));
//		props.setProperty("hibernate.show_sql", this.env.getProperty("hibernate.show_sql"));
//		props.setProperty("hibernate.format_sql", this.env.getProperty("hibernate.format_sql"));
//        props.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        // TODO re-enable this when flyway/liquibase is ready
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        props.setProperty("hibernate.show_sql", "false");

        props.setProperty("hibernate.format_sql", "true");

//		props.setProperty("hibernate.transaction.jta.platform",
//				this.env.getProperty("hibernate.transaction.jta.platform"));
        props.setProperty("hibernate.transaction.jta.platform", "org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform");
        bean.setJpaProperties(props);
        bean.setPersistenceUnitName("dw");
        bean.setPackagesToScan(new String[]{"com.sample.entities"});
        bean.afterPropertiesSet();
        return bean.getObject();
    }
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter hibernateJpa = new HibernateJpaVendorAdapter();
        hibernateJpa.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        hibernateJpa.setGenerateDdl(true);
        //hibernateJpa.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        hibernateJpa.setShowSql(false);

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.sample");
        emf.setJpaVendorAdapter(hibernateJpa);
        emf.setPersistenceUnitName("dw");
        emf.setJpaPropertyMap(Collections.singletonMap("javax.persistence.validation.mode", "none"));
        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager txnMgr = new JpaTransactionManager();
        txnMgr.setEntityManagerFactory(entityManagerFactory().getObject());
        return txnMgr;
    }
}

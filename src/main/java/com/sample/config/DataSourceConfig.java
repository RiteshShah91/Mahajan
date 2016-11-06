package com.sample.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        //DataSource dataSource = dsLookup.getDataSource(this.env.getProperty("database.jndi"));
        DataSource dataSource = dsLookup.getDataSource("java:/mahajanPostgresDS");

        return dataSource;
    }
//	 local
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/test123");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("smartant");
//
//        dataSource.setMaxActive(100);
//        dataSource.setMaxIdle(30);
//        dataSource.setMaxWait(2000);
//
//        return dataSource;
//    }
}

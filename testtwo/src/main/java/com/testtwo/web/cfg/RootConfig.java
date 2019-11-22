package com.testtwo.web.cfg;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = { "com.testtwo.web" })
@Import({MyBatisConfig.class, ServletConfig.class})
public class RootConfig {
	@Bean
    public DataSource dataSource() {
    
           DriverManagerDataSource dataSource = new DriverManagerDataSource();
             dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
             dataSource.setUrl("jdbc:mysql://172.168.0.78/goodfor");
             dataSource.setUsername("goodfor");
             dataSource.setPassword("goodfor");
             return dataSource;
    }    
    
    @Bean
    public DataSourceTransactionManager txManager() {
         return new DataSourceTransactionManager(dataSource());
    }
}


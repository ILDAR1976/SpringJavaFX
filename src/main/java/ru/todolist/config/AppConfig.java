package ru.todolist.config;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Properties;

@Configuration
@ComponentScan("ru.todolist.controller")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
    	System.out.println("Create datasource begin");
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        dataSource.setUrl("jdbc:derby://localhost:1527/todo;create=true"); 
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        System.out.println(dataSource);
        System.out.println("Create datasource end");
        return dataSource;
    }

    @Autowired
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) throws SQLException {
    	System.out.println("Create entity begin");
    	System.out.println(" URL datasource: " + dataSource.getConnection().getMetaData().getURL());
    	LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        bean.setPersistenceProviderClass(HibernatePersistence.class);
        bean.setDataSource(dataSource);
        bean.setJpaProperties(properties);
        bean.setPackagesToScan("ru.todolist.config");
        //System.out.println(" Catalog datasource: " + dataSource.getConnection().getMetaData().getSQLStateType());
        System.out.println(bean);
        System.out.println("Create entity end");
        return bean;
    }

    @Autowired
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory, DataSource dataSource) {
    	System.out.println("Create transaction begin");
    	JpaTransactionManager bean = new JpaTransactionManager(entityManagerFactory);
        bean.setDataSource(dataSource);
        System.out.println(bean);
        System.out.println("Create transaction end");
        return bean;
    }
    

}
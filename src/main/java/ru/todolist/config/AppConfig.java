package ru.todolist.config;

import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.persistence.spi.PersistenceUnitInfo;
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

    @SuppressWarnings({ "deprecation", "unused" })
	@Autowired
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) throws SQLException {
    	LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        Properties properties = new Properties();
        String systemDir = "./todo";
        properties.put("derby.system.home", systemDir);
        properties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
       
        DefaultPersistenceUnitManager pum = new DefaultPersistenceUnitManager();
        pum.setDefaultDataSource(dataSource);
        
        //PersistenceUnitPostProcessor[] postProcessors = { new JPAPersistenceUnitPostProcessor() };
        //pum.setPersistenceUnitPostProcessors(postProcessors);
        //pum.afterPropertiesSet();
        bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        
        bean.setDataSource(dataSource);
        bean.setJpaProperties(properties);
        bean.setPackagesToScan("ru.todolist");
        
        //bean.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
        //bean.setPersistenceUnitManager(pum);
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
package pro.sky.jdbc.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pro.sky.jdbc.exception.ConfigurationException;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class HibernateConfiguration {
    private static final String HIBERNATE_PROPERTIES_FILE = "hibernate.properties";
    private final ApplicationProperties applicationProperties;


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(applicationProperties.entityPackages());
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(applicationProperties.driverClassName());
        dataSource.setUrl(applicationProperties.url());
        dataSource.setUsername(applicationProperties.user());
        dataSource.setPassword(applicationProperties.password());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        try {
            return PropertiesLoaderUtils.loadAllProperties(HIBERNATE_PROPERTIES_FILE);
        } catch (IOException e) {
            throw new ConfigurationException("Ошибка при конфигурации Hibernate: " + e.getMessage());
        }
    }
}
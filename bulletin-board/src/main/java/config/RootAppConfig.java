package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@ComponentScan
@EnableTransactionManagement
public class RootAppConfig {

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/bulletin_board?serverTimezone=Asia/Taipei");
		config.setUsername("root");
		config.setPassword("root");
		config.setMaximumPoolSize(10);           
        config.setMinimumIdle(5);               
        config.setConnectionTimeout(30000);      
        config.setIdleTimeout(600000);          
        config.setPoolName("MySQLHikariCP");   
        return new HikariDataSource(config);
	}
	@Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("entity");
        factoryBean.setHibernateProperties(addtionalProerties());
        return factoryBean;
    }
    private Properties addtionalProerties() {
        Properties props = new Properties();
        props.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class);
        props.put("hibernate.show_sql", Boolean.TRUE);
        props.put("hibernate.format_sql", Boolean.TRUE);
        props.put("hibernate.hbm2ddl.auto", "update");
        return props;
    }
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txMgr = new HibernateTransactionManager();
        txMgr.setSessionFactory(localSessionFactoryBean().getObject());
        return txMgr;
    }
        
    
	
}

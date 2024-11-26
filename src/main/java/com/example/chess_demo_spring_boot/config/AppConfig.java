package com.example.chess_demo_spring_boot.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.example.chess_demo_spring_boot")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.chess_demo_spring_boot.repository")
@PropertySource("classpath:application.properties")
@EnableWebMvc
@RequiredArgsConstructor
public class AppConfig {
    private final Environment environment;
    @Bean
    @Profile("prod")
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

//    @Bean
//    @Profile("prod")
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:db/init.sql")
//                .build();
////        return new HikariDataSource(hikariConfig());
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties properties) {

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactory.setJpaProperties(properties);
        entityManagerFactory.setPackagesToScan("com.example.chess_demo_spring_boot.domain",
                "com.example.chess_demo_spring_boot.repository");
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
//        properties.setProperty("spring.jpa.hibernate.dialect", environment.getProperty("spring.jpa.hibernate.dialect"));
        properties.setProperty("spring.jpa.hibernate.ddl-auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("spring.jpa.show-sql", environment.getProperty("spring.jpa.show-sql"));
        properties.setProperty("spring.jpa.properties.hibernate.format_sql",
                environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
        return properties;
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty("spring.datasource.url"));
        config.setUsername(environment.getProperty("spring.datasource.username"));
        config.setPassword(environment.getProperty("spring.datasource.password"));
        config.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        config.setMaximumPoolSize(environment.getProperty("spring.datasource.maximum-pool-size", Integer.class));

        return config;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return  new BCryptPasswordEncoder();
//    }

    // M V C = frontend (request) -> C -> M -> V -> frontend (response)
    @Bean
    public ViewResolver viewResolver(){
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".ftlh");
        viewResolver.setContentType("text/html;Charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer markerConfigurer(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:templates/");
        return configurer;
    }
}

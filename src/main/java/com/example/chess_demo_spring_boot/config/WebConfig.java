package com.example.chess_demo_spring_boot.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
@Configuration
@EnableWebMvc
public class WebConfig implements WebApplicationInitializer {
    @Override
    @SneakyThrows
    public void onStartup(ServletContext container){

        try {
            ResourcePropertySource propertySource = new ResourcePropertySource("classpath:application.properties");

            AnnotationConfigWebApplicationContext servletContext = new AnnotationConfigWebApplicationContext();
            servletContext.getEnvironment().
                    setActiveProfiles(Objects.requireNonNull(propertySource.getProperty("spring.application-profile")).
                            toString());
            servletContext.register(AppConfig.class);

            ContextLoaderListener loaderListener = new ContextLoaderListener(servletContext);

            container.addListener(loaderListener);
            ServletRegistration.Dynamic dispatcherServlet = container.
                    addServlet("dispatcher", new DispatcherServlet(servletContext));
            dispatcherServlet.setLoadOnStartup(1);
            dispatcherServlet.addMapping("/");
        } catch (IOException ex) {
            Logger.getLogger(WebConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

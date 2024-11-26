package com.example.chess_demo_spring_boot;

//import com.example.chess_demo_spring_boot.config.AppConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ChessDemoSpringBootApplication.class);
	}

}

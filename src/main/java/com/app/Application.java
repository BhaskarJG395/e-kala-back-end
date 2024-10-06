package com.app;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean // equivalent to <bean id ..../> in xml file
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
	.setPropertyCondition(Conditions.isNotNull());
		return modelMapper;
	}

	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
		    public void addCorsMappings(CorsRegistry registry) {
		        registry.addMapping("/**")  // Apply CORS settings to all endpoints
		            .allowedOrigins("http://localhost:3000")  // Allow requests from this origin
		            .allowedOrigins("https://gorgeous-moonbeam-bcf21d.netlify.app")  // Allow requests from this origin
		            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "UPDATE")  // Allow these HTTP methods
		            .allowedHeaders("*")  // Allow all headers
		            .allowCredentials(true);  // Allow credentials (e.g., cookies)
		    }
		};
	}
}
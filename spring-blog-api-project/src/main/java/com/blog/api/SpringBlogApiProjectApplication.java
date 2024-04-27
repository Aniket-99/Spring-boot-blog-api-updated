package com.blog.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@SpringBootApplication
//@EnableSwagger2
public class SpringBlogApiProjectApplication{

	public static void main(String[] args) {
		SpringApplication.run(SpringBlogApiProjectApplication.class, args);
	}
	
	
	@Bean
	protected ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	/*
	 * @Bean public Docket productApi() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage("com.blog")).build(); }
	 */

	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	/*
	 * @Override public void run(String... args) throws Exception {
	 * System.out.println(this.passwordEncoder().encode("aniket"));
	 * 
	 * }
	 */
	
}

package com.gachonsw.blooddonation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BloodDonationApplication {
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
//
//			+ "classpath:application.yml,"
//			+ "classpath:application-local.yml,"
//			+ "classpath:aws.yml,"

			+ "classpath:application-prod.yml";
	public static void main(String[] args){
		new SpringApplicationBuilder(BloodDonationApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
}
package com.karrabo.interswitch_api_tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InterswitchApiTestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterswitchApiTestsApplication.class, args);
	}

}

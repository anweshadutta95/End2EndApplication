package com.myproject.microservice.faculty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FacultyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacultyApplication.class, args);
	}

}

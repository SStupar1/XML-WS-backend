package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableEurekaClient
@EnableJpaRepositories
public class AdServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdServiceApplication.class, args);
	}

}

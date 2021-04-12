package com.example.mainmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MainModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainModuleApplication.class, args);
	}

}

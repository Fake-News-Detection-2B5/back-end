package com.example.zuulredirect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class RedirectZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedirectZuulApplication.class, args);
	}

}

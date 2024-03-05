package com.example.springapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
@RestController
public class SpringapiApplication {


	@Value("${application.name}")
	public String appName;

	@GetMapping("/")
	public String hello() {
		return appName;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(SpringapiApplication.class, args);
	}

}

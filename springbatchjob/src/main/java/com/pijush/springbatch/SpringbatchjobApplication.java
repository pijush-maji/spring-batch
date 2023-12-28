package com.pijush.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringbatchjobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchjobApplication.class, args);
	}

}

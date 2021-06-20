package com.SpringBoot.Rest.mongo.rest.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableCaching
public class SpringBootMongoRestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoRestProjectApplication.class, args);
	}

}

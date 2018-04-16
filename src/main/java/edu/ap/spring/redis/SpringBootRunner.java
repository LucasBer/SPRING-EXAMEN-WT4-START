package edu.ap.spring.redis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import edu.ap.spring.redis.RedisService;

@SpringBootApplication
public class SpringBootRunner {
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {	 		
	 		// Keys
		};
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRunner.class, args);
	}

}


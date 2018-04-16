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
	
	private RedisService service;
	
	@Autowired
	public void setRedisService(RedisService service) {
		this.service = service;
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {	 	
		
	 		service.setKey("test", "test2");
	 		Map<String, String> studentInfo = new HashMap<String, String>();
	 		studentInfo.put("examen1", "reden1");
	 		service.hset("student", studentInfo);
	 		Map<Object, Object> testInfo = service.hgetAll("student");
	 		String keyString = testInfo.keySet().toString();
	 		System.out.println(keyString.substring(1, keyString.length()-1));
	 		System.out.println(testInfo.get("examen1"));
	 		
		};
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRunner.class, args);
	}

}


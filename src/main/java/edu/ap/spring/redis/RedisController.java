package edu.ap.spring.redis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RedisController {

	 private RedisService service;
		
	   @Autowired
		public void setRedisService(RedisService service) {
			this.service = service;
	   }
	   
	   @PostMapping("/new")
	   public void newInhaal(@RequestParam ("student") String student, @RequestParam ("exam") String exam, @RequestParam ("reason") String reason) {
		   if (service.getKey(student).toString() == student.toString()) {
			   if(!service.hgetAll(student).containsKey(exam) && !service.hgetAll(student).containsValue(reason))
			   {
			   Map<String, String> studentInfo = new HashMap<String, String>();
			   studentInfo.put(exam, reason);
			   service.hset(student, studentInfo);
			   }
		   }
		   
	   }
}

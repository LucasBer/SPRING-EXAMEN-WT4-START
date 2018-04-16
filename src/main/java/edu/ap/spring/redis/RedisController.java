package edu.ap.spring.redis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisController {

	 private RedisService service;
		
	   @Autowired
		public void setRedisService(RedisService service) {
			this.service = service;
	   }
	   
	   @RequestMapping("/")
	   @ResponseBody
	   public String index() {
		   String html = "<html><body><form action='new' method='POST'>";
		   html += "<input type='text' class='form-control' name='student' id='student'>";
		   html += "<input type='text' class='form-control' name='exam' id='exam'>";
		   html += "<input type='text' class='form-control' name='reason' id='reason'>";
		   html += "<input type='submit' value='Save'></body></html>";
		   return html;
	   }
	   
	   @PostMapping("/new")
	   public String newInhaal(@RequestParam ("student") String student, @RequestParam ("exam") String exam, @RequestParam ("reason") String reason) {
		   Set<String> studentSet = service.keys(student); //Studenten ophalen met zelfde naam
		   String studentName = studentSet.toString(); // [student]
		   studentName = studentName.substring(1, studentName.length()-1); //student
		   if (!(studentName == student)) { //
			   Map<String, String> newStudent = new HashMap<String, String>();
			   newStudent.put(exam, reason);
			   service.hset(student, newStudent);
			   }
		   else {
			   Map<Object, Object> studentInfo = service.hgetAll(student);
			   String keyString = studentInfo.keySet().toString(); // [keyString]
			   keyString = keyString.substring(1, keyString.length()-1); // keyString
			   String valueString = studentInfo.get(keyString).toString();
			   if (!(keyString == exam && valueString == reason)) {
				   Map<String, String> newStudent = new HashMap<String, String>();
				   studentInfo.put(exam, reason);
				   service.hset(student, newStudent);
			   }
			   else
				   return "/";
		   }
		   return "/";
	   }
	   
	   @RequestMapping("/list")
	   @ResponseBody
	   public String inhaalList(@RequestParam ("student") String student) {
		   String html = "<html><body><ul><li>";
		   Map<Object, Object> studentInfo = service.hgetAll(student);
		   String keyString = studentInfo.keySet().toString();
		   keyString = keyString.substring(1, keyString.length()-1);
		   String valueString = studentInfo.get(keyString).toString();
		   html += "Student : " + student + "<li>";
		   html += keyString + "</li><li>";
		   html += valueString + "</li></body></html>";
		   return html;
	   }
}

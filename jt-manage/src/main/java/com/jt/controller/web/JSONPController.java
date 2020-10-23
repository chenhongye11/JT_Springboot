package com.jt.controller.web;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;

@RestController
public class JSONPController {
	
	
	@RequestMapping("/web/testJSON")
	public String testJSONP(String callback) throws JsonProcessingException {
		User user = new User();
		user.setId(100L);
		user.setUsername("tomcat");
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(user);
		return callback +"("+json+")";
		
		
		
		
	}
	
	@RequestMapping("/web/tesJSON")
	public JSONPObject jsonp(String callback) {
		User user = new User();
		user.setId(100L);
		user.setUsername("tomcat");
		JSONPObject object= new JSONPObject(callback,user);
		
		
		return object;
	}
	
	
	
}




package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.pojo.vo.SysResult;
import com.jt.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject checkUser(@PathVariable String param, @PathVariable Integer type,  String callback) {
		
		JSONPObject object = null;
		try {
			boolean flag = userService.checkUser(param,type);
			object = new JSONPObject (callback,SysResult.ok(flag));
		} catch (Exception e) {
			e.printStackTrace();
			object = new JSONPObject (callback,SysResult.fail());
			
		}
		
		return object;
		
		
	}
	
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult registerUser(String user) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			User _user=objectMapper.readValue(user, User.class);
			userService.registerUser(_user);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail();
		}
		 
	}
	
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public String LoginUser(String user) {
		String token;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			User _user=objectMapper.readValue(user, User.class);
			
			token=userService.LoginUser(_user);
			return token;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	@RequestMapping("/query/{ticket}")
	public JSONPObject findUserByTicket(@PathVariable String ticket,String callback ) {
		String userJSON = null;
		return new JSONPObject(callback,SysResult.ok(userJSON));
	}
	
	
}

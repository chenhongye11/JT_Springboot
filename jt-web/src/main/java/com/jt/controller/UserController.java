package com.jt.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.jt.pojo.User;
import com.jt.pojo.vo.SysResult;
import com.jt.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/{moduleName}")
	//http://sso.jt.com/user/check/gfsdsdf/1?r=0.3642255663110252&callback=jsonp1595351758633&_=1595351775383
	public String index(@PathVariable String moduleName) {
		return moduleName;
	}
	
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult RegisterUser(User user) {
		
		
		
		
		SysResult sysResult=null;
		try {
			sysResult = userService.RegisterUser(user);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 return sysResult;
		
		
	}
	
	
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult Login (User user,HttpServletResponse response) {
		try {
			String token = userService.findUserByUP(user);
			
			if(!StringUtils.isEmpty(token)) {
				Cookie cookie = new Cookie("JT_TICKET",token);
				cookie.setMaxAge(7*24*3600);
				cookie.setDomain("jt.com");
				cookie.setPath("");
				response.addCookie(cookie);
				
			}
			
			return SysResult.ok();
		} catch (Exception e) {
			
			e.printStackTrace();
			
			
		}
		return SysResult.fail();
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();
		String token = null;
		if(cookies.length!=0) {
			
			for (Cookie cookie: cookies) {
				if("JT_TICKET".equals(cookie.getName())) {
					token = cookie.getValue();
					break;
					
				}
			}
			
		}
		
		
		if(StringUtils.isEmpty(token)) {
			
			Cookie cookie = new Cookie("JT_TICKET","");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setDomain("jt.com");
			
			response.addCookie(cookie);
		}
		return "redirect:/";
		
	}
}

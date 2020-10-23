package com.jt.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.User;
import com.jt.pojo.vo.SysResult;
import com.jt.util.HttpClientService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	HttpClientService httpClientService;

	@Override
	public SysResult RegisterUser(User user) throws JsonParseException, JsonMappingException, IOException {
		
		
		String url = "http://sso.jt.com/user/doRegister";
		
		Map<String,String> params = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		String password=DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(password);
		
		String userJson=objectMapper.writeValueAsString(user);
		params.put("user", userJson);
		
		
		
		
		String result =httpClientService.doPost(url, params);
		SysResult sysresult =objectMapper.readValue(result, SysResult.class);
		return sysresult;
		
		
		
		
		
		
	}

	@Override
	public String findUserByUP(User user) {
		String url = "http://sso.jt.com/user/doLogin";
		Map<String,String> params = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		String userJson;
		try {
			userJson = objectMapper.writeValueAsString(user);
			params.put("user", userJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String result =httpClientService.doPost(url, params);
		return result;
	}
	

}

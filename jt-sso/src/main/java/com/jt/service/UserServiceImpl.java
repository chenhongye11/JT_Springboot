package com.jt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	
	//Requirement 1.username 2.phone 3.email
	@Override
	public boolean checkUser(String param, Integer type) {
		String column= (type==1)?"username":(type==2)?"phone":"email";
		
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(column, param);
		int count = userMapper.selectCount(queryWrapper);
		boolean flag = (count ==0)?false:true;
		return flag ;
	}



	@Override
	@Transactional
	public void registerUser(User user) {
		user.setEmail(user.getPhone()).setCreated(new Date()).setUpdated(user.getCreated());
		userMapper.insert(user);
		
	}


	//token = jt_ticket+username+the second rightnow, using md5 to encode.
	@Override
	public String LoginUser(User user) {
		String token= null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String password= DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
			user.setPassword(password);
			QueryWrapper queryWrapper = new QueryWrapper<User>(user);
			
			User userDB = userMapper.selectOne(queryWrapper);
			
			if(userDB!=null) {
				token="JT_TICKET_"+userDB.getUsername()+System.currentTimeMillis();
				token = DigestUtils.md5DigestAsHex(token.getBytes());
			
				String userJSON = objectMapper.writeValueAsString(userDB);
			}
		} catch (Exception e) {
			
		}
		return token;
	}
	
	
	
}

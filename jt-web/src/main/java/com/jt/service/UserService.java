package com.jt.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.pojo.User;
import com.jt.pojo.vo.SysResult;

public interface UserService {
	

	SysResult RegisterUser(User user) throws JsonParseException, JsonMappingException, IOException ;

	String findUserByUP(User user);
	

}

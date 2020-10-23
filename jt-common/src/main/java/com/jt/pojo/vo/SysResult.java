package com.jt.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



//need: there are some case that only need to return one of the parameter
@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysResult {
	
	private Integer status;
	
	private String msg; //后台返回值数据的提示信息
	
	private Object data;//返台返回的任意数据
	
	
	public static SysResult ok() {
		return new SysResult (200, null, null);
		
	}
	
	public static SysResult ok(Object data) {
		return new SysResult(200,null,data);
	}
	
	
	public static SysResult fail() {
		return new SysResult (201,null,null);
	}
	
	
	public static SysResult fail(String msg) {
		return new SysResult(201,msg,null);
	}
	
	

	
	
	
	
	//说明：
	//由于项目是前后台统一调用，封装一个vo对象

}

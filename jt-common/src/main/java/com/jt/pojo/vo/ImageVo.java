package com.jt.pojo.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;



@Data
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ImageVo implements Serializable{
	
	private Integer error;
	private String url;
	private Integer height;
	private Integer width;

}

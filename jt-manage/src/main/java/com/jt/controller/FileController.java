package com.jt.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.pojo.vo.ImageVo;
import com.jt.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	FileService fileService;
	
	
	
	
	
	
	@RequestMapping("pic/upload")
	@ResponseBody
	public ImageVo imageUpload( MultipartFile uploadFile) {
		return fileService.updateFile(uploadFile);
		
	}

}

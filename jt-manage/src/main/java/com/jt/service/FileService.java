package com.jt.service;

import org.springframework.web.multipart.MultipartFile;

import com.jt.pojo.vo.ImageVo;

public interface FileService {

	ImageVo updateFile(MultipartFile uploadFile);

}

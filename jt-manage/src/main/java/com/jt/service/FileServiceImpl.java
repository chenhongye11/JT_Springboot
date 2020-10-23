package com.jt.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.pojo.vo.ImageVo;



@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService{
	
	
	@Value("${image.localDirPath}")
	private String localDirPath;
	
	private String urlPath = "http://127.0.0.1/";
	
	/**
	 * 文件传送注意事项：
	 * 1. 获取文件名称
	 * 2. 校验是否为负片类型 jpg|png|gif
	 * 3. 校验是否为恶意程序木马 .exe.jpg
	 * 4. 分文件保存，按照时间存储 yyyy/MM/dd
	 * 5. 防止文件重名:是用ＵＵＩＤ来进行　３２位１６进制数＋毫秒数
	 */

	@Override
	public ImageVo updateFile(MultipartFile uploadFile) {
		
		
		
		
		ImageVo imageVo= new ImageVo();
		
		//help linux to identify the letter
		String fileName=uploadFile.getOriginalFilename().toLowerCase();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		Date date= new Date();
		String fileDir=sdf.format(date);
		
	
		if(!fileName.matches("^.+\\.(jpg|png|gif|jpeg)$")) {
			imageVo.setError(1);
			return imageVo;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			
			
			if(width==0 ||height==0) {
				imageVo.setError(1);
				return imageVo;
			}
			
			String localDir = localDirPath+fileDir;
			File newDir= new File(localDir);
			if(!newDir.exists()) {
				newDir.mkdirs();
			}
			//use the UUID to define the name of the file
			String uuid =UUID.randomUUID().toString();
			uuid=uuid.replace("-", "");
			
			String fileType= fileName.substring((fileName.lastIndexOf(".")));
			
			fileName= uuid+fileType;
			
			
		
			
			uploadFile.transferTo(new File(localDirPath+fileDir+fileName));
			
			String realUrlPath = urlPath+fileDir+fileName;
			
			imageVo.setError(0);
			imageVo.setUrl(realUrlPath);
			imageVo.setHeight(height);
			imageVo.setWidth(width);
			return imageVo;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			imageVo.setError(1);
		}
		return imageVo;
		
		
		
		
		
			
			
	}
	
	

}

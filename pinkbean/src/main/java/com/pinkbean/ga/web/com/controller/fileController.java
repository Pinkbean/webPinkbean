package com.pinkbean.ga.web.com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.pinkbean.ga.web.com.dao.FileDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class fileController {
	
	private static final Logger logger = LoggerFactory.getLogger(fileController.class);
	
	@Inject
	DataSource ds;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public ModelAndView home(MultipartHttpServletRequest mpreq) {
		ModelAndView mv = new ModelAndView();
		
		// 파일 변수
		MultipartFile file = null;
		String filename    = null;
		
		// 파일 정보 변수
		List fileDtoList   = new ArrayList<FileDto>();
		FileDto fileDto	   = null;
		String pFileName   = "";
		String lfileName   = "";
		String fileExt     = "";
		String filePath	   = "";
		long   fileSize    = 0;
		
		// 파일 저장 경로 처리
		filePath   = mpreq.getSession().getServletContext().getRealPath("/")+File.separator+"upload";
		File saveDir = new File(filePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}		
		
		// 파일 정보 처리
		Iterator<String> fineNames = mpreq.getFileNames();
		while (fineNames.hasNext()) {
			filename = fineNames.next();
			file 	 = mpreq.getFile(filename);
			
			if (file != null) {
				fileDto	   = new FileDto();
				
				// todo 파일 확장자 검사 필요
				
				lfileName  = file.getOriginalFilename();
				fileExt    = file.getOriginalFilename().split("\\.")[1];
				pFileName  = file.getOriginalFilename().split("\\.")[0] + UUID.randomUUID() +"."+ fileExt;
				fileSize   = file.getSize();
				
				System.out.println("filename 	:: "+filename);
				System.out.println("origFileNm 	:: "+lfileName);
				System.out.println("fileExt 	:: "+fileExt);		
				System.out.println("phyfileNm 	:: "+pFileName);	
				System.out.println("filePath 	:: "+filePath);	
				System.out.println("fileSize 	:: "+fileSize);		
				
				fileDto.setLfileName(lfileName);
				fileDto.setFileSize(fileSize);
				fileDto.setFileExt(fileExt);
				fileDto.setpFileName(pFileName);
				fileDto.setFilePath(filePath);

				// 파일 저장
				this.writeFile(file, filePath+File.separator+pFileName);	
				
				fileDtoList.add(fileDto);
			}
		}
		
		mv.addObject("fileDtoList",fileDtoList);
		mv.setViewName("axisj_home");
		return mv;
	}	
	
	// 파일을 실제로 write 하는 메서드 
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) { 
		
		boolean result 		 = false; 
		byte[] data 		 = null;
		FileOutputStream fos = null;
		
		try {
			data 	= multipartFile.getBytes(); 
			fos 	= new FileOutputStream(saveFileName); 
			System.out.println("saveFileName :: "+saveFileName);
			
			fos.write(data);
			fos.flush();
			fos.close(); 
			
			result	= true;
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
			// 파일 업로드 중 문제가 발생했을 경우 소켓 종료
			if (fos != null) {
				try {
					fos.flush();
					fos.close(); 	
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		}
		
		return result; 
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/file/save", method = RequestMethod.POST)
	public ModelAndView fileLogiccalSave(@ModelAttribute FileDto fileDto) {
		ModelAndView mv = new ModelAndView();

		if(fileDto != null && fileDto.getFileDtoList() != null) {
			System.out.println("fileDtoList :: "+fileDto.getFileDtoList().toString());
		}
		
		mv.setViewName("axisj_home");
		return mv;
	}	
}

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
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
		//filePath   = mpreq.getSession().getServletContext().getRealPath("/")+File.separator+"upload";
		filePath   = mpreq.getSession().getServletContext().getResourcePaths("/")+File.separator+"upload";
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
	

	/**
	 * fileSeUpload
	 * smartEditor 전용 file upload controller
	 * 
	 * @param response
	 * @param originalFile
	 * @param callback
	 * @param callbackFunction
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/file/se/upload",  produces = MediaType.TEXT_HTML_VALUE)
//	public Object fileSeUpload(HttpServletRequest req, HttpServletResponse response, @RequestPart(value = "file") MultipartFile file,
//            @RequestParam(value = "callback", required = false, defaultValue = "") String callback,
//            @RequestParam(value = "callback_func", required = false, defaultValue = "") String callbackFunction) throws Exception {
	public Object fileSeUpload(HttpServletRequest req, HttpServletResponse response, @RequestPart(value = "file") MultipartFile file) throws Exception {		
		ModelAndView mv = new ModelAndView();

		// 파일 정보 변수
		FileDto fileDto	   = new FileDto();
		String pFileName   = "";
		String lfileName   = "";
		String fileExt     = "";
		String filePath	   = "";
		long   fileSize    = 0;
		
		// 파일 저장 경로 처리
		filePath   = req.getSession().getServletContext().getRealPath("/resources/upload");
		File saveDir = new File(filePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		
		lfileName  = file.getOriginalFilename();
		fileExt    = file.getOriginalFilename().split("\\.")[1];
		pFileName  = file.getOriginalFilename().split("\\.")[0] + UUID.randomUUID() +"."+ fileExt;
		fileSize   = file.getSize();
		
		fileDto.setLfileName(lfileName);
		fileDto.setFileSize(fileSize);
		fileDto.setFileExt(fileExt);
		fileDto.setpFileName(pFileName);
		fileDto.setFilePath(filePath);	
		
		// 파일 저장
		this.writeFile(file, filePath+File.separator+pFileName);			
        
        // Smart Editor 리턴
        // 예시) bNewLine=true&sFileName=IMG_0378.JPG&sFileURL=upload/IMG_0378.JPG
        List<BasicNameValuePair> returnQueryString = new ArrayList();
        returnQueryString.add(new BasicNameValuePair("bNewLine", "true"));
        returnQueryString.add(new BasicNameValuePair("sFileName", lfileName));
        returnQueryString.add(new BasicNameValuePair("sFileURL", "/resources/upload"+File.separator+pFileName));
        
        String queryString = URLEncodedUtils.format(returnQueryString, "UTF-8");
        
        response.getWriter().write(queryString);
        response.getWriter().close();

        return null;	
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
	 * Test
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

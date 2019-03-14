package com.pinkbean.ga.web.com.controller;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public ModelAndView home(MultipartHttpServletRequest mpreq) {
		ModelAndView mv = new ModelAndView();
		
		// 파일 변수
		MultipartFile file = null;
		String filename    = null;
		
		// 
		Iterator<String> fineNames = mpreq.getFileNames();
		while (fineNames.hasNext()) {
			filename = fineNames.next();
			file 	 = mpreq.getFile(filename);
			
			if (file != null) {
				System.out.println("filename :: "+filename);
				System.out.println("test :: "+file.getName());
				System.out.println("test :: "+file.getOriginalFilename());
			}
		}
		
		
		mv.addObject("test","test");
		mv.setViewName("axisj_home");
		return mv;
	}	
}

package com.pinkbean.ga.web.main.controller;

import java.sql.SQLException;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pinkbean.ga.web.main.service.TestService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Inject
	DataSource ds;

	@Autowired
	TestService testService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		ModelAndView mv = new ModelAndView();
		
		try {
			
			if (ds != null)
				mv.addObject("dbConnection", ds.getConnection());
			else 
				mv.addObject("dbConnection", "NOCONNECTION");
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		System.out.println(testService.getTest(""));
		
		mv.setViewName("home");
		return mv;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView homeSave(Locale locale, Model model, HttpServletRequest req, HttpSession session) {
		System.out.println("Home_Save Page Requested, locale = " + locale);
		ModelAndView mv = new ModelAndView();
		
		System.out.println("테스트 키워드: "+req.getParameter("keyWord"));
		session.setAttribute("keyWord", req.getParameter("keyWord"));

		mv.addObject("test","test");
		return mv;
	}
	
	@RequestMapping(value = "/menu01", method = RequestMethod.GET)
	public String menu01(Locale locale, Model model) {
		System.out.println("menu01 Page Requested, locale = " + locale);

		return "menu01";
	}		
}

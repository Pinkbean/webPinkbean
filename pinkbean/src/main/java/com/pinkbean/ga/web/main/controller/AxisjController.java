package com.pinkbean.ga.web.main.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AxisjController {
	
	private static final Logger logger = LoggerFactory.getLogger(AxisjController.class);
	
	@Inject
	DataSource ds;

	@Autowired
	TestService testService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/axisj", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		ModelAndView mv = new ModelAndView();
		
		
		mv.setViewName("axisj_home");
		return mv;
	}	
}

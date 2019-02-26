package com.pinkbean.ga.web.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinkbean.ga.web.main.dao.TestDao;

/**
 * Handles requests for the application home page.
 */
@Service("TestService")
public class TestServiceImpl implements TestService{
	
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
	
	@Autowired
	TestDao testDao;
	
	public String getTest(String test) {
		return testDao.getTest(test);
	}
}

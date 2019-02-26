package com.pinkbean.ga.main.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Handles requests for the application home page.
 */
@Repository
public class TestDao {
	
	private static final Logger logger = LoggerFactory.getLogger(TestDao.class);
	
	@Autowired
	SqlSessionTemplate session;
	
	public String getTest(String test) {
		return session.selectOne("com.pinkbean.ga.main.dao.TestDao.getTest", test);
	}
}

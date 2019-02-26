package com.pinkbean.ga.datasource;


import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Configuration
@MapperScan(annotationClass=Repository.class, basePackages="com.pinkbean.ga.web")
public class MybatisConfig {
    
    @Bean(destroyMethod="close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://www.pinkbean.ga:3306/wordpress");
        dataSource.setUsername("mariaDB");
        dataSource.setPassword("1234");
        return dataSource;
    }    
    
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource, ApplicationContext applicationContext) throws IOException {
    	SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean ();
    	sessionFactoryBean.setDataSource(dataSource);
    	sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*Mapper.xml"));
    	return sessionFactoryBean;
    }
    
    @Bean(name="sqlSession")
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }    
}

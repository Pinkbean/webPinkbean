package com.pinkbean.ga.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

import com.pinkbean.ga.datasource.MybatisConfig;

@Configuration
@Import({ MybatisConfig.class })
@ComponentScan(basePackages = {"com.pinkbean.ga"}, excludeFilters = {
        @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
})
public class ApplicationConfig {

}

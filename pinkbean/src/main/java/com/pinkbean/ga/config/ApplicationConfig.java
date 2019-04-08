package com.pinkbean.ga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.mornya.lib.spring.social.naver.connect.NaverConnectionFactory;
import com.pinkbean.ga.datasource.MybatisConfig;
import com.pinkbean.ga.payco.connect.PaycoConnectionFactory;
//import com.pinkbean.ga.payco.socialapi.PaycoConnectionFactory;

@Configuration
@Import({ MybatisConfig.class })
@ComponentScan(basePackages = {"com.pinkbean.ga"}, excludeFilters = {
        @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = Configuration.class, type = FilterType.ANNOTATION)
})
public class ApplicationConfig {

	/**
	 * 2019.04.04 소셜로그인 rest test를 위한 restTemplate Bean 추가
	 * @return
	 */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }	
    
    @Bean
    public ConnectionFactoryRegistry connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        
        registry.addConnectionFactory(new FacebookConnectionFactory("639981633089720", "4c87c6f70c205f7342a67e743e19ec76"));
        registry.addConnectionFactory(new NaverConnectionFactory("bhNoJHMXwy2aHBoMoY0S", "kV4vzfQ1Bq"));
        registry.addConnectionFactory(new PaycoConnectionFactory("3RDQLzSRYbOV_FiHNbD3", "8ADqKfEblEiPMKPxwIHNlbTb"));
        return registry;
    }    
}

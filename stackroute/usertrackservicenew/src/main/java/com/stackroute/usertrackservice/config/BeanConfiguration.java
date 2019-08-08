package com.stackroute.usertrackservice.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stackroute.usertrackservice.filter.JwtFilter;

@Configuration
public class BeanConfiguration {

	/****@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registationBean  = new FilterRegistrationBean();
		registationBean.setFilter(new JwtFilter());
		registationBean.addUrlPatterns("/api/v1/usertrackservice/user/*");
		return registationBean;
	}*****/
}

package com.bhupesh.cricapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bhupesh.cricapi.filter.Jwtfilter;
@Configuration
public class BeanConfigurator {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registationBean  = new FilterRegistrationBean();
		registationBean.setFilter(new Jwtfilter());
		registationBean.addUrlPatterns("/cricapi/v1/*");
		return registationBean;
	}

}

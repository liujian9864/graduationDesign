package com.ecut.frozenpearassistant.config;

import com.ecut.frozenpearassistant.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoginInterceptorConfigurer
	implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor interceptor
			= new LoginInterceptor();
		
		List<String> excludeList = new ArrayList<>();
		excludeList.add("/users/reg");
		excludeList.add("/users/login");
		excludeList.add("/districts/**");
		excludeList.add("/products/**");
		
		excludeList.add("/register");
		excludeList.add("/login");
		excludeList.add("/index");
		excludeList.add("/book");
		excludeList.add("/numerical");
		excludeList.add("/clothes");
		excludeList.add("/daily");
		excludeList.add("/vehicle");
		excludeList.add("/other");
		excludeList.add("/product");
		excludeList.add("/after_search");
		excludeList.add("/goods_details");
		excludeList.add("/goods_details/**");

		excludeList.add("/bootstrap/**");
		excludeList.add("/imgs/**");
		excludeList.add("/css/**");
		excludeList.add("/js/**");
		
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(excludeList);
	}

}








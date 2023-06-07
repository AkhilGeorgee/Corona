package com.filim.program;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.filim.program.filter.ExeptionHandil;

public class WebMVCconfig implements WebMvcConfigurer
{
	@Bean
     public ExeptionHandil getExeptionHandil()
     {
		return new ExeptionHandil();
    	 
     }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(getExeptionHandil()).addPathPatterns("/*");
	}

}

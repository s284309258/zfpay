package com.example.longecological.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 继承WebMvcConfigurerAdapter，通过重写父类的方法进行扩展从而新增一个拦截器
 * @author Administrator
 *
 */
@Configuration
public class ConfigAdapter extends WebMvcConfigurerAdapter{
	
	
	@Override  
	public void addInterceptors(InterceptorRegistry registry)
	{  
		//registry.addInterceptor(new loginInterceptor()).addPathPatterns("/**"); 
	}
}


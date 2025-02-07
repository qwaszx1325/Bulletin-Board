package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.test")
public class WebAppConfig implements WebMvcConfigurer{
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver irViewResolver() {
	    InternalResourceViewResolver irv = new InternalResourceViewResolver();
	    irv.setPrefix("/WEB-INF/jsp/");  
	    irv.setSuffix(".jsp");           
	    irv.setOrder(6);                 
	    return irv;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
	    ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
	    ms.setBasename("message");       
	    ms.setDefaultEncoding("UTF-8"); 
	    return ms;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("locale");      
	    registry.addInterceptor(lci).addPathPatterns("/**");
	}
	
	@Bean
	public SessionLocaleResolver localeResolver() {
	    return new SessionLocaleResolver();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	    StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
	    return resolver;
	}
	
	
	
	
}

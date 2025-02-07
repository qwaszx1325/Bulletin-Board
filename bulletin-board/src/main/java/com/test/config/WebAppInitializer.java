package com.test.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration.Dynamic;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootAppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebAppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	@Override
	protected void customizeRegistration(Dynamic registration) {
	    String location = new java.io.File(System.getProperty("catalina.base"), "wtpwebapps/ROOT/WEB-INF/uploads").getAbsolutePath();
	    
	    new java.io.File(location).mkdirs();
	    
	    long maxFileSize = 5 * 1024 * 1024;      
	    long maxRequestSize = 10 * 1024 * 1024;   
	    int fileSizeThreshold = 0;

	    MultipartConfigElement multipartConfig = new MultipartConfigElement(
	        location, maxFileSize, maxRequestSize, fileSizeThreshold);
	    registration.setMultipartConfig(multipartConfig);
	}

}

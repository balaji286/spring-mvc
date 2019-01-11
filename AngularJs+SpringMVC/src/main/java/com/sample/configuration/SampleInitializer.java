/**
 * Spring Initializer configuration 
 */
package com.sample.configuration;


import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SampleInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    private static final String LOCATION = "c:\\temp"; // Temporary location where files will be stored
 
    private static final long MAX_FILE_SIZE = 10485760 ; // 10MB in bytes(binary) : Max file size.
                                                        // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
     
    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
    
    // Setting the Root configuration
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SampleConfiguration.class }; // Setting Configuration file class
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    
    // Filter CORS - Cross Origin Resource Sharing
    @Override
    protected Filter[] getServletFilters() {
    	Filter [] singleton = { new CORSFilter() };
    	return singleton;
    }
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }
 
    // Setting the size of file and temporary location where the file get stored
    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement( LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
    
}

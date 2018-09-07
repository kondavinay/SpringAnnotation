package com.rabo.statementprocessor;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainClass 
{
    public static void main(String[] args ) throws Exception
    {

    	ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	File c = factory.getBean(File.class);
    	c.reading();
    	
    	
    	
    }
}

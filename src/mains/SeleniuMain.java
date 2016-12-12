package mains;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import Toconfigure.configures;
import action.action;
import io.appium.java_client.AppiumDriver;

public class SeleniuMain {
    public static WebDriver driver;
    @Before  
    public void confiDriver() throws Exception {  
    	//配置Appium
    	driver = configures.configWebDriver("web");
    	System.out.println("=====configure OVER=====");
    } 
    @Test  
    public void dotheAction(){
    	//test
    	System.out.println("=====Test Start=====");
    	action.mainselenium(driver); 
    	System.out.println("=====Test OVER=====");
    } 
    @After  
    public void leaveAppium() throws Exception {  
    	//退出  	
    	driver.quit();
    	System.out.println("=====Exit OVER=====");
    } 
}

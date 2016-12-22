/*
 * Appium Start
 * 
 */
package mains;  
  
import org.junit.After;  
import org.junit.Before;  
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Toconfigure.configures;
import action.action;
import forobject.ReadXml;
import io.appium.java_client.AppiumDriver; 
public class AmAppMain {
    public AppiumDriver driver; 
    public static WebDriver webDriver;
    @Before  
    public void confiDriver() throws Exception {  
    	//配置Appium
    	webDriver = configures.configWebDriver("app");
    	driver = configures.configAppium();
    	System.out.println("=====configure OVER=====");
    } 
    @Test  
    public void dotheAction(){
    	//test
    	System.out.println("=====Test Start=====");
    	action.mainaction(driver); 
    	System.out.println("=====Test OVER=====");
    } 
    @After  
    public void leaveAppium() throws Exception {  
    	//退出  	
    	configures.quirDriver(webDriver);
    	driver.quit();  
    	System.out.println("=====Exit OVER=====");
    } 
}  
//int pageLoadTime = 30;
//driver.manage().timeouts().pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
//driver.manage().timeouts().implicitlyWait(pageLoadTime, TimeUnit.SECONDS);

package seleniums;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import Toconfigure.configures;
import action.action;
import domain.testcase;
import forobject.ReadXml;
import forobject.formexcel;
import forobject.getElement;
import forobject.toolsforObj;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import jdbc.Amojdbc;
import mains.AmAppMain;

public class seleniumDetil {	
		
		public void dogetUrl(WebDriver driver, testcase tc) {				
				//driver.get("https://bc-ecomft1.amplus.com.cn/");
				String URLs = tc.getCaseElement();
	    		if(URLs != null){   	
	    			URLs = toolsforObj.getStrForSend(tc.getCaseElement());	
	    			System.out.println(URLs);
	    			if(URLs != null){
	    				driver.get(URLs);
	    			}
	    			
	    		} 						
				toolsforObj.sleepone();
		}
		public void dosendkey(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){	
				WebElement webElement =  getselenium.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
					String elementlist = elements.get("Others");
					String temp = elementlist;
					if(elementlist.contains("#")){
						temp = (String) formexcel.formexcelSheetgetElement().get(elementlist.substring(elementlist.indexOf("#")+1));				
					}
					if(elementlist.contains("*")){
						temp = configures.forTempString.get(elementlist);					
					}			
					System.out.println(tc.getCaseElement());
					sendKysNow(webElement,temp);		
				}		
			}
		}	
		private void sendKysNow(WebElement webElement, String temp) {
			// TODO Auto-generated method stub
			if(temp != null){
				webElement.sendKeys(temp);
			}
		}
		public void doclick(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				//获取对象
				WebElement webElement =  getselenium.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					System.out.println(tc.getCaseElement());
					webElement.click();
				}		
			}
		}
		public void dogetText(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getselenium.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					String temp = webElement.getText();
					Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
					String elementlist = elements.get("Others");
					System.out.println(elementlist);
					if(elementlist != null){
						if(elementlist.contains("*")){
							configures.forTempString.put(elementlist,temp);	
							System.out.println(temp+"========");
						}	
					}
							
				}		
			}
		}
		public void doclearText(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getselenium.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){			
					for(int i=0;i<10;i++){
						if(webElement.getText().isEmpty()){
							break;
						}
						webElement.clear();
					}
				}		
			}
		}
		
		public void doallow(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			toolsforObj.sleepten();
			driver.switchTo().alert().accept();
		}	
		public void dogetTextbyname(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getselenium.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					String temp = webElement.getAttribute("name");
					Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
					String elementlist = elements.get("Others");
					System.out.println(elementlist);
					if(elementlist != null){
						if(elementlist.contains("*")){
							configures.forTempString.put(elementlist,temp);	
							System.out.println(temp+"========");
						}	
					}
							
				}		
			}
		}
		public void dogetTextbyvalue(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getselenium.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					String temp = webElement.getAttribute("value");
					Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
					String elementlist = elements.get("Others");
					System.out.println(elementlist);
					if(elementlist != null){
						if(elementlist.contains("*")){
							configures.forTempString.put(elementlist,temp);	
							System.out.println(temp+"========");
						}	                                                                                                                                                                                                              
					}			
				}		
			}
		}	
		public void doscrollToE(WebDriver wdrive, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getselenium.getElementObject(wdrive,tc.getCaseElement());
				if(webElement != null){	
					((JavascriptExecutor) wdrive).executeScript("arguments[0].scrollIntoView();", webElement);
					toolsforObj.sleepfive();
				}		
			}		
		}
		public void dotiaoshi(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			System.out.println("=======开始调试=======");
			toolsforObj.sleepten();
			
			
			
			toolsforObj.sleepten();
			System.out.println("=======开始结束=======");	
		}
		
}






























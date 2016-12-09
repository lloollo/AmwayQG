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
		public void doallow(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			toolsforObj.sleepten();
			driver.switchTo().alert().accept();
		}	
		
		public void doscrollToE(WebDriver wdrive, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(wdrive,tc.getCaseElement());
				if(webElement != null){	
					((JavascriptExecutor) wdrive).executeScript("arguments[0].scrollIntoView();", webElement);
					toolsforObj.sleepfive();
				}		
			}		
		}
		
		
}






























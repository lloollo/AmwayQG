package forobject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Toconfigure.configures;

public class getElement {
	
	//处理Element字符串
	public static String[] fStrings(String caseElement) {
		// TODO Auto-generated method stub
    	Map<String, String>  map = formexcel.formexcelSheetgetElement();
    	String Elements = map.get(caseElement);  		   	
    	String temp[] =toolsforObj.SeparateByCommaAndColon(Elements);
   		return temp;
	}  
	 //分解字符串
    public static String[] FindActiveElementfromTow(String caseElement) {
		// TODO Auto-generated method stub
    	Map<String, String>  map = new HashMap<String, String>();
  
    	String Elements = map.get(caseElement);  		   	
    	String temp[] =toolsforObj.SeparateByCommaAndColon(Elements);
   		return temp;
	}     
	//通过罗列的元素查找唯一对象
	public static WebElement getElementObject(WebDriver driver, String getCaseEle) {
			// TODO Auto-generated method stub
			Map<String, String> elements = toolsforObj.decomposeElement(getCaseEle);
			WebElement webEl = null;
			WebElement webElement = null;
			WebElement webElements = null;
			String elementlist = elements.get("elements");
			String elementlists = elements.get("element");
			if(!elementlist.isEmpty()&&elementlists == null){							
				webElement = getObjecyByElements(driver,elementlist);
				//if(ReadXml.actiononMobile.contains("Android")){
				    int sleeptime = configures.sleeptime;
					for(int i=0;i<sleeptime;i++){
						if(webElement !=null){
						//Android把需要操作的当前元素的位置，通过滑动调整到可操作界面上
//							if(ReadXml.actiononMobile.contains("Android")){
//							toolsforObj.putElementToScreen(driver,webElement);
//							}
							webEl = webElement;	
							break;
						}
						toolsforObj.sleepone();
						webElement = getObjecyByElements(driver,elementlist);
						if(i==0){
							System.out.print(getCaseEle);
						}
						System.out.print(i);
					}			
			}	
			if(!elementlist.isEmpty()&&elementlists != null){
				webElement = getObjecyByElements(driver,elementlist);
				webElements = getObjecyByElements(driver,elementlists);
				//if(ReadXml.actiononMobile.contains("Android")){
				int sleeptime = configures.sleeptime;
					for(int i=0;i<sleeptime;i++){
						webElement = getObjecyByElements(driver,elementlist);
						webElements = getObjecyByElements(driver,elementlists);
						if(webElement !=null||webElements!=null){
						//Android把需要操作的当前元素的位置，通过滑动调整到可操作界面上
							if(webElement !=null){
//									if(ReadXml.actiononMobile.contains("Android")){
//									toolsforObj.putElementToScreen(driver,webElement);
//									}
									webEl = webElement;	
							}else{
//									if(ReadXml.actiononMobile.contains("Android")){
//									toolsforObj.putElementToScreen(driver,webElement);
//									}
									webEl = webElements;	
							}
								
							break;
						}
						toolsforObj.sleepone();
						System.out.print(i);
					}	
				//}
								
			}
	    	return webEl;
		} 
	public static WebElement getObjecyByElements(WebDriver driver,String elementlist) {
	    	String temp = toolsforObj.mapgetElement.get(elementlist.substring(elementlist.indexOf("#")+1));  
	    	Map<String, String> elementlMap = toolsforObj.decomposeElementDetile(temp);
	    	WebElement webElement = null;
	    	if(elementlMap.size() == 2){
	    		String eleFindHow = elementlMap.get("eleFindHow");
	    		String eleFindWhat = elementlMap.get("eleFindWhat");    	
	    		switch (eleFindHow) {
	    		case "id":
	    			try {
	    				webElement = driver.findElement(By.id(eleFindWhat));
	    			} catch (Exception e) {
						webElement = null;
					}
	    			break;
	    		case "classname":
	    			webElement = driver.findElement(By.className(eleFindWhat));
	    			break;
	    		case "name":			
	    			webElement = driver.findElement(By.name(eleFindWhat));
	    			break;
	    		case "xpath":
	    			try {    				
	    				webElement = driver.findElement(By.xpath(eleFindWhat));
					} catch (Exception e) {
						webElement = null;
					}
	    			
	    			break;
//	    		case "swipe":
//	    			driver.swipe(248, 690,248, 300, 500);
//	    			driver.tap(1, 85, 950, 500);
//	    			break;
	    		case "linkText":
	    			webElement = driver.findElement(By.linkText(eleFindWhat));
	    			break;
	    		default:
	    			break;
	    		}
	    	}
	    	return webElement;
		}	
	  //分解字符串
	public static Map decomposeElement(String caseElement) {
			// TODO Auto-generated method stub
	    	Map<String, List<String>> map = new HashMap<>();
	    	List<String>  elementList = new ArrayList<String>() ; 			   	
	    	String[] split = null;	
			return map;
		}       
}

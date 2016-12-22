package action;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import Toconfigure.configures;
import domain.testcase;
import forobject.ReadXml;
import forobject.formexcel;
import forobject.getElement;
import forobject.toolsforObj;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import jdbc.Amojdbc;
import mains.AmAppMain;
import seleniums.seleniumDetil;
public class actionDetil {	
		public void dogetUrl(WebDriver driver, testcase tc) {		
			String URLs = tc.getCaseElement();
    		if(URLs != null){   	
    			URLs = toolsforObj.getStrForSend(tc.getCaseElement());	
    			driver.get(URLs);
    		} 								
			toolsforObj.sleepone();
		}
		public void dogetUrlLaunch(AppiumDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			try {
				if(ReadXml.actiononMobile.contains("IOS")){
					//driver.get("https://bc-ecomft1.amplus.com.cn/");
					String URLs = tc.getCaseElement();
		    		if(URLs != null){   			
		    			URLs = toolsforObj.getStrForSend(tc.getCaseElement());				    			
		    			driver.get(URLs);
		    		} 				
				}else {
					if(ReadXml.system.contains("Windows")){
						String URLs = tc.getCaseElement();
			    		if(URLs != null){   			
			    			URLs = toolsforObj.getStrForSend(tc.getCaseElement());				    			
			    			driver.get(URLs);
			    		} 
					}else {
						driver.launchApp();
						toolsforObj.sleeptow();
						WebElement ChromeWelcom = getElement.getElementObject(driver, "#ChromeWelcom"); 
						if(ChromeWelcom != null){				
							ClickNow(ChromeWelcom);
						}
//						WebElement NoChromeWelcom = getElement.getElementObject(driver, "#NoChromeWelcom"); 
//						if(NoChromeWelcom != null){				
//							ClickNow(NoChromeWelcom);			
//						}
					
						//点击首页 输入URL
						WebElement webElement = getElement.getElementObject(driver, "#ChromMain"); //"id", "com.android.chrome:id/search_box_text"
				    	if(webElement != null){				
							ClickNow(webElement);			
						}
				    	//在URL里输入值
				    	webElement =getElement.getElementObject(driver, "#Chromurls");//"id", "com.android.chrome:id/url_bar"
				    	
				    	if(webElement != null){		
				    		webElement.clear();
				    		String amURLs  = (String) formexcel.formexcelSheetgetElement().get(tc.getCaseElement().substring(tc.getCaseElement().indexOf("#")+1));
							sendKysNow(webElement,amURLs);			
						}		    	
				    	driver.sendKeyEvent(66);
					
				
					Thread.sleep(1000);		    	
					}							    	
				}
		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void dosendkey(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){	
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
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
		public void doclick(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					System.out.println(tc.getCaseElement());
					ClickNow(webElement);
				}		
			}
		}
		public void dogetText(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					String temp = gettextall(webElement);
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
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
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
		public void doswipeTo(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			
		}		
		public void dogetPrice(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					String temp = "";
					if(ReadXml.actiononMobile.contains("Android")){
						temp = webElement.getAttribute("name");
					}else {
						temp = webElement.getText();
					}
					if(temp !=null){
						temp = temp.substring(temp.indexOf("¥")+1);
						Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
						String elementlist = elements.get("Others");
						System.out.println(elementlist);
						if(elementlist != null){
							if(elementlist.contains("*")){
								configures.forTempString.put(elementlist,temp);	
								System.out.println(elementlist+"====="+temp);
							}	
						}
					}
			    	
							
				}		
			}
		}
		public void doTotal_Tax(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub		
			String Shipping_Fee =configures.forTempString.get("*Shipping_Fee");
			String NCL_Total_Amount = configures.forTempString.get("*NCL_Total_Amount");;
			if((NCL_Total_Amount !=null)&&(Shipping_Fee!=null)){
				if(Shipping_Fee.contains("免费")){
					Shipping_Fee = "0";
				}
				NCL_Total_Amount=NCL_Total_Amount.replace(",",""); 
				double a = Double.valueOf(Shipping_Fee.toString());
				System.out.println(a);
				double b = Double.valueOf(NCL_Total_Amount.toString());
				double Cart_Tota = (a + b) *0.119;
				System.out.println(Cart_Tota);
				String result =String.format("%.2f", Cart_Tota);
				if(tc.getCaseElement().contains("*")){
					configures.forTempString.put(tc.getCaseElement(),result);	
					System.out.println(result+"========");
				}	
			}
		}
		public void doTotal_Tax_p(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub		
			String Shipping_Fee =configures.forTempString.get("*Shipping_Fee_p");
			String NCL_Total_Amount = configures.forTempString.get("*NCL_Total_Amount_p");;
			if((NCL_Total_Amount !=null)&&(Shipping_Fee!=null)){
				if(Shipping_Fee.contains("免费")){
					Shipping_Fee = "0";
				}
				NCL_Total_Amount=NCL_Total_Amount.replace(",",""); 
				double a = Double.valueOf(Shipping_Fee.toString());
				double b = Double.valueOf(NCL_Total_Amount.toString());
				double Cart_Tota = (a + b) *0.2636;
				String result =String.format("%.2f", Cart_Tota);
				if(tc.getCaseElement().contains("*")){
					configures.forTempString.put(tc.getCaseElement(),result);	
				}	
			}
			
			
			
		}
		public void doTotal_Tax_A(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			String Shipping_Fee =configures.forTempString.get("*Shipping_Fee_A");
			String NCL_Total_Amount_N = configures.forTempString.get("*Cart_Total_Amount_N");
			String NCL_Total_Amount_Z = configures.forTempString.get("*Cart_Total_Amount_Z");
			if((NCL_Total_Amount_N !=null)&&(Shipping_Fee!=null)&&(NCL_Total_Amount_Z !=null)){
				if(Shipping_Fee.contains("免费")){
					Shipping_Fee = "0";
				}
				NCL_Total_Amount_Z=NCL_Total_Amount_Z.replace(",",""); 
				NCL_Total_Amount_N=NCL_Total_Amount_N.replace(",",""); 
				double a = Double.valueOf(Shipping_Fee.toString());
				double b = Double.valueOf(NCL_Total_Amount_Z.toString());
				double c = Double.valueOf(NCL_Total_Amount_N.toString());
				double Cart_Tota = (1+a/(b+c))*(c*0.119+b*0.2636);
				String result =String.format("%.2f", Cart_Tota);
				System.out.println(tc.getCaseElement());
				if(tc.getCaseElement().contains("*")){
					configures.forTempString.put(tc.getCaseElement(),result);	
					System.out.println(result+"========");
				}	
			}
		}
		public void dosendKeyEvent(AppiumDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(tc.getCaseElement() !=null){
				driver.sendKeyEvent(Integer.valueOf(tc.getCaseElement()));
				toolsforObj.sleepfive();
			}
			
		}
		public void doallow(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(ReadXml.actiononMobile.contains("Android")){
				try {
					WebElement button_primary = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.chrome:id/button_primary\"]"));
					button_primary.click();
					toolsforObj.sleepfive();
				} catch (Exception e) {
					// TODO: handle exception
				}	
			}
		}
		public void dogetTextbytext(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					toolsforObj.sleepten();
					String temp = webElement.getText();
					for (int i = 0; i < 10; i++) {
						if(!"".equals(temp)){
							break;
						}
						temp = webElement.getText();
						toolsforObj.sleepone();
					}		
					System.out.println(temp);
					Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
					String elementlist = elements.get("Others");
					System.out.println(elementlist);
					if(elementlist != null){
						if(elementlist.contains("*")){
							configures.forTempString.put(elementlist,temp);
						}	
					}
							
				}		
			}
		}
		public void doscrollToE(AppiumDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					toolsforObj.putElementToScreen(driver,webElement);
				}		
			}
		}
		public void dogetSqlpos(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){		
				Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
				String elementlistelements = elements.get("elements");		
				String[] elementlistelemes=null;
				if(elementlistelements.contains(",")){
					String[] elementlistelem = toolsforObj.SeparateByComma(elementlistelements);	
					elementlistelements = elementlistelem[0];
					elementlistelemes = elementlistelem;
				}
				String elementlistp = (String) formexcel.formexcelSheetgetElement().get(elementlistelements.substring(elementlistelements.indexOf("#")+1));
				if(elementlistp != null){
					String temp =Amojdbc.getDataFromJdbcpos(elementlistp,elementlistelemes);
					String elementlistOthers = elements.get("Others");
					if(elementlistOthers != null){
						if(elementlistOthers.contains("*")){
							configures.forTempString.put(elementlistOthers,temp);	
						}	
					}
				}								
			}
		}
		public void docustom(AppiumDriver driver, testcase tc) {
			String elementlistelements = tc.getCaseElement();		
			doifelse(driver,elementlistelements);
		}
		public void doCaculator(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			System.out.println("开始计算");
			String num_a,num_b,cal_select,num_sum;
			int cal_a,cal_b,cal_sum = 0;
			//获取excel中element的字符串,例如：*a,*b,+;*add_total意味着(add_total=a+b)
			String cal=tc.getCaseElement();
			//通过，或者；分割字符串,
			String splitcal[]=cal.split(",|;");
			//判断是否是变量，是变量取变量的值
			if(splitcal[0].contains("*")){
				 num_a=configures.forTempString.get(splitcal[0]);
			}else{
				num_a=splitcal[0];
			}
			if(splitcal[1].contains("*")){		
			    num_b=configures.forTempString.get(splitcal[1]);
			}else{
			    num_b=splitcal[1];
			}
			cal_select=splitcal[2];
			//转换为int型
			cal_a=Integer.valueOf(num_a).intValue();
			cal_b=Integer.valueOf(num_b).intValue();
			//==============
			//System.out.println(cal_a+cal_select+cal_b);
			//选择＋—＊／
			switch(cal_select){
			case"+":
				cal_sum=cal_a+cal_b;
				break;
			case"-":
				cal_sum=cal_a-cal_b;
				break;
			case"*":
				cal_sum=cal_a*cal_b;
				break;
			case"/":
				cal_sum=cal_a/cal_b;
				break;
			default:
				break;
			}
			num_sum=String.valueOf(cal_sum);
			//将计算好的结果放到map里
			configures.forTempString.put(splitcal[3],num_sum);	
			//打印计算结果
			System.out.println("="+configures.forTempString.get(splitcal[3]));		

		}
		public void doSelectAddress(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub		
			if(!tc.getCaseElement().isEmpty()){		
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
					String elementlist = elements.get("Others");
					Select option = new Select(webElement);
					option.selectByVisibleText(elementlist);
					toolsforObj.sleeptow();		
				}
			}
		}
		public void dotiaoshi(AppiumDriver driver) {
			// TODO Auto-generated method stub 
			System.out.println("==========开始调试==============");		
				 toolsforObj.sleeptow();
				// toolsforObj.sleepten();
				 System.out.println(driver.getContext());
				WebElement element1 = driver.findElement(By.xpath("//android.view.View[@content-desc=\"全程代办\"]"));
				element1.click();
				//安装APP
					//driver.installApp(appPath);					
					//判断应用是否已安装
					//driver.isAppInstalled("package name");
				 
				 toolsforObj.sleepten();
			System.out.println("==========调试结算==============");
		}
		public void dotiaoshi2(AppiumDriver driver) {
			// TODO Auto-generated method stub 
			System.out.println("==========开始调试2==============");
			
				 	toolsforObj.sleeptow();
			    	Set<String> context = driver.getContextHandles();
			        for (String contextName : context) {
			            System.out.println(contextName);
			        }	        
			        driver.context("NATIVE_APP");
			        WebElement webElement1 = (WebElement) driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.tencent.mm:id/cyq\"]"));
			        webElement1.click();
			        toolsforObj.sleepten();
			        WebElement webElement2 = (WebElement) driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id=\"com.tencent.mm:id/bwl\"]/android.widget.RelativeLayout[1]"));
			        webElement2.sendKeys("");
			        //driver.context("WEBVIEW_com.amway.hub.phone");
			        toolsforObj.sleepten();
			        
			
			System.out.println("==========调试结算==============");
		}
		public void dogetbyvalue(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					String temp = getbyvalue(webElement);
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
		public void dogetbyname(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					String temp = getbyname(webElement);
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
		public void dogetOrderNum(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			String temppage = driver.getPageSource();
			String ordernum=temppage.substring(temppage.indexOf("<label>订单编号</label><span>")+25, temppage.indexOf("<label>订单编号</label><span>")+39);			
			System.out.println(ordernum+"========");
			Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
			String elementlist = elements.get("Others");
			if(elementlist != null){
				if(elementlist.contains("*")){
					configures.forTempString.put(elementlist,ordernum);	
					System.out.println(ordernum+"========");
				}	
			}
		
		}
		public void doclicklefttop(AppiumDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					System.out.println(tc.getCaseElement());
					int x = webElement.getLocation().x;
					int y = webElement.getLocation().y;
					driver.swipe(x, y, x, y, 1);		
				}		
			}

		}
		public void doWait(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){
				int time = Integer.valueOf(tc.getCaseElement());
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		}
		public void actionfor(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
			String getCaseElem = elements.get("elements");
			if(!getCaseElem.isEmpty()){	
				int a = getCaseElem.indexOf("#");
		    	int b = getCaseElem.indexOf("~");
		    	String textEleD = getCaseElem.substring(a, b);
		    	String textTextD = getCaseElem.substring(b+1);
				WebElement webElement1 =  getElement.getObjecyByElements(driver,textEleD);
				WebElement webElement2 =  getElement.getObjecyByElements(driver,textTextD);	
				for (int i = 0; i < 10; i++) {
					if(webElement2 !=null){
						break;
					}
					for(int j = 0; j <5; j++){		
						if(webElement1 !=null){
							System.out.println(tc.getCaseElement());
							ClickNow(webElement1);
							break;
						}
						toolsforObj.sleepone();
						webElement1 =  getElement.getObjecyByElements(driver,textEleD);
					}
					
					toolsforObj.sleepone();
					webElement1 =  getElement.getObjecyByElements(driver,textEleD);
					webElement2 =  getElement.getObjecyByElements(driver,textTextD);	
				}
					
			}
		}
		public void dosum(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			String  getCaseElem = tc.getCaseElement();
			if(getCaseElem.contains(";")){	    		
		    	int b = getCaseElem.indexOf(";");
		    	String textEleD = getCaseElem.substring(0, b);
		    	String textTextD = getCaseElem.substring(b+1);
		    	String[] split = textEleD.split("\\,|\\;");	
		    	double sum = 0.0;
		    	String temp = "";
		    	for (String s : split) {
		    		if(s.contains("*")){
		    			s=configures.forTempString.get(s);			
		    		}    	
		    		double d = Double.parseDouble(s);
		    		sum = sum+d;
		    		temp = String.format("%.2f", sum);
				}
		    	if(textTextD.contains("*")){
					configures.forTempString.put(textTextD,temp);	
					System.out.println(temp+"========");
				}
		    	
		    			    		
	    	}
			
		}
		public void doclicklrighttop(AppiumDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
				WebElement webElement =  getElement.getElementObject(driver,tc.getCaseElement());
				if(webElement != null){	
					System.out.println(tc.getCaseElement());
					int x = webElement.getLocation().x;
					int y = webElement.getLocation().y;
					int w = webElement.getSize().width;
					x=x+w-35;
					y=y+55;
					System.out.println(x+"=="+y);
					driver.swipe(x, y, x, y, 1);		
				}		
			}
		}
		public static void ClickNow(WebElement element){
			try {
				element.click();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//toolsforObj.sleeptow();;
		}	
		public static void sendKysNow(WebElement element,String KeyText){
			if(KeyText != null){
				element.sendKeys(KeyText);
			}	
			//toolsforObj.sleepfive();
		}
		public static String gettextall(WebElement webElement) {
			// TODO Auto-generated method stub
			String temp = getbytext(webElement)+getbyname(webElement)+getbyvalue(webElement);	
			return temp;
		}
		public static String getbytext(WebElement webElement) {
			// TODO Auto-generated method stub
			String temp = "";
			try {
				temp = webElement.getText();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return temp;
		}
		public static String getbyname(WebElement webElement) {
			// TODO Auto-generated method stub
			String temp = "";
			try {
				temp = webElement.getAttribute("name");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return temp;
		}
		public static String getbyvalue(WebElement webElement) {
			// TODO Auto-generated method stub
			String temp = "";
			try {
				temp = webElement.getAttribute("value");
			} catch (Exception e) {
				// TODO: handle exception
			}
			return temp;
		}
		public void dogetCardID(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){				
					String elementlist = tc.getCaseElement();
					if(elementlist != null){
						if(elementlist.contains("*")){
							toolsforObj g = new toolsforObj();
							String temp = g.generate();
							configures.forTempString.put(elementlist,temp);	
							System.out.println(temp+"========");
						}	
					}			
			}
		}
		public void contextWeb(AppiumDriver driver) {
			int sleeptime = configures.sleeptime;
			for(int i=0;i<sleeptime;i++){
				Set<String> context = driver.getContextHandles();
				for (String contexts : context) {
					System.out.println(contexts);
					if(contexts.contains("WEBVIEW")){
						driver.context(contexts);
						System.out.println("切换到"+ driver.getContext());
						break;
					}
				}
				if(driver.getContext().contains("WEBVIEW")){
					break;
				}
				if(!driver.getContext().contains("WEBVIEW")){
					if(i==0){
						System.out.print("切换中");
					}
					System.out.print(i);
				}
				
				toolsforObj.sleepone();
			}
		}
		public void contextNATIVE(AppiumDriver driver) {
			// TODO Auto-generated method stub			
			int sleeptime = configures.sleeptime;
			for(int i=0;i<sleeptime;i++){
				Set<String> context = driver.getContextHandles();
				for (String contexts : context) {
					if(contexts.contains("NATIVE_APP")){
						driver.context(contexts);
						System.out.println("切换到"+ driver.getContext());
						break;
					}
				}
				if(driver.getContext().contains("NATIVE_APP")){
					break;
				}
				toolsforObj.sleepone();
			}	
		}
		public void doinformation(WebDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			Map<String, String> information =  toolsforObj.getinformation();
			try {
				String CaseElement  =tc.getCaseElement();
				String[]  informations = toolsforObj.SeparateByComma(CaseElement);
				for (String infor : informations) {
					//身份证号
					if(infor.contains("id")){
						configures.forTempString.put(infor,information.get("cardid"));	
					}
					//手机号码
					if(infor.contains("tel")){
						configures.forTempString.put(infor,information.get("tel"));
					}
					//姓名
					if(infor.contains("name")){
						configures.forTempString.put(infor,information.get("name"));
					}	
					//地址
					if(infor.contains("road")){
						configures.forTempString.put(infor,information.get("road"));
					}
					//性别
					if(infor.contains("sex")){
						configures.forTempString.put(infor,information.get("sex"));
					}
					//邮箱
					if(infor.contains("email")){
						configures.forTempString.put(infor,information.get("email"));
					}
				}
				
				
				
			} catch (Exception e) {
				
			}
			
		}
		public void doifelse(AppiumDriver driver, testcase tc) {
			String ifString = tc.getCaseElement();
			if(ifString.contains(":")&&ifString.contains("?")){
				String[] split = ifString.split("\\:|\\?");
				
				
				if(split.length == 3){
					String ielement = split[0];
					char j = 'N';
					WebElement webElement = getElement.getObjecyByElements(driver,ielement);
					for(int i=0;i<5;i++){
						if(webElement !=null){
							j = 'Y';
							break;
						}
						toolsforObj.sleepone();
						webElement = getElement.getObjecyByElements(driver,ielement);
					}
					switch (j) {
					case 'Y':
						String iactionY = split[1];
						doifelse(driver,iactionY);
						break;
					case 'N':
						String iactionN = split[2];
						doifelse(driver,iactionN);
						break;
					default:		
						break;
					}
				}
			}
		}		
		
		//执行自定义方法
		public void doifelse(AppiumDriver driver,String docuntest) {	
			String elementlistelements = docuntest;
			//给自定义方法传参
			if(!docuntest.isEmpty()&&docuntest.contains(",")){
				String[] split=toolsforObj.SeparateByComma(docuntest);
				elementlistelements = split[0];
				for (int i = 1; i < split.length; i++) {
					String customtemp = split[i];
					if(customtemp.contains("#")){
						customtemp = (String) formexcel.formexcelSheetgetElement().get(customtemp.substring(customtemp.indexOf("#")+1));	
					}else if(customtemp.contains("*")){
						customtemp = configures.forTempString.get(customtemp);	
					}
					configures.forTempString.put("*customtemp"+i,customtemp);	
				}
			}
			//自定义公用方法
			if((!docuntest.isEmpty())&&(docuntest.contains("&"))){							
				//获取自定义脚本的Sheet	
				String elementlistp = elementlistelements.substring(elementlistelements.indexOf("&")+1);
				//获取到Case包含多步
				ArrayList<testcase>  customEleL = (ArrayList<testcase>) formexcel.formexcelSheetgetOther("custom").get("communal").get(elementlistp);
				//执行脚本
    			for (testcase ce : customEleL) {
    				//步骤具体操作
    				action.actioncase(driver, ce);     				
				}					
			}
			//自定义不同方法
			if((!docuntest.isEmpty())&&(docuntest.contains("$"))){							
				String temp = "";
				if(ReadXml.actiononMobile.contains("Android")){
					temp = "Android";
				}else {
					temp = "IOS";
				}
				//获取自定义脚本的Sheet
				String elementlistp =elementlistelements.substring(elementlistelements.indexOf("$")+1);
				//获取到Case包含多步
				ArrayList<testcase>  customEleL = (ArrayList<testcase>) formexcel.formexcelSheetgetOther("custom").get(temp).get(elementlistp);
				//执行脚本
    			for (testcase ce : customEleL) {
    				//步骤具体操作
    				action.actioncase(driver, ce);     				
				}					
			}
		}
		public void doreset(AppiumDriver driver, testcase tc) {
				driver.resetApp();
		}
		public void dostativit(AppiumDriver driver, testcase tc) {
			((AndroidDriver) driver).startActivity("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
		}
		public void dogetSqlsoa(AppiumDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){		
				Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
				String elementlistelements = elements.get("elements");		
				String[] elementlistelemes=null;
				if(elementlistelements.contains(",")){
					String[] elementlistelem = toolsforObj.SeparateByComma(elementlistelements);	
					elementlistelements = elementlistelem[0];
					elementlistelemes = elementlistelem;
				}
				String elementlistp = (String) formexcel.formexcelSheetgetElement().get(elementlistelements.substring(elementlistelements.indexOf("#")+1));
				if(elementlistp != null){
					String temp =Amojdbc.getDataFromJdbcsoa(elementlistp,elementlistelemes);
					String elementlistOthers = elements.get("Others");
					if(elementlistOthers != null){
						if(elementlistOthers.contains("*")){
							configures.forTempString.put(elementlistOthers,temp);	
						}	
					}
				}								
			}
		}
		public void dogetSqlecard(AppiumDriver driver, testcase tc) {
			// TODO Auto-generated method stub
			if(!tc.getCaseElement().isEmpty()){		
				Map<String, String> elements = toolsforObj.decomposeElement(tc.getCaseElement());
				String elementlistelements = elements.get("elements");		
				String[] elementlistelemes=null;
				if(elementlistelements.contains(",")){
					String[] elementlistelem = toolsforObj.SeparateByComma(elementlistelements);	
					elementlistelements = elementlistelem[0];
					elementlistelemes = elementlistelem;
				}
				String elementlistp = (String) formexcel.formexcelSheetgetElement().get(elementlistelements.substring(elementlistelements.indexOf("#")+1));
				if(elementlistp != null){
					String temp =Amojdbc.getDataFromJdbcecard(elementlistp,elementlistelemes);
					String elementlistOthers = elements.get("Others");
					if(elementlistOthers != null){
						if(elementlistOthers.contains("*")){
							configures.forTempString.put(elementlistOthers,temp);	
						}	
					}
				}								
			}
		}
		public void doappend(testcase tc) {
			// TODO Auto-generated method stub
			String  getCaseElem = tc.getCaseElement();
			if(getCaseElem.contains(";")){	    		
		    	int b = getCaseElem.indexOf(";");
		    	String textEleD = getCaseElem.substring(0, b);
		    	String textTextD = getCaseElem.substring(b+1);
		    	String temp = textEleD;
		    	if(textEleD.contains(",")){
		    		String tempover = "";
		    		String[] split = textEleD.split(",");			    	
			    	for (String s : split) {
			    		if(s.contains("*")){
			    			s=configures.forTempString.get(s);			
			    		}  
			    		if(s.contains("#")){
			    			s=toolsforObj.mapgetElement.get(s.substring(s.indexOf("#")+1)); 
			    		}
			    		tempover = tempover+s;
					}
			    	temp = tempover;
		    	}
		    	
		    	if(textTextD.contains("*")){
					configures.forTempString.put(textTextD,temp);	
					System.out.println(temp+"========");
				}			    		
	    	}
		}
}






























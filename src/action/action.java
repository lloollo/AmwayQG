package action;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Toconfigure.configures;
import domain.stepresult;
import domain.testcase;
import forobject.formexcel;
import forobject.getElement;
import forobject.toolsforObj;
import io.appium.java_client.AppiumDriver;
import mains.AmAppMain;
import report.HtmlDoc;
import report.SnapShot;
import report.forreport;
import seleniums.seleniumDetil;
public class action {
		public static String passfailed;
		public static String anwLogs;		
		public String casrAcResult;		
		public static int Total;
		public static int Passed;
		public static int Failed;
		//主测试方法
		public static void  mainaction(AppiumDriver driver){		
	    	toolsforObj.sleepfive();//等等5S
	    	stepresult sr = new stepresult();
	    	//1 读取excel 第一个Sheet
	    	Map<String, ArrayList<testcase>>  map = formexcel.formexcelSheetgetCase();
	    					//生成Log.html文件  	
	    	String DirPathname = toolsforObj.DirPathname();
	    	HtmlDoc HtmlDoc = new HtmlDoc(DirPathname);
	    	HtmlDoc.dirPathmkdirs();
	    	//2 遍历Case内容
	    	for (Map.Entry<String, ArrayList<testcase>> entry : map.entrySet()) {
	    			//1 获取到Case包含多步
	    			ArrayList<testcase>  atc = entry.getValue();
	    			forreport.InitializationResult();
	    			forreport.tr.setCaseID(atc.get(0).getCaseID());
	    			forreport.tr.setCaseName(atc.get(0).getCaseTitle());
	    			forreport.tr.setDescribe(atc.get(0).getCaseTitle());
	    			System.out.println(atc.get(0).getCaseID());
	    			//2 遍历Case的Step，单步操作
	    			int i=0;
	    			for (testcase tc : atc) {
	    				i++;
	    				passfailed = configures.passfailed;
	    				anwLogs  = configures.anwLogs;
	    				sr = new stepresult();
	    				sr.setCaseID(tc.getCaseID());   				
	    				sr.setStepActiom(tc.getCaseStep());
	    				//步骤具体操作
	    				actioncase(driver, tc);  
	    				//结果校验
	    				List<String> anwLogs =ALcaseCheckresult(driver, tc,DirPathname,i);
	    				sr.setStepResult(anwLogs.get(0));
	    				sr.setStepLog(anwLogs.get(1));
	    				sr.setTec(tc);
	    				//2 单步结果记录
	    				forreport.sr.add(sr);	
					}
	    			//3 把Case的结果记录包含单步操作结果，写入日志
	    			forreport.writeResult(DirPathname);
	    	}
	    	System.out.println(configures.forTempString);
	    	//3case结果统计
	    	HtmlDoc.CompleteCount(Total,Passed,Failed);		
		}
		//selenium主测试方法
		public static void  mainselenium(WebDriver driver){		
	    	toolsforObj.sleepfive();//等等5S
	    	stepresult sr = new stepresult();
	    	//1 读取excel 第一个Sheet
	    	Map<String, ArrayList<testcase>>  map = formexcel.formexcelSheetgetCase();
	    					//生成Log.html文件  	
	    	String DirPathname = toolsforObj.DirPathname();
	    	HtmlDoc HtmlDoc = new HtmlDoc(DirPathname);
	    	HtmlDoc.dirPathmkdirs();
	    	//2 遍历Case内容
	    	for (Map.Entry<String, ArrayList<testcase>> entry : map.entrySet()) {
	    			//1 获取到Case包含多步
	    			ArrayList<testcase>  atc = entry.getValue();
	    			forreport.InitializationResult();
	    			forreport.tr.setCaseID(atc.get(0).getCaseID());
	    			forreport.tr.setCaseName(atc.get(0).getCaseTitle());
	    			forreport.tr.setDescribe(atc.get(0).getCaseTitle());
	    			System.out.println(atc.get(0).getCaseID());
	    			//2 遍历Case的Step，单步操作
	    			int i=0;
	    			for (testcase tc : atc) {
	    				i++;
	    				passfailed = configures.passfailed;
	    				anwLogs  = configures.anwLogs;
	    				sr = new stepresult();
	    				sr.setCaseID(tc.getCaseID());   				
	    				sr.setStepActiom(tc.getCaseStep());
	    				//步骤具体操作
	    				seleniumcase(driver, tc);  
	    				//结果校验
	    				List<String> anwLogs =ALcaseCheckresult(driver, tc,DirPathname,i);
	    				sr.setStepResult(anwLogs.get(0));
	    				sr.setStepLog(anwLogs.get(1));
	    				sr.setTec(tc);
	    				//2 单步结果记录
	    				forreport.sr.add(sr);	
					}
	    			//3 把Case的结果记录包含单步操作结果，写入日志
	    			forreport.writeResult(DirPathname);
	    	}
	    	System.out.println(configures.forTempString);
	    	//3case结果统计
	    	HtmlDoc.CompleteCount(Total,Passed,Failed);		
		}
		static actionDetil ad =new actionDetil();
		//执行关键字解析
		public static void actioncase(AppiumDriver driver, testcase tc) {
			actionDetil ad =new actionDetil();	
			seleniumDetil se = new seleniumDetil();
			WebDriver Wdrive = getdriver(driver);
			switch (tc.getCaseAction()) {			
			//$ - selenium测试关键字
			//点击对象
			case "$click":			
				ad.doclick(Wdrive, tc);
				break;
				//输入Url访问页面
			case "$getUrl":
				ad.dogetUrl(Wdrive, tc);
				break;
				//对象中输入信息
			case "$sendkey":
				ad.dosendkey(Wdrive, tc);
				break;
				//对象中输入信息
			case "$allow":
				se.doallow(Wdrive, tc);
				break;		
				//滑动到当前对象
			case "$scrollToE":
				se.doscrollToE(Wdrive, tc);
				break;				
			//点击对象
			case "click":
				ad.doclick(driver, tc);
				break;	
				//对象中输入信息
			case "sendkey":
				ad.dosendkey(driver, tc);
				break;				
			//获取对象Text
			case "getText":	
				ad.dogetText(driver, tc);
				break;
				//获取个人信息
			case "getinfor":
				ad.doinformation(driver,tc);
				break;
				//数据库查询结果
			case "getSQLpos":
				ad.dogetSqlpos(driver, tc);
				break;
			case "getSQLsoa":
				ad.dogetSqlsoa(driver, tc);
				break;
			case "getSQLecard":
				ad.dogetSqlecard(driver, tc);
				break;
				//自定义脚本方法	
			case "custom":
				ad.docustom(driver, tc);
				break;
				//自定义脚本方法	
			case "stativit":
				ad.dostativit(driver, tc);
				break;
				//切换mobile-web
			case "contextWeb":
				ad.contextWeb(driver);
				break;	
				//切换mobile-源生的
			case "contextNATIVE":
				ad.contextNATIVE(driver);
				break;					
			case "ifelse":
				ad.doifelse(driver,tc);
				break;	
			//输入Url访问页面
			case "getUrl":
				ad.dogetUrl(driver, tc);
				break;
			//重启APP，并输入URL访问
			case "getUrlLaunch":
				ad.dogetUrlLaunch(driver, tc);
				break;	
				//重启APP，并输入URL访问
			case "reset":
				ad.doreset(driver, tc);
				break;	
				//点击左上角
			case "clicklefttop":
				ad.doclicklefttop(driver, tc);
				break;	
			//点击右上角
			case "clicklrighttop":
				ad.doclicklrighttop(driver, tc);
				break;		
			//获取身份证号码
			case "getCardID":	
				ad.dogetCardID(driver, tc);
				break;			
			//通过Text获取text	
			case "getbytext":
				ad.dogetTextbytext(driver, tc);
				break;	
			//通过Text获取value	
			case "getbyvalue":
				ad.dogetbyvalue(driver, tc);
				break;	
			//通过Text获取name	
			case "getbyname":
				ad.dogetbyname(driver, tc);
				break;		
			//清空对象里的信息
			case "clearText":
				ad.doclearText(driver, tc);
				break;	
			//滑动屏幕
			case "swipeTo":
				ad.doswipeTo(driver, tc);
				break;
			//获取价格
			case "getPrice":
				ad.dogetPrice(driver, tc);
				break;	
			//计算税收
			case "Total_Tax":
				ad.doTotal_Tax(driver, tc);
				break;	
			//计算多种商品税收
			case "Total_Tax_P":
				ad.doTotal_Tax_p(driver, tc);
				break;
			//计算多种商品税收
			case "Total_Tax_A":
				ad.doTotal_Tax_A(driver, tc);
				break;
			//点击系统按钮：HOME MENU
			case "sendKeyEvent":
				ad.dosendKeyEvent(driver, tc);
				break;
			//允许弹框
			case "allow":
				ad.doallow(driver, tc);
				break;
			//滑动到当前对象
			case "scrollToE":
				ad.doscrollToE(driver, tc);
				break;				
				//滑动到当前对象
			case "scrollToup":
				ad.doscrollToUp(driver, tc);
				break;	
			//加减乘数计算
			case "caculator":
				ad.doCaculator(driver, tc);
				break;	
			//下拉框输入值
			case "selectAddress":
				ad.doSelectAddress(driver, tc);
				break;
			//调试代码	
			case "tiaoshi":
				ad.dotiaoshi(driver);
				break;
				//调试代码	
			case "tiaoshi2":
				ad.dotiaoshi2(driver);
				break;			
			//获取订单编号
			case "getOrderNum":
				ad.dogetOrderNum(driver,tc);
				break;
			//等等时长
			case "Wait":
				ad.doWait(driver,tc);		
				break;	
			//点击第一个控件知道第二个控件出现
			case "actionfor":
				ad.actionfor(driver,tc);
				break;
			//求和ß
			case "sum":
				ad.dosum(driver,tc);
				break;
				//连接字符串
			case "append":
				ad.doappend(tc);
				break;	
				
			default:
				System.out.println("操作关键字错误！！");
				break;
			}
			// TODO Auto-generated method stub			 	
		}
		//selenium执行关键字解析
		public static void seleniumcase(WebDriver Wdrive, testcase tc) {
			seleniumDetil se = new seleniumDetil();
			switch (tc.getCaseAction()) {			
			//$ - selenium测试关键字
			//点击对象
			case "$click":			
				ad.doclick(Wdrive, tc);
				break;
				//输入Url访问页面
			case "$getUrl":
				ad.dogetUrl(Wdrive, tc);
				break;
				//对象中输入信息
			case "$sendkey":
				ad.dosendkey(Wdrive, tc);
				break;
				//对象中输入信息
			case "$allow":
				ad.doallow(Wdrive, tc);
				break;		
				//滑动到当前对象
			case "$scrollToE":
				se.doscrollToE(Wdrive, tc);
				break;				
			default:
				System.out.println("操作关键字错误！！");
				break;
			}
			// TODO Auto-generated method stub			 	
		}
		//结果校验
		public static List<String> ALcaseCheckresult(WebDriver driver, testcase tc, String dirPathname, int i) {
			toolsforObj.sleeptow();
			List<String> anlogs = new ArrayList<String>();
			String passfailed = "pass";
			String anwLogs = "success";	
			String Caseresult = tc.getCaseresult();
			if(tc.getCaseresult()!= null){
				Caseresult.replace(" ", "");
			}
			if(tc.getCaseresult() != null && (!"".equals(Caseresult))){
				if(tc.getCaseresult().contains(";")){
					String[] results = toolsforObj.SeparateBySemicolon(tc.getCaseresult()); 
					String temp1 = getResulyTextFirst(driver, results[0],tc.getCaseAction());
					String temp2 = temp1;
					if(results[1] !=null){
						temp2 = getResulyTextSecond(driver, results[1],tc.getCaseAction());
					}		
					if((!temp1.contains(temp2))&&(!temp2.contains(temp1))){
						passfailed = "failed";
						anwLogs ="Text is not rigth";
					}
				}else {
					WebElement webElement =  getElement.getElementObject(driver,tc.getCaseresult());	
					for (int j = 0; j < 3; j++) {
						if(webElement != null){
							break;
						}
						toolsforObj.sleeptow();
					}		
					if(webElement == null){			
						passfailed ="failed";
						anwLogs ="not find element";
					}		
				}
			}else {
				anwLogs ="no results!!";
			}
			//记录日志
			anlogs.add(passfailed);
			anlogs.add(anwLogs);
			//手机截屏
			if(passfailed.equals("failed")){
				//SnapShot.JustSnapShot(driver, dirPathname, tc.getCaseID() + "-" + i+".png");
			}
			//SnapShot.JustSnapShot(driver, dirPathname, tc.getCaseID() + "-" + i+".png");
			return anlogs;				
		}	
		//校验第一个Text获取
		public static String getResulyTextFirst(WebDriver driver,String ResulyT, String actions) {
			String resulytext = ResulyT;
			if(ResulyT.contains("#")){
				WebElement webElement =  null;
				if(actions.contains("$")){
					WebDriver Wdrive = getdriver(driver);
					webElement =  getElement.getElementObject(Wdrive,ResulyT);
				}else {
					webElement =  getElement.getElementObject(driver,ResulyT);
				}
				
				if(webElement != null){
					if(actionDetil.gettextall(webElement) != null){
						resulytext = actionDetil.gettextall(webElement);
					}else if(webElement.getText()!= null){
						resulytext = webElement.getText();
					}	
				}else {
					resulytext = "null";
				}	
			}else if (ResulyT.contains("*")) {
				if(configures.forTempString.get(ResulyT) != null){
					resulytext = configures.forTempString.get(ResulyT);
				}else {
					resulytext = "";
				}
			}
			return resulytext;
		}
		//校验第二个Text获取
		public static String getResulyTextSecond(WebDriver driver,String ResulyT,String actions) {
			String resulytext = ResulyT;
			if(resulytext == null){
				ResulyT= "exist";
			}else {
				if(ResulyT.contains("#")){
					ResulyT = (String) formexcel.formexcelSheetgetElement().get(ResulyT.substring(ResulyT.indexOf("#")+1));	
				}else if (ResulyT.contains("*")) {
					if(configures.forTempString.get(ResulyT) != null){
						resulytext = configures.forTempString.get(ResulyT);
					}
				}
			}
			return resulytext;
		}
		/*
		 * 切换Driver
		 */
		public static WebDriver getdriver(WebDriver driver) {
			driver = AmAppMain.webDriver;
			return driver;
		}	
}
/* 
//打开通知栏
public void openNotification(AndroidDriver driver) {
	driver.openNotifications();
}
//打开其他APP
public void startActivity(AndroidDriver driver,String appPackage,String appActivity) {
	driver.startActivity(appPackage, appActivity);
	toolsforObj.sleeptow();
}
*/
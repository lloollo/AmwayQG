package Toconfigure;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import forobject.ReadXml;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class configures {
	//项目路径
	static File classpathRoot = new File(System.getProperty("user.dir"));
    //存放app目录：apps
	static File appDir = new File(classpathRoot, "apps");
    static File app = new File(appDir, (String) ReadXml.readxml("apps").get("androidapps")+".apk");
    public static String appiumURL = "http://127.0.0.1:"+(String) ReadXml.readxml("action").get("interface")+"/wd/hub";
    public static String passfailed = "pass";
    public static String anwLogs  = "Success";
    public static  Map<String, String> forTempString = new HashMap<>();
    
	public static AppiumDriver driver;
	public static WebDriver webDriver;
	public static WebDriver configWebDriver(){
		WebDriver webDriver = null;
		try {
			webDriver =  new FirefoxDriver();
			webDriver.manage().window().maximize();
		} catch (Exception e) {
			System.out.println("webDriver启动失败");// TODO: handle exception
		}
		return webDriver;	
	}
	public static AppiumDriver configAppium() throws MalformedURLException {		
		
        DesiredCapabilities capabilities = new DesiredCapabilities();  
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");      
        //读取XML配置文件，
        Map<String, String>  RootElBute  = (ReadXml.readxml(ReadXml.actiononMobile));       
        //选择测试机型
        if(ReadXml.actiononMobile.contains("Android")){
        	//遍历写入测试配置信息：手机类型，手机版本，
            for (String key : RootElBute.keySet()) {
    				capabilities.setCapability(key, RootElBute.get(key));			
    		}   
        	capabilities.setCapability("app", app.getAbsolutePath());
            driver = new AndroidDriver(new URL(appiumURL), capabilities);  
        }else {
        	//遍历写入测试配置信息：手机类型，手机版本，
            for (String key : RootElBute.keySet()) {
    				capabilities.setCapability(key, RootElBute.get(key));			
    		}   
            //capabilities.setCapability("safariOpenLinksInBackground", true);
            //capabilities.setCapability("safariAllowPopups", true);
            //capabilities.setCapability("ignoreSynchronization", true);
            //capabilities.setCapability("autoDismissAlerts", true);
            //capabilities.setCapability("locationServicesAuthorized", true);
            //capabilities.setCapability("waitForAppScript", "$.delay(2000); $.acceptAlert(); true;");
        	driver = new IOSDriver(new URL(appiumURL),capabilities);	
		}
		return driver;
		
	}
}

package report;

import java.util.ArrayList;
import java.util.List;

import action.action;
import domain.stepresult;
import domain.theresoult;
import io.appium.java_client.AppiumDriver;
import mains.AmAppMain;

public class forreport {
	protected static int Total = 0;
	public static theresoult tr = null;
	public static List<stepresult> sr = null;	
	//记录单步日志
	public static void InitializationResult(){
			//初始化测试结果
			 tr = new theresoult();
			 sr = new ArrayList<stepresult>();	
	}
	//记录test case日子
	public static void writeResult(String dirPathname) {
		tr.setCaseresult("pass");
		for (stepresult srt : sr) {
			if(srt.getStepResult().equals("failed")){
				tr.setCaseresult("failed");
				break;
			}
		}
		HtmlDoc hd = new HtmlDoc(dirPathname);
		
		action.Total++;
		if (tr.getCaseresult().equals("pass")) {
			action.Passed++;
		} else {
			action.Failed++;
		}
		hd.InsertHtml(tr.getCaseID(), tr.getCaseName(),tr.getDescribe(), tr.getCaseresult());
		int i = 0;
		for (stepresult srt : sr) {
			i++;
			hd.Htmldectail(tr.getCaseID(),i+"", srt.getStepActiom(), srt.getStepResult(), srt.getStepLog(),srt.getTec(), tr.getCaseName());
		}
	}
	

}

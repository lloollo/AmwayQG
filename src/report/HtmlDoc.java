package report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import domain.testcase;
import forobject.ReadXml;
import io.appium.java_client.AppiumDriver;

public class HtmlDoc {
	protected String DirPath = "";
	protected String IndexList = "<table width=\"90%\" border=\"1\" cellspacing=\"1\" cellpadding=\"8\" style=\"table-layout:fixed;border: #000000; border-style: solid; border-width: 1px \">"
			+ "					<tr><td>$CaseID</td><td>$TaskName</td><td>$Describe</td>"
			+ "					<td onclick=\"toggle_child_visibility('$CaseSummIds');\" style=\"font-weight:bold;\">"
			+ "					<font color=\"$color\">$TestResult</font></td></tr> ";
	//protected String IndexList = "<tr><td>$CaseID</td><td>$TaskName</td><td>$Describe</td><td onclick=\"toggle_child_visibility('$CaseSummIds');\" style=\"font-weight:bold;\"><font color=\"$color\">$TestResult</font></td></tr>";;
	protected String IndexListChild = "<table id=\"&testcaseid\" width=\"90%\" class=\"suite\"style=\"display: none;\">"
			+ " <tr> <td class=\"subtitle\" width = \"15%\">Sn</td>	"
			+ "<td class=\"subtitle\" width = \"15%\">action</td> "
			+ "<td class=\"subtitle\" width = \"15%\">Result</td> "
			+ "<td class=\"subtitle\" width = \"55%\">Log</td> "
			+ "</tr>";
		
	protected String IndexListDetailModel = "<tr>"
			+ "<td class=\"centertext\" >$Teststep</td>"
			+ "<td class=\"detailtext\" >$dotheacotion</td>"
			+ "<td class=\"passedcentertext\" style=\"font-weight:bold;\"><a href=\"$href\"> <font color=\"$color\"> $testresult </font></a></td>"
			+ "<td class=\"detailtext\" >$testlogs</td>"
			+ "</tr>";
	String IndexModel = "";
	protected String DetailModel = "";
	protected String IndexFile = "";
	protected String DetailFile = "";
	protected String CsvFile = "";

	protected String ProjectName = "";
	protected String HttpPath = "http://127.0.0.1/";
	protected String ScriptPath = "http://127.0.0.1/";
	protected String TestDate = "2013-01-16";
	protected String Summary = "";
	protected int Total = 0;
	protected int Passed = 0;
	protected int Failed = 0;

	protected String CaseID = "";
	protected String TaskName = "";
	protected String TestResult = "";
	protected String href = "";
	protected String color = "";
	protected String describe="";
	
	protected String Precondition = "";
	protected String Remarks = "none";

	public String HomePath;
	private String testTime;

	public String getTestTime() {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH.mm.ss");
		this.testTime = dateFormat.format(now);
		return this.testTime;
	}

	public HtmlDoc(String dirPathname) {
		this.ProjectName = EtcIO.readValue("HtmlDoc.ProjectName")+"//"+(String) ReadXml.readxml("action").get("platformName");
		this.HomePath = System.getProperty("user.dir")+"/reports/";//EtcIO.readValue("HtmlDoc.HomePath");

		this.IndexModel = EtcIO.readValue("HtmlDoc.IndexModel");
		this.DetailModel = EtcIO.readValue("HtmlDoc.DetailModel");
		this.ScriptPath = EtcIO.readValue("HtmlDoc.ScriptPath");
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.TestDate = dateFormat.format(now);

		//this.DirPath = EtcIO.readValue("HtmlDoc.DirPath") + "\\"+ this.TestDate;
		//this.DirPath = System.getProperty("user.dir")+"/reports/"+ this.TestDate;
		//this.HttpPath = EtcIO.readValue("HtmlDoc.HttpPath") + "/"+ this.TestDate;
		//this.HttpPath =this.DirPath = System.getProperty("user.dir")+"/reports/"+ this.TestDate;
		this.HttpPath ="";
		this.DirPath = dirPathname;
		this.IndexFile = this.DirPath + "/" + "index.html";
		this.CsvFile = this.DirPath + "/" + "test_result.txt";
		try {
			String temp = EtcIO.readValue("HtmlDoc.IndexList");
			if (temp != null && temp.equals("")) {
				this.IndexList = temp;
			}
		} catch (Exception e) {
		}

		
	}
	public void dirPathmkdirs() {
		File dirPath = new File(this.DirPath);
		if (dirPath.exists()) {
			String  distFolder = this.DirPath + ".bak." + this.getTestTime();
			try {
				moveFile(this.DirPath, distFolder);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				dirPath.delete();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dirPath.mkdirs();
		String[] search = new String[] { "$ProjectName", "$HttpPath",
				"$ScriptPath", "$TestDate" };
		String[] replace = new String[] { this.ProjectName, this.HttpPath,
				this.ScriptPath, this.TestDate };
		
		EtcIO.ReplaceContent(this.IndexModel, this.IndexFile, search, replace);
		
	}
	public void InsertHtml(String CaseIDs,String TaskName,String describe,String TestResult) {
		this.Total++;
		this.CaseID = this.ProjectName + "-TEST-";
		if (this.Total < 10) {
			this.CaseID += "000" + this.Total;
		} else if (this.Total < 100) {
			this.CaseID += "00" + this.Total;
		} else if (this.Total < 1000) {
			this.CaseID += "0" + this.Total;
		} else {
			this.CaseID += this.Total;
		}
		this.TestResult = TestResult;
		if (this.TestResult.trim().toLowerCase().equals("pass")) {
			this.Passed++;
			this.color = "GREEN";
		} else {
			this.Failed++;
			this.color = "RED";
		}
		this.TaskName = TaskName;
		this.describe = describe;
		//this.href = TaskName + "-" + this.CaseID+ ".html";
		
		String temprow = this.IndexList.replace("$CaseID", CaseIDs)
				.replace("$TaskName", this.TaskName)
				.replace("$Describe", this.describe)
				.replace("$color", this.color)
				.replace("$TestResult", this.TestResult)
				.replace("$CaseSummIds", CaseIDs+"CaseSu");
		EtcIO.appendMethodB(this.IndexFile, temprow);
	}

	public void CompleteCount(int total2, int passed2, int failed2) {
		System.out.println(total2+"======="+passed2+"======"+failed2);
		String[] search = new String[] { "$Total", "$Passed", "$Failed" };
		String[] replace = new String[] { "" + total2, "" + passed2,
				"" + failed2 };
		EtcIO.ReplaceContent(this.IndexFile, this.IndexFile, search, replace);
		this.Summary = "PassRate: "
				+ String.format("%.2f",
						((double) passed2 / total2) * 100)
				+ "% \t CompleteRate: 100%";
		EtcIO.ReplaceContent(this.IndexFile, this.IndexFile,
				new String[] { "$Summary" }, new String[] { this.Summary });
	}

	public void ScreenCapture() {
		if (!this.DetailFile.equals("")) {
			SnapShot.screenShoot(this.DirPath, this.CaseID + ".png",
					this.DetailFile);
		}
	}

	public void ScreenCapture(WebDriver driver) {
		if (!this.DetailFile.equals("")) {
			SnapShot.appendSnapShot(this.DirPath, this.CaseID + ".png",
					this.DetailFile);
		}
	}

	public void ScreenCapture(String imagePath) {
		if (!this.DetailFile.equals("")) {
			SnapShot.appendSnapShotToLogFile(imagePath, this.DetailFile);
		}
	}
	public  void moveFile(String resFilePath, String distFolder) throws IOException {
		File resFile = new File(resFilePath);  
		File distFile = new File(distFolder); 
		if (resFile.isDirectory()) {     
			FileUtils.moveDirectoryToDirectory(resFile, distFile, true); 
			} else if (resFile.isFile()) {    
				FileUtils.moveFileToDirectory(resFile, distFile, true);            
				}       
		} 
	
	public void Htmldectail( String  CaseIDs,String Teststeps,String Testaction,String TestResult,String Testlog, testcase tec, String getCaseName) {
		this.TestResult = TestResult;
		if (this.TestResult.trim().toLowerCase().equals("pass")) {
			this.color = "GREEN";
		} else {
			this.color = "RED";
		}		
		//this.href = TaskName + "-" + this.CaseID+ ".html";
		//this.DetailFile = this.DirPath + "/" + TaskName + "-" + this.CaseID	+ ".html";
		String DetailFilede = CaseIDs + "-" + Teststeps	+ ".html";
		String temprow = this.IndexListDetailModel.replace("$Teststep", Teststeps)
				.replace("$dotheacotion",Testaction)
				.replace("$color", this.color)
				.replace("$testresult", this.TestResult)
				.replace("$testlogs", Testlog)
				.replace("$href", DetailFilede);
		if (Teststeps.equals("1")) {
			EtcIO.appendMethodB(this.IndexFile, this.IndexListChild.replace("&testcaseid", CaseIDs+"CaseSu"));
		}
		EtcIO.appendMethodB(this.IndexFile, temprow);
		String DetailFile = this.DirPath + "/" + CaseIDs + "-" + Teststeps	+ ".html";
		String[] search = new String[] { 
				"$CaseID", "$TaskName", "$TestTime",
				"$StepName", "$color", "$TestResult",
				"$StepsID", "$CaseExpected", "$Caseaction",
				"$Caselog","$href"};
		String[] replace = new String[] { 
				CaseIDs, getCaseName,this.TestDate, 
				tec.getCaseStep(), this.color,this.TestResult,
				Teststeps,tec.getCaseExpected(),tec.getCaseAction(),
				Testlog, "index.html" };//this.HttpPath + 
		EtcIO.ReplaceContent(this.DetailModel, DetailFile, search, replace);
		SnapShot.appendSnapShot(this.DirPath,CaseIDs + "-" + Teststeps+".png", DetailFile);
		
		
//		EtcIO.AppendContent(this.CsvFile, this.CaseID + "`" + this.TaskName
//				+ "`" + this.TestSummary + "`" + this.Precondition + "`"
//				+ this.Steps + "`" + this.Expects + "`" + this.getTestTime()
//				+ "`" + this.Results + "`" + this.TestResult + "`"
//				+ this.Remarks + "\r\n");
				
	}
	public void mkdirsCaseDitel() {
		String[] search = new String[] { "$ProjectName", "$HttpPath",
				"$ScriptPath", "$TestDate" };
		String[] replace = new String[] { this.ProjectName, this.HttpPath,
				this.ScriptPath, this.TestDate };
		
		EtcIO.ReplaceContent(this.DetailModel, DetailFile, search, replace);
		
	}
}

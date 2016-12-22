package forobject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.customEle;
import domain.testcase;
import domain.testplan;
import jxl.*;
public class formexcel<StringSplitOptions>{ 
	public static Map<String, ArrayList<testcase>> map1 = formexcelSheetgetCase();
	/*
	 *解析excel，
	 *每一行解析到list中，
	 *每一个case再存放到Map中
	 */
	//static String CaseExcel = "conf/"+ReadXml.readxml("action").get("Excel-name")+".xls";
	static String CaseExcel = System.getProperty("user.dir")+"Case/"+ReadXml.readxml("action").get("Excel-name")+".xls";
	//获取Case
	public static Map formexcelSheetgetCase() { 	
        int i;     
        Workbook book;
        Sheet sheet;
        //Excel列
        Cell cell1,cell2,cell3,cell4,cell5,cell6,cell7;
        Map<String, ArrayList<testcase>> map =  new HashMap<String,ArrayList<testcase>>();
        Map<String, ArrayList<testcase>> mapcation =  new HashMap<String,ArrayList<testcase>>();
        try { 
//        	if(ReadXml.actiononMobile.contains("Android")){
//        		book= Workbook.getWorkbook(new File(CaseExcel));   
//        	}else {
        		book= Workbook.getWorkbook(new File(CaseExcel));    
//        	}        
        	String temp = "Case";
            sheet=book.getSheet(temp);  
            i=1;
            ArrayList<testcase> lisetc = new ArrayList<testcase>();
            while(true)
            {            	
            	testcase tc = new testcase();
                //获取每一行的单元格 
            	cell1=sheet.getCell(0,i);//（列，行）
                cell2=sheet.getCell(1,i);
                cell3=sheet.getCell(2,i);
                cell4=sheet.getCell(3,i);
                cell5=sheet.getCell(4,i);
                cell6=sheet.getCell(5,i);
                cell7=sheet.getCell(6,i);
                if("".equals(cell1.getContents())&&
                   "".equals(cell2.getContents())&&
                   "".equals(cell3.getContents())&&
                   "".equals(cell4.getContents())&&
                   "".equals(cell5.getContents())&&
                   "".equals(cell6.getContents())&&
                   "".equals(cell7.getContents())){   //如果读取的数据为空
                    break;
                }
                if(!"".equals(cell1.getContents())){   //如果读取的数据不为空
                	lisetc = new ArrayList<testcase>();  
                	temp = cell1.getContents();
                	tc.setCaseID(cell1.getContents());
	                tc.setCaseTitle(cell2.getContents());
	                tc.setCaseStep(cell3.getContents());
	                tc.setCaseExpected(cell4.getContents());
	                tc.setCaseAction(cell5.getContents());
	                tc.setCaseElement(cell6.getContents());
	                tc.setCaseresult(cell7.getContents());
	                lisetc.add(tc);
                    map.put(temp, lisetc);
                    //System.out.println(map);
                }
                if("".equals(cell1.getContents())){   //如果读取的数据为空
                	tc.setCaseID(temp);
	                tc.setCaseTitle(cell2.getContents());
	                tc.setCaseStep(cell3.getContents());
	                tc.setCaseExpected(cell4.getContents());
	                tc.setCaseAction(cell5.getContents());
	                tc.setCaseElement(cell6.getContents());
	                tc.setCaseresult(cell7.getContents());
	                lisetc.add(tc);
                    map.put(temp, lisetc);    
                }
                //System.out.println(cell1.getContents()+"\t"+cell2.getContents()+"\t"+cell3.getContents()); 
                //System.out.println(map);
                i++;
            }
            book.close(); 
        }
        catch(Exception e)  { }     
        //控制Case执行开关
        Map<String, testplan> maptestplan = gettestPlan();
        for (Map.Entry<String, testplan> entry : maptestplan.entrySet()) {
        	if(entry.getValue().getCaseState().equals("ON")){
        		mapcation.put(entry.getValue().getCaseNo(),map.get(entry.getValue().getCaseNo()));
        	}
		}
        
        TreeMap tm = new TreeMap(mapcation);
		Iterator ii =tm.descendingKeySet().iterator();	
        return tm;
    }
	//获取元素
	public static Map formexcelSheetgetElement() { 	
        int i,j;
        Sheet sheet;
        Workbook book;
        Cell cell1,cell2;
        Map<String, String> map =  new HashMap<String,String>();
        try { 
        	book= Workbook.getWorkbook(new File(CaseExcel));     	
        	sheet=book.getSheet("Element");  	
            i=1;  
            j=1;
            if(ReadXml.actiononMobile.contains("IOS")){
        		j=1;
            }else {
            	j=2;		
        	}
            while(true)
            {     
            	cell1=sheet.getCell(0,i);//（列，行）
            	cell2=sheet.getCell(j,i);     	   
                //获取每一行的单元格  
                if("".equals(cell1.getContents())&&
                   "".equals(cell2.getContents())
                		){   //如果读取的数据为空
                    break;
                }
                if(!"".equals(cell1.getContents())){   //如果读取的数据不为空 
                    map.put(cell1.getContents(), cell2.getContents());
                }
                i++;
            }
            book.close(); 
        }
        catch(Exception e)  {         	
        }
        return map;
    }
	public static Map<String,HashMap<String, ArrayList<testcase>>> formexcelSheetgetOther(String SheetF) { 	
		  int i;
	        Sheet sheet;
	        Workbook book;
	        Cell cell1,cell2,cell3,cell4,cell5,cell6;
	        Map<String,HashMap<String, ArrayList<testcase>>> customMap = new HashMap<String,HashMap<String, ArrayList<testcase>>>();
	        HashMap<String, ArrayList<testcase>> platform =  new HashMap<String,ArrayList<testcase>>();
	        ArrayList<testcase> lisetc = new ArrayList<testcase>();
	        try { 
	        	book= Workbook.getWorkbook(new File(CaseExcel));//获取excel           
	        	String platformvalue = "platformvalue";
	        	String lisetcvalue = "lisetcvalue";
	            sheet=book.getSheet(SheetF);  //获取excel里的Sheet
	            i=1; 
	            while(true)
	            {            	
	            	testcase tc = new testcase();
	                //获取每一行的单元格 
	                cell1=sheet.getCell(0,i);//（列，行）
	                cell2=sheet.getCell(1,i);
	                cell3=sheet.getCell(2,i);
	                cell4=sheet.getCell(3,i);
	                cell5=sheet.getCell(4,i);
	                cell6=sheet.getCell(5,i);
	                if("".equals(cell1.getContents())&&
	                   "".equals(cell2.getContents())&&
	                   "".equals(cell3.getContents())&&
	                   "".equals(cell4.getContents())&&
	                   "".equals(cell5.getContents())&&
	                   "".equals(cell6.getContents())){   //如果读取的数据为空
	                    break;
	                }
	                if(!"".equals(cell1.getContents())){   //如果读取的数据不为空  
	                	platform =  new HashMap<String,ArrayList<testcase>>();
	                	lisetc = new ArrayList<testcase>(); 
	                	platformvalue = cell1.getContents();
		                customMap.put(platformvalue, platform);
	                } 
	                if(!"".equals(cell2.getContents())){   //如果读取的数据不为空                 	
	                	lisetc = new ArrayList<testcase>(); 
	                	lisetcvalue = cell2.getContents();
		                tc.setCaseTitle(cell2.getContents());
		                tc.setCaseStep(cell3.getContents());
		                tc.setCaseExpected(cell4.getContents());
		                tc.setCaseAction(cell5.getContents());
		                tc.setCaseElement(cell6.getContents());
		                lisetc.add(tc);
		                platform.put(lisetcvalue, lisetc);
	                }
	                if("".equals(cell2.getContents())){   //如果读取的数据为空   
	                	tc.setCaseStep(cell3.getContents());
		                tc.setCaseExpected(cell4.getContents());
		                tc.setCaseAction(cell5.getContents());
		                tc.setCaseElement(cell6.getContents());           
		                lisetc.add(tc);
		                platform.put(lisetcvalue, lisetc);
	                }
	                i++;
	            }
	            book.close(); 
	        }
	        catch(Exception e){
	        }
	        return customMap;
	}
	public static Map gettestPlan() { 	
        int i,j;
        Sheet sheet;
        Workbook book;
        Cell cell1,cell2,cell3;
        Map<String, testplan> map =  new HashMap<String,testplan>();
        testplan tp = new testplan();
        try { 
        	book= Workbook.getWorkbook(new File(CaseExcel));     	
        	sheet=book.getSheet("testplan");  	
            i=1;
            while(true)
            {     
            	tp = new testplan();
            	cell1=sheet.getCell(0,i);//（列，行）
            	cell2=sheet.getCell(1,i);     	
            	cell3=sheet.getCell(2,i);
                //获取每一行的单元格  
                if("".equals(cell1.getContents())&&
                   "".equals(cell2.getContents())&&
                   "".equals(cell3.getContents())
                		){   //如果读取的数据为空
                    break;
                }
                if(!"".equals(cell1.getContents())){   //如果读取的数据不为空 
                	tp.setCaseNo(cell1.getContents());
                	tp.setCaseName(cell2.getContents());
                	tp.setCaseState(cell3.getContents());
                    map.put(cell1.getContents(),tp);
                }
                i++;
            }
            book.close(); 
        }
        catch(Exception e)  {         	
        }
        return map;
    }
}

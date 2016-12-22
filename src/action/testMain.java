package action;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Toconfigure.configures;
import domain.testcase;
import forobject.ReadXml;
import forobject.formexcel;
import forobject.toolsforObj;

public class testMain {
			public static void main(String[] args) {
				String  getCaseElem = "sdaj,asd,asd;*sjdka";
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
				    		tempover = tempover+s;
						}
				    	temp = tempover;
			    	}
			    	
			    	if(textTextD.contains("*")){
						//configures.forTempString.put(textTextD,temp);	
						System.out.println(temp+"========"+textTextD);
					}			    		
		    	}
			}
}

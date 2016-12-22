package jdbc;

import java.sql.*;

import Toconfigure.configures;
import forobject.formexcel;   
public class Amojdbc {   
	public static void main(String[] args) { 
		String url="jdbc:oracle:thin:@10.143.169.45:1521/wechatqa";//orac表示你安装oracle时取的名字   
		String userName="amwayecard";  
		String passWord="amwayecard";   
		Connection conn=null;   
		try{    
			Class.forName("oracle.jdbc.OracleDriver");    
			conn=DriverManager.getConnection(url,userName,passWord);    
			System.out.println("连接成功");   
			
			
//			PreparedStatement Statement=conn.prepareStatement("select * from POS_Order.IFTB_Order_Header_HB WHERE ID = '86'");
//			//Statement.setString(1,"86");
//			ResultSet rs2 = Statement.executeQuery();		
//			rs2.next();
//			System.out.println(rs2.getString(4));

			
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from mstb_customer_master;");
			rs.next();
			System.out.println(rs.getString(0));
			
			
//			PreparedStatement Statement=conn.prepareStatement("INSERT INTO user VALUES(?,?)");
//		       for(int i=0;i<100;i++)        //定义个100次的循环，往表里插入一百条信息。
//		      {
//		           Statement.setString(1,"chongshi"+i);
//		           Statement.setString(2,"bo"+i);
//		           Statement.executeQuery();
//		      }
		       
		       
		       
			rs.close();
			st.close();
			conn.close();
			
		}
		catch(Exception e){    
			e.printStackTrace();   
		}   
	   
		 
	}
	
	
	public static String getDataFromJdbcpos(String sqls, String[] elementlistelemes){
		String getData=null;
		String url="jdbc:oracle:thin:@10.140.210.185:1521:cnpos5qa";//orac表示你安装oracle时取的名字   
		String userName="Capgemini";  
		String passWord="posqa321";   
		Connection conn=null;   
		try{    
			Class.forName("oracle.jdbc.OracleDriver");    
			conn=DriverManager.getConnection(url,userName,passWord);    
			System.out.println("连接成功");   
			PreparedStatement Statement=conn.prepareStatement(sqls);
			if(elementlistelemes.length>1){
				for (int i = 1; i < elementlistelemes.length; i++) {
					String elementsql=elementlistelemes[i];
					if(elementsql.contains("*")){
						elementsql  = configures.forTempString.get(elementsql);	
					}else if(elementsql.contains("#")){
						elementsql = (String) formexcel.formexcelSheetgetElement().get(elementsql.substring(elementsql.indexOf("#")+1));	
					}
					Statement.setString(i,elementsql);
				}			
			}
			
			ResultSet rs2 = Statement.executeQuery();		
			rs2.next();
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(sqls);
//			rs.next();
			try {
				getData = rs2.getString(1);	
			} catch (Exception e) {
				System.out.println("查询结果为空");
				getData="";
			}
				
			rs2.close();
			Statement.close();
			conn.close();	
		}
		catch(Exception e){    
			System.out.println("数据库查询失败。。。");
			//e.printStackTrace();   
		} 
		
		return getData;
	}
	public static String getDataFromJdbcsoa(String sqls, String[] elementlistelemes){
		String getData=null;
		String url="jdbc:oracle:thin:@10.143.169.119:1521:inquiry";//orac表示你安装oracle时取的名字   
		String userName="amway";  
		String passWord="admin";   
		Connection conn=null;   
		try{    
			Class.forName("oracle.jdbc.OracleDriver");    
			conn=DriverManager.getConnection(url,userName,passWord);    
			System.out.println("连接成功");   
			PreparedStatement Statement=conn.prepareStatement(sqls);
			if(elementlistelemes.length>1){
				for (int i = 1; i < elementlistelemes.length; i++) {
					String elementsql=elementlistelemes[i];
					if(elementsql.contains("*")){
						elementsql  = configures.forTempString.get(elementsql);	
					}else if(elementsql.contains("#")){
						elementsql = (String) formexcel.formexcelSheetgetElement().get(elementsql.substring(elementsql.indexOf("#")+1));	
					}
					Statement.setString(i,elementsql);
				}			
			}
			
			ResultSet rs2 = Statement.executeQuery();		
			rs2.next();
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(sqls);
//			rs.next();
			try {
				getData = rs2.getString(1);	
			} catch (Exception e) {
				System.out.println("查询结果为空");
				getData="";
			}
				
			rs2.close();
			Statement.close();
			conn.close();	
		}
		catch(Exception e){    
			System.out.println("数据库查询失败。。。");
			//e.printStackTrace();   
		} 
		
		return getData;
	}
	public static String getDataFromJdbcecard(String sqls, String[] elementlistelemes){
		String getData=null;
		String url="jdbc:oracle:thin:@10.143.169.45:1521/wechatqa";//orac表示你安装oracle时取的名字   
		String userName="amwayecard";  
		String passWord="amwayecard";   
		Connection conn=null;   
		try{    
			Class.forName("oracle.jdbc.OracleDriver");    
			conn=DriverManager.getConnection(url,userName,passWord);    
			System.out.println("连接成功");   
			PreparedStatement Statement=conn.prepareStatement(sqls);
			if(elementlistelemes.length>1){
				for (int i = 1; i < elementlistelemes.length; i++) {
					String elementsql=elementlistelemes[i];
					if(elementsql.contains("*")){
						elementsql  = configures.forTempString.get(elementsql);	
					}else if(elementsql.contains("#")){
						elementsql = (String) formexcel.formexcelSheetgetElement().get(elementsql.substring(elementsql.indexOf("#")+1));	
					}
					Statement.setString(i,elementsql);
				}			
			}
			
			ResultSet rs2 = Statement.executeQuery();		
			rs2.next();
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(sqls);
//			rs.next();
			try {
				getData = rs2.getString(1);	
			} catch (Exception e) {
				System.out.println("查询结果为空");
				getData="";
			}
				
			rs2.close();
			Statement.close();
			conn.close();	
		}
		catch(Exception e){    
			System.out.println("数据库查询失败。。。");
			//e.printStackTrace();   
		} 
		
		return getData;
	}
}
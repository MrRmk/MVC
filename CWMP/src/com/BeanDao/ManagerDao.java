package com.BeanDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaBean.Manager;


public class ManagerDao {

	public List<Manager> QueryAll(){
		List<Manager> list =new ArrayList<Manager>();
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {	
			System.out.println("连接前！");
			Class.forName(driver);
			System.out.println("连接后！");
			conn = DriverManager.getConnection(url,"sa","314159");	
			stmt = conn.createStatement();
			String sql="select * from Manager";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Manager m = new Manager();
				m.setMno(rs.getString(1));
				m.setMname(rs.getString(2));
				m.setMsex(rs.getString(3));
				m.setMpassword(rs.getString(4));
				list.add(m);
			}	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		}finally{			
			try{	
				if( rs!=null ){
					rs.close();	
				}
				if( stmt!=null ){
					stmt.close();		
				}
				if( conn!=null ){
					conn.close();					
				}
			}catch (SQLException e){
	            e.printStackTrace(); 
	        } 
		}
		System.out.println("管理员数据库连接成功！");
		return list;
	}
	
	public Manager QueryByMno(String Mno){
		
		Manager m = null;
		//连接数据库，获取管理员表中的数据
		List<Manager> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Mno.equals(list.get(i).getMno().toString()) ){
				//查找管理员表中与(传进来的参数)Mno相等的这条记录
				m = list.get(i);
			}
		}
		return m;	
	}
}

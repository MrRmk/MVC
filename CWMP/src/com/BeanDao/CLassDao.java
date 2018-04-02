package com.BeanDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaBean.CLass;

public class CLassDao {
	public List<CLass> QueryAll(){
		List<CLass> list =new ArrayList<CLass>();
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
			String sql="select * from CLass";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				CLass cl = new CLass();
				cl.setCLno(rs.getString(1));
				cl.setCLnumber(rs.getInt(2));
				cl.setCLdept(rs.getString(3));
				list.add(cl);
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
		System.out.println("班级表数据库连接成功！");
		return list;
	}
	
	public CLass QueryByCLno(String CLno){
		
		CLass cl = new CLass();
		//连接数据库，获取班级表中的数据
		List<CLass> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( CLno.equals(list.get(i).getCLno().toString()) ){
				//查找班级表中与(传进来的参数)CLno相等的这条记录
				cl = list.get(i);
			}
		}
		return cl;	
	}
}

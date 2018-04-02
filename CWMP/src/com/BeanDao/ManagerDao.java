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
			System.out.println("����ǰ��");
			Class.forName(driver);
			System.out.println("���Ӻ�");
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
			System.out.print("SQL Server����ʧ�ܣ�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("SQL Server����ʧ�ܣ�");
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
		System.out.println("����Ա���ݿ����ӳɹ���");
		return list;
	}
	
	public Manager QueryByMno(String Mno){
		
		Manager m = null;
		//�������ݿ⣬��ȡ����Ա���е�����
		List<Manager> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Mno.equals(list.get(i).getMno().toString()) ){
				//���ҹ���Ա������(�������Ĳ���)Mno��ȵ�������¼
				m = list.get(i);
			}
		}
		return m;	
	}
}

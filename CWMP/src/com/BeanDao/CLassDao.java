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
			System.out.println("����ǰ��");
			Class.forName(driver);
			System.out.println("���Ӻ�");
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
		System.out.println("�༶�����ݿ����ӳɹ���");
		return list;
	}
	
	public CLass QueryByCLno(String CLno){
		
		CLass cl = new CLass();
		//�������ݿ⣬��ȡ�༶���е�����
		List<CLass> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( CLno.equals(list.get(i).getCLno().toString()) ){
				//���Ұ༶������(�������Ĳ���)CLno��ȵ�������¼
				cl = list.get(i);
			}
		}
		return cl;	
	}
}

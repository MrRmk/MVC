package com.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCUtils {
	String username = "sa";		//sql server的数据库的用户名
	String password = "";	//sql server的数据库的密码
	String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";//sql server的数据库的url
	String sql = "select Mno,Mpassword from Manager";//sql语句
	Connection con = null;//
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pstm = null;
	
	//数据库得到连接
	public void GetConnection(){
		try {
			//1、加载驱动程序
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2、得到链接
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//实现查询功能的方法
	public ResultSet sqlQuery(String sql){
		try {
			
			//加载驱动+得到连接
			GetConnection();
			pstm = con.prepareStatement(sql);
			System.out.println("*****数据库***查询功能***连接成功了!");
			
			rs = pstm.executeQuery();
			System.out.println("*****数据库***查询数据成功!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			sqlClose();
		}
		return rs;
	}
	
	//实现修改，添加功能的方法
	public int sqlUpdate(String sql){
		int i = 0;
		try {
			
			//加载驱动+得到连接
			GetConnection();
			pstm = con.prepareStatement(sql);
			System.out.println("*****数据库***修改/添加功能***连接成功了!");
			
			i = pstm.executeUpdate();
			if( i==1 ){
				System.out.println("*****数据库***修改/添加数据成功!");
			}else{
				System.out.println("*****数据库***修改/添加数据失败!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			sqlClose();
		}
		return i;
	}
	//实现删除功能的方法
	public void sqlDelete(String sql){
		try {
			
			//加载驱动+得到连接
			GetConnection();
			pstm = con.prepareStatement(sql);
			System.out.println("*****数据库***删除功能***连接成功了!");
			
			int i = pstm.executeUpdate();
			if( i==1 ){
				System.out.println("*****数据库***删除数据成功!");
			}else{
				System.out.println("*****数据库***删除数据失败!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			sqlClose();
		}
	}
	
	public void sqlClose(){
		
		//6、关闭连接
		try {
			if(rs != null){	
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(con != null){
				con.close();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

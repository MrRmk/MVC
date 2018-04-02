package com.BeanDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaBean.Course;

public class CourseDao {
	
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pstm  = null;
	private ResultSet rs = null;
	int i = 0;
	
	public List<Course> QueryAll(){
		List<Course> list =new ArrayList<Course>();
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
			String sql="select * from Course";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Course c = new Course();
				c.setCno(rs.getString(1));
				c.setCname(rs.getString(2));
				c.setCcredit(rs.getString(3));
				list.add(c);
			}	
			sqlClose();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		}
		System.out.println("课程表数据库连接成功！");
		return list;
	}
	//根据课程号查找
	public Course QueryByCno(String Cno){
		Course c = new Course();
		//连接数据库，获取课程表中的数据
		List<Course> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Cno.equals(list.get(i).getCno().toString()) ){
				//查找课程表中与(传进来的参数)Cno相等的这条记录
				c = list.get(i);
				System.out.println("QueryByCno inif CourseDao:"+Cno+"--"+c.getCname());
			}
			System.out.println("QueryByCno infor CourseDao:"+Cno+"--"+list.get(i).getCname()+" "+list.get(i).getCno());
		}
		return c;	
	}
	//根据课程名查询课程号
	public Course QueryByCname(String Cname){
			
			Course c = new Course();
			//连接数据库，获取课程表中的数据
			List<Course> list = QueryAll();
			for(int i=0; i<list.size(); i++){
				if( Cname.equals(list.get(i).getCname().toString()) ){
					//查找课程表中与(传进来的参数)Cno相等的这条记录
					c = list.get(i);
					System.out.println("QueryByCno inif CourseDao:"+Cname+"--"+c.getCname());
				}
				System.out.println("QueryByCno infor CourseDao:"+Cname+"--"+list.get(i).getCname()+" "+list.get(i).getCno());
			}
			return c;	
		}
	//向课程Course表中更新数据的方法
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("Course课程表插入数据成功! ");
			}else{
				System.out.println("Course课程表插入数据失败!");
			}
			//关闭连接
			sqlClose();		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	//关闭连接的方法
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

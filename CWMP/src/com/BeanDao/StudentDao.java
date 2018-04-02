package com.BeanDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DBConnection.JDBCUtils;
import com.JavaBean.Student;

public class StudentDao {
	
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pstm  = null;
	private ResultSet rs = null;
	int i = 0;
	
	public List<Student> QueryAll(){
		List<Student> list = new ArrayList<Student>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"sa","314159");
			st = con.createStatement();
			String sql = "select * from Student";
			rs = st.executeQuery(sql);
			while(rs.next()){
				Student stu = new Student();
				stu.setSno(rs.getString(1));
				stu.setSname(rs.getString(2));
				stu.setSsex(rs.getString(3));
				stu.setSpassword(rs.getString(4));
				stu.setSdept(rs.getString(5));
				stu.setCLno(rs.getString(6));
				list.add(stu);
			}
			sqlClose();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("学生数据库连接成功！");
		return list;
	}
	//通过学号查找
	public Student QueryBySno(String Sno){
		Student stu = new Student();
		//连接数据库，获取学生表中的数据
		List<Student> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Sno.equals(list.get(i).getSno()) ){
				//查找学生表中与(传进来的参数)Sno相等的这条记录
				stu = list.get(i);
			}
		}
		return stu;
	}
	//通过班级号查找
	public List<Student> QueryByCLno(String CLno){
		List<Student> li = new ArrayList<Student>();
		//连接数据库，获取学生表中的数据
		List<Student> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( CLno.equals(list.get(i).getCLno()) ){
				//查找学生表中与(传进来的参数)CLno相等的这条记录
				li.add(list.get(i));
			}
		}
		return li;
	}
	//根据姓名查询
	public Student QueryBySname(String Sname){
		Student s = new Student();
		//连接数据库，获取学生表中的数据
		System.out.println("StudentDao-QueryBySname-:Sname"+Sname);
		List<Student> list = QueryAll();System.out.println("--StudentDao--:"+list.get(0).getSno()+" "+list.size());
		for(int i=0; i<list.size(); i++){System.out.println("StudentDao-infor upif-:"+list.get(i).getSname());
			if( Sname.equals(list.get(i).getSname().toString()) ){
				//查找学生表中与(传进来的参数)Sno相等的这条记录
				s = list.get(i);
				System.out.println("StudentDao-in if-:"+s.getSno()+s.getSname());
			}
		}
		return s;
	}
	//向学生Student表中更新数据的方法
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("Student学生表插入数据成功! ");
			}else{
				System.out.println("Student学生表插入数据失败!");
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
	//用SQL语句查询学生表
	public int QueryBySql(String sql){
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);
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

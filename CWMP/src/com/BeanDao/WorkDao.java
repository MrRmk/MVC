package com.BeanDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DBConnection.JDBCUtils;
import com.JavaBean.Work;

public class WorkDao {
	public List<Work> QueryAll(){
		List<Work> list =new ArrayList<Work>();
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
			String sql="select * from Work";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Work w = new Work();
				w.setWno(rs.getString(1));
				w.setWtitle(rs.getString(2));
				w.setWcontent(rs.getString(3));
				w.setWdeadline(rs.getString(4));
				w.setTno(rs.getString(5));
				w.setCLno(rs.getString(6));
				w.setCno(rs.getString(7));
				list.add(w);
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
		System.out.println("作业表数据库连接成功！");
		return list;
	}
	
	//按作业号查询
	public Work QueryByWno(String Wno){
		
		Work w = new Work();
		//连接数据库，获取作业表中的数据
		List<Work> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Wno.equals(list.get(i).getWno().toString()) ){
				//查找作业表中与(传进来的参数)Wno相等的这条记录
				w = list.get(i);
			}
		}
		return w;	
	}
	//按主题和班级号查询作业表中的作业号
	public Work QueryByWtitleCLno(String Wtitle,String CLno){
		
		Work w = new Work();
		//连接数据库，获取作业表中的数据
		List<Work> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (Wtitle.equals(list.get(i).getWtitle().toString()))&&(CLno.equals(list.get(i).getCLno().toString())) ){
				//查找作业表中与(传进来的参数)Wtitle和CLno都相等的这条记录
				w = list.get(i);
			}
		}
		return w;	
	}
	//按教师号查询
	public List<Work> QueryByTno(String Tno){
		List<Work> wList =new ArrayList<Work>();
		//连接数据库，获取作业表中的数据
		List<Work> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tno.equals(list.get(i).getTno().toString()) ){
				//查找作业表中与(传进来的参数)Tno相等的这条记录
				wList.add(list.get(i));
			}
		}
		return wList;
		
	}
	//按班级号查询
	public List<Work> QueryByCLno(String CLno){
		List<Work> wList =new ArrayList<Work>();
		//连接数据库，获取作业表中的数据
		List<Work> li = QueryAll();
		System.out.println("li.size():"+li.size() );
		for(int i=0; i<li.size(); i++){
			if( CLno.equals(li.get(i).getCLno().toString()) ){
				//查找作业表中与(传进来的参数)CLno相等的这条记录,放到数组中
				wList.add(li.get(i));
				System.out.println("list.get(i).getCLno().toString():"+li.get(i).getCLno().toString());
			}
		}
		return wList;
		
	}
	//插入一条数据
	public int Insert_one(Work w){
		
		System.out.println("Insert_one之前");
		String sql = "insert into Work(Wno,Wtitle,Wcontent,Wdeadline,Tno,CLno,Cno) "
				+ "values('"+w.getWno()+"','"+w.getWtitle()+"','"+w.getWcontent()+"','"+w.getWdeadline()+"','"+w.getTno()+"','"+w.getCLno()+"','"+w.getCno()+"')";
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);

	}
	//按作业号删除
	public int DeleteByWno(String Wno){
		JDBCUtils jdbc = new JDBCUtils();
		String sql = "delete from Work where Wno = '"+Wno+"';";
		return jdbc.sqlUpdate(sql);
		
	}
	
}

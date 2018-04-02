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
import com.JavaBean.Ccheck;

public class CcheckDao {
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pstm  = null;
	private ResultSet rs = null;
	int i = 0;
	
	public List<Ccheck> QueryAll(){
		List<Ccheck> list = new ArrayList<Ccheck>();
		try {
			System.out.println("连接前！");
			Class.forName(driver);
			System.out.println("连接后！");
			con = DriverManager.getConnection(url,"sa","314159");
			st = con.createStatement();
			String sql = "select * from Ccheck";
			rs = st.executeQuery(sql);
			while(rs.next()){
				Ccheck ch = new Ccheck();
				ch.setSno(rs.getString(1));
				ch.setWno(rs.getString(2));
				ch.setChscore(rs.getInt(3));
				ch.setChremark(rs.getString(4));
				ch.setChtime(rs.getString(5));
				ch.setUptime(rs.getString(6));
				ch.setChanswer(rs.getString(7));
				list.add(ch);
			}
			//关闭连接
			sqlClose();

			System.out.println("批改表数据库连接成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}
	//通过学号和作业号查询表中的一条记录
	public Ccheck QueryByChno(String Sno,String Wno){
		System.out.println("函数QueryByChno执行  "+"Sno="+Sno+",Wno="+Wno);
		Ccheck ch = new Ccheck();;
		//连接数据库，获取批改表中的数据
		List<Ccheck> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getSno().toString()).equals(Sno) && (list.get(i).getWno().toString()).equals(Wno)  ){
				//查找批改表中与(传进来的参数)Sno和Wno相等的这条记录
				ch = list.get(i);
			}	
		}
		return ch;
	}
	//根据作业号查询
	public List<Ccheck> QueryByWno(String Wno){
		List<Ccheck> listCcheck = new ArrayList<>();
		//连接数据库，获取批改表中的数据
		List<Ccheck> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( ( (list.get(i).getWno().toString()).equals(Wno)  )){
				//查找批改表中与(传进来的参数)Sno和Wno相等的这条记录
				listCcheck.add( list.get(i));//System.out.println("CcheckDao:************************"+listCcheck.get(i).getSno());
			}	
		}
		return listCcheck;
	}
	//通过学号查询批改表Ccheck中的所有记录
	public List<Ccheck> QueryBySno(String Sno){
		System.out.println("函数QueryBySno执行  "+"Sno="+Sno);
		List<Ccheck> ch =  new ArrayList<Ccheck>();
		//连接数据库，获取批改表中的数据
		List<Ccheck> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getSno().toString()).equals(Sno) ){
				//查找批改表中与(传进来的参数)Sno相等的这条记录
				ch.add( list.get(i) );
			}	
		}
		return ch;
	}
	//插入一条批改记录
	public int Insert_one(Ccheck check){		
		System.out.println(check.getChscore()+check.getChremark()+check.getChtime());
		
		String sql = "update Ccheck set Chscore = '"+check.getChscore()+"',"+" Chremark= '"+check.getChremark()+"',"+" Chtime= '"+check.getChtime() +
		"' where Sno = '"+check.getSno()+"' and Wno = '"+check.getWno()+"';";
		
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);
		
	}
	//向批改表Ccheck中更新数据的方法
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("Ccheck批改表插入数据成功! ");
			}else{
				System.out.println("Ccheck批改表插入数据失败!");
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
	//6、关闭连接
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

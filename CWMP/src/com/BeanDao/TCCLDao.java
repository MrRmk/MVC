package com.BeanDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.JavaBean.TCCL;


public class TCCLDao {

	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pstm  = null;
	private ResultSet rs = null;
	int i = 0;
	public List<TCCL> QueryAll(){
		List<TCCL> list =new ArrayList<TCCL>();
		try {	
			System.out.println("连接前！");
			Class.forName(driver);
			System.out.println("连接后！");
			con = DriverManager.getConnection(url,"sa","314159");	
			st = con.createStatement();
			String sql="select * from TCCL";
			rs = st.executeQuery(sql);
			while(rs.next()){
				TCCL tc = new TCCL();
				tc.setTno(rs.getString(1));
				tc.setCLno(rs.getString(2));
				tc.setCno(rs.getString(3));
				list.add(tc);
			}
			//关闭连接
			sqlClose();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		}
		
		System.out.println("授课表数据库连接成功！");
		return list;
	}
	
	//***********有问题**************************************
	/*public TCCL QueryByTno(String Tno){
		
		TCCL tc = null;
		//连接数据库，获取授课表中的数据
		List<TCCL> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tno.equals(list.get(i).getTno().toString()) ){
				//查找授课表中与(传进来的参数)Tno相等的这条记录
				tc = list.get(i);
			}
		}
		return tc;	
	}*/
	
	//按教师号查询,返回集合
	public List<TCCL> QueryByTno(String Tno){
		int j= 0;
		//连接数据库，获取授课表中的数据
		List<TCCL> list = QueryAll();
		List<TCCL> listResult = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			if( Tno.equals(list.get(i).getTno().toString()) ){
				//查找授课表中与(传进来的参数)Tno相等的这条记录
				listResult.add(list.get(i));
				System.out.println("QueryByTno TCCLDao:"+Tno+"--"+listResult.get(j++).getCno());
			}
		}
		return listResult;	
	}
	//根据班级号查询,返回集合
	public List<TCCL> QueryByCLno(String CLno){
		int j = 0;
		//连接数据库，获取授课表中的数据
		List<TCCL> list = QueryAll();
		List<TCCL> listResult = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			if( CLno.equals(list.get(i).getCLno().toString()) ){
				//查找授课表中与(传进来的参数)Tno相等的这条记录
				listResult.add(list.get(i));
				System.out.println("QueryByCLno TCCLDao:"+CLno+"--"+listResult.get(j++).getCno());
			}
		}
		return listResult;	
	}
	//向授课TCCL表中更新数据的方法
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("TCCL授课表插入数据成功! ");
			}else{
				System.out.println("TCCL授课表插入数据失败!");
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

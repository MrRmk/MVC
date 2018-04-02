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
import com.JavaBean.Teacher;

public class TeacherDao {
	
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pstm  = null;
	private ResultSet rs = null;
	int i = 0;
	
	//查找教师Teacher表中的左右数据，返回教师表的集合list
	public List<Teacher> QueryAll(){
		List<Teacher> list = new ArrayList<Teacher>();		
		try {
			System.out.println("连接前！");
			Class.forName(driver);
			System.out.println("连接后！");
			con = DriverManager.getConnection(url,"sa","314159");
			st = con.createStatement();
			String sql = "select * from Teacher";
			rs = st.executeQuery(sql);
			while(rs.next()){
				Teacher t = new Teacher();
				t.setTno(rs.getString(1));
				t.setTname(rs.getString(2));
				t.setTsex(rs.getString(3));
				t.setToffice(rs.getString(4));
				t.setTpassword(rs.getString(5));
				t.setTdept(rs.getString(6));
				list.add(t);
			}
			//关闭连接
			sqlClose();

			System.out.println("教师数据库连接成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}
	
	//在教师Teacher表中查找与教师号Tno相同的一条数据记录，保存在t中返回
	public Teacher QueryByTno(String Tno){
		System.out.println("函数QueryByTno执行  "+Tno);
		Teacher t = new Teacher();
		//连接数据库，获取教师表中的数据
		List<Teacher> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getTno().toString()).equals(Tno) ){
				//查找教师表中与(传进来的参数)Tno相等的这条记录
				t = list.get(i);
			}	
		}
		return t;
	}
	
	/*public List<Teacher> QueryByTno(String Tno){
		System.out.println("函数QueryByTno执行  "+Tno);
		List<Teacher> li = new ArrayList();
		Teacher t = null;
		//连接数据库，获取教师表中的数据
		List<Teacher> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getTno().toString()).equals(Tno) ){
				//查找教师表中与(传进来的参数)Tno相等的这条记录
				t = list.get(i);
				li = (List<Teacher>) t;
			}	
		}
		return li;
	}*/
	//插入一条数据
	public int Update_one(Teacher t){
		
		System.out.println("Update_one之前");
		String sql = "update Teacher set Toffice='"+t.getToffice()+"',Tpassword='"+t.getTpassword()+"',Tdept='"+t.getTdept()+"'  where Tno='"+t.getTno()+"'; ";
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);

	}
	//向教师Teacher表中更新数据的方法
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("Teacher教师表插入数据成功! ");
			}else{
				System.out.println("Teacher教师表插入数据失败!");
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

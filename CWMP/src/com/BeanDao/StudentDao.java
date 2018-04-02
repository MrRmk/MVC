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
		System.out.println("ѧ�����ݿ����ӳɹ���");
		return list;
	}
	//ͨ��ѧ�Ų���
	public Student QueryBySno(String Sno){
		Student stu = new Student();
		//�������ݿ⣬��ȡѧ�����е�����
		List<Student> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Sno.equals(list.get(i).getSno()) ){
				//����ѧ��������(�������Ĳ���)Sno��ȵ�������¼
				stu = list.get(i);
			}
		}
		return stu;
	}
	//ͨ���༶�Ų���
	public List<Student> QueryByCLno(String CLno){
		List<Student> li = new ArrayList<Student>();
		//�������ݿ⣬��ȡѧ�����е�����
		List<Student> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( CLno.equals(list.get(i).getCLno()) ){
				//����ѧ��������(�������Ĳ���)CLno��ȵ�������¼
				li.add(list.get(i));
			}
		}
		return li;
	}
	//����������ѯ
	public Student QueryBySname(String Sname){
		Student s = new Student();
		//�������ݿ⣬��ȡѧ�����е�����
		System.out.println("StudentDao-QueryBySname-:Sname"+Sname);
		List<Student> list = QueryAll();System.out.println("--StudentDao--:"+list.get(0).getSno()+" "+list.size());
		for(int i=0; i<list.size(); i++){System.out.println("StudentDao-infor upif-:"+list.get(i).getSname());
			if( Sname.equals(list.get(i).getSname().toString()) ){
				//����ѧ��������(�������Ĳ���)Sno��ȵ�������¼
				s = list.get(i);
				System.out.println("StudentDao-in if-:"+s.getSno()+s.getSname());
			}
		}
		return s;
	}
	//��ѧ��Student���и������ݵķ���
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("Studentѧ����������ݳɹ�! ");
			}else{
				System.out.println("Studentѧ�����������ʧ��!");
			}
			//�ر�����
			sqlClose();		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	//��SQL����ѯѧ����
	public int QueryBySql(String sql){
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);
	}
	//�ر����ӵķ���
	public void sqlClose(){		
		//6���ر�����
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

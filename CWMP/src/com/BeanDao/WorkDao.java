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
			System.out.println("����ǰ��");
			Class.forName(driver);
			System.out.println("���Ӻ�");
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
		System.out.println("��ҵ�����ݿ����ӳɹ���");
		return list;
	}
	
	//����ҵ�Ų�ѯ
	public Work QueryByWno(String Wno){
		
		Work w = new Work();
		//�������ݿ⣬��ȡ��ҵ���е�����
		List<Work> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Wno.equals(list.get(i).getWno().toString()) ){
				//������ҵ������(�������Ĳ���)Wno��ȵ�������¼
				w = list.get(i);
			}
		}
		return w;	
	}
	//������Ͱ༶�Ų�ѯ��ҵ���е���ҵ��
	public Work QueryByWtitleCLno(String Wtitle,String CLno){
		
		Work w = new Work();
		//�������ݿ⣬��ȡ��ҵ���е�����
		List<Work> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (Wtitle.equals(list.get(i).getWtitle().toString()))&&(CLno.equals(list.get(i).getCLno().toString())) ){
				//������ҵ������(�������Ĳ���)Wtitle��CLno����ȵ�������¼
				w = list.get(i);
			}
		}
		return w;	
	}
	//����ʦ�Ų�ѯ
	public List<Work> QueryByTno(String Tno){
		List<Work> wList =new ArrayList<Work>();
		//�������ݿ⣬��ȡ��ҵ���е�����
		List<Work> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tno.equals(list.get(i).getTno().toString()) ){
				//������ҵ������(�������Ĳ���)Tno��ȵ�������¼
				wList.add(list.get(i));
			}
		}
		return wList;
		
	}
	//���༶�Ų�ѯ
	public List<Work> QueryByCLno(String CLno){
		List<Work> wList =new ArrayList<Work>();
		//�������ݿ⣬��ȡ��ҵ���е�����
		List<Work> li = QueryAll();
		System.out.println("li.size():"+li.size() );
		for(int i=0; i<li.size(); i++){
			if( CLno.equals(li.get(i).getCLno().toString()) ){
				//������ҵ������(�������Ĳ���)CLno��ȵ�������¼,�ŵ�������
				wList.add(li.get(i));
				System.out.println("list.get(i).getCLno().toString():"+li.get(i).getCLno().toString());
			}
		}
		return wList;
		
	}
	//����һ������
	public int Insert_one(Work w){
		
		System.out.println("Insert_one֮ǰ");
		String sql = "insert into Work(Wno,Wtitle,Wcontent,Wdeadline,Tno,CLno,Cno) "
				+ "values('"+w.getWno()+"','"+w.getWtitle()+"','"+w.getWcontent()+"','"+w.getWdeadline()+"','"+w.getTno()+"','"+w.getCLno()+"','"+w.getCno()+"')";
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);

	}
	//����ҵ��ɾ��
	public int DeleteByWno(String Wno){
		JDBCUtils jdbc = new JDBCUtils();
		String sql = "delete from Work where Wno = '"+Wno+"';";
		return jdbc.sqlUpdate(sql);
		
	}
	
}

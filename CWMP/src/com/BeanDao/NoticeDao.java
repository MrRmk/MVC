package com.BeanDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.DBConnection.JDBCUtils;
import com.JavaBean.Notice;

public class NoticeDao {
		//��ѯ����
		public List<Notice> QueryAll(){
			
			List<Notice> list = new ArrayList<Notice>();
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
				String sql="select * from Notice";
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					System.out.println("rs:"+rs.getString(1));
						Notice n = new Notice();
						n.setNtNo(rs.getString(1));
						n.setNtTitle(rs.getString(2));
						n.setNtContent(rs.getString(3));
						n.setNtTime(rs.getString(4));
						n.setNtCLno(rs.getString(5));
						n.setNtTno(rs.getString(6));
						list.add(n);
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
			/*List<Notice> list =new ArrayList<Notice>();
			JDBCUtils jdbc = new JDBCUtils();
			ResultSet rs = null;
			String sql="select * from Notice";
			try{
				rs = jdbc.sqlQuery(sql);
				while(rs.next()){
					System.out.println("rs:"+rs.getString(0));
						Notice n = new Notice();
						n.setNtNo(rs.getString(1));
						n.setNtTitle(rs.getString(2));
						n.setNtContent(rs.getString(3));
						n.setNtTime(rs.getString(4));
						n.setNtCLno(rs.getString(5));
						n.setNtTno(rs.getString(6));
						list.add(n);
						}	
			} catch (SQLException e){
		            e.printStackTrace(); 
		        } */
			System.out.println("��������ݿ����ӳɹ���");
			return list;
		}
		//���༶�Ų�ѯ
		public List<Notice> QueryByCLno(String CLno){
			
			List<Notice> lt = new ArrayList<Notice>();
			Notice n = new Notice();
			//�������ݿ⣬��ȡ������е�����
			List<Notice> list = QueryAll();
			for(int i=0; i<list.size(); i++){
				if( CLno.equals(list.get(i).getNtCLno().toString()) ){
					//���ҹ��������(�������Ĳ���)�༶��CLno��ȵļ�¼
					lt.add(list.get(i));
				}
			}
			return lt;	
		}
		//������Ų�ѯ
		public Notice QueryByNtNo(String NtNo){
			
			Notice n = new Notice();
			//�������ݿ⣬��ȡ������е�����
			List<Notice> list = QueryAll();
			for(int i=0; i<list.size(); i++){
				if( NtNo.equals(list.get(i).getNtNo().toString()) ){
					//���ҹ��������(�������Ĳ���)NtNo��ȵ�������¼
					n = list.get(i);
				}
			}
			return n;	
		}
		//����ʦ�Ų�ѯ
		public List<Notice> QueryByTno(String Tno){
			List<Notice> wList =new ArrayList<Notice>();
			//�������ݿ⣬��ȡ������е�����
			List<Notice> list = QueryAll();
			for(int i=0; i<list.size(); i++){
				if( Tno.equals(list.get(i).getNtTno().toString()) ){
					//���ҹ��������(�������Ĳ���)Tno��ȵ�������¼
					wList.add(list.get(i));
				}
			}
			return wList;
			
		}
		//����һ������
		public int Insert_one(Notice n){
			//������һ�����ݵĹ����
			List<Notice> list = QueryAll();
			String NtNo = null;
			for(int i=0; i<list.size(); i++){
				NtNo = list.get(i).getNtNo();
			}
			int ntNo = Integer.parseInt(NtNo)+1;System.out.println("NtNo:before--"+ntNo);
			String ntNoStr = ntNo+"";
			System.out.println("Insert_one֮ǰ");System.out.println("NtNo:"+ntNoStr);
			String sql = "insert into Notice(NtNo,NtTitle,NtContent,NtTime,NtCLno,NtTno) "
					+ "values('"+ntNoStr+"','"+n.getNtTitle()+"','"+n.getNtContent()+"','"+n.getNtTime()+"','"+n.getNtCLno()+"','"+n.getNtTno()+"')";
			JDBCUtils jdbc = new JDBCUtils();
			return jdbc.sqlUpdate(sql);

		}
}

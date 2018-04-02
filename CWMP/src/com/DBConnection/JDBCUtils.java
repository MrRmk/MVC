package com.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCUtils {
	String username = "sa";		//sql server�����ݿ���û���
	String password = "314159";	//sql server�����ݿ������
	String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CWMP";//sql server�����ݿ��url
	String sql = "select Mno,Mpassword from Manager";//sql���
	Connection con = null;//
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pstm = null;
	
	//���ݿ�õ�����
	public void GetConnection(){
		try {
			//1��������������
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2���õ�����
			con = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//ʵ�ֲ�ѯ���ܵķ���
	public ResultSet sqlQuery(String sql){
		try {
			
			//��������+�õ�����
			GetConnection();
			pstm = con.prepareStatement(sql);
			System.out.println("*****���ݿ�***��ѯ����***���ӳɹ���!");
			
			rs = pstm.executeQuery();
			System.out.println("*****���ݿ�***��ѯ���ݳɹ�!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			sqlClose();
		}
		return rs;
	}
	
	//ʵ���޸ģ���ӹ��ܵķ���
	public int sqlUpdate(String sql){
		int i = 0;
		try {
			
			//��������+�õ�����
			GetConnection();
			pstm = con.prepareStatement(sql);
			System.out.println("*****���ݿ�***�޸�/��ӹ���***���ӳɹ���!");
			
			i = pstm.executeUpdate();
			if( i==1 ){
				System.out.println("*****���ݿ�***�޸�/������ݳɹ�!");
			}else{
				System.out.println("*****���ݿ�***�޸�/�������ʧ��!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			sqlClose();
		}
		return i;
	}
	//ʵ��ɾ�����ܵķ���
	public void sqlDelete(String sql){
		try {
			
			//��������+�õ�����
			GetConnection();
			pstm = con.prepareStatement(sql);
			System.out.println("*****���ݿ�***ɾ������***���ӳɹ���!");
			
			int i = pstm.executeUpdate();
			if( i==1 ){
				System.out.println("*****���ݿ�***ɾ�����ݳɹ�!");
			}else{
				System.out.println("*****���ݿ�***ɾ������ʧ��!");
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

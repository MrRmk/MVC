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
			System.out.println("����ǰ��");
			Class.forName(driver);
			System.out.println("���Ӻ�");
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
			//�ر�����
			sqlClose();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("SQL Server����ʧ�ܣ�");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("SQL Server����ʧ�ܣ�");
		}
		
		System.out.println("�ڿα����ݿ����ӳɹ���");
		return list;
	}
	
	//***********������**************************************
	/*public TCCL QueryByTno(String Tno){
		
		TCCL tc = null;
		//�������ݿ⣬��ȡ�ڿα��е�����
		List<TCCL> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tno.equals(list.get(i).getTno().toString()) ){
				//�����ڿα�����(�������Ĳ���)Tno��ȵ�������¼
				tc = list.get(i);
			}
		}
		return tc;	
	}*/
	
	//����ʦ�Ų�ѯ,���ؼ���
	public List<TCCL> QueryByTno(String Tno){
		int j= 0;
		//�������ݿ⣬��ȡ�ڿα��е�����
		List<TCCL> list = QueryAll();
		List<TCCL> listResult = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			if( Tno.equals(list.get(i).getTno().toString()) ){
				//�����ڿα�����(�������Ĳ���)Tno��ȵ�������¼
				listResult.add(list.get(i));
				System.out.println("QueryByTno TCCLDao:"+Tno+"--"+listResult.get(j++).getCno());
			}
		}
		return listResult;	
	}
	//���ݰ༶�Ų�ѯ,���ؼ���
	public List<TCCL> QueryByCLno(String CLno){
		int j = 0;
		//�������ݿ⣬��ȡ�ڿα��е�����
		List<TCCL> list = QueryAll();
		List<TCCL> listResult = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			if( CLno.equals(list.get(i).getCLno().toString()) ){
				//�����ڿα�����(�������Ĳ���)Tno��ȵ�������¼
				listResult.add(list.get(i));
				System.out.println("QueryByCLno TCCLDao:"+CLno+"--"+listResult.get(j++).getCno());
			}
		}
		return listResult;	
	}
	//���ڿ�TCCL���и������ݵķ���
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("TCCL�ڿα�������ݳɹ�! ");
			}else{
				System.out.println("TCCL�ڿα��������ʧ��!");
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
	//6���ر�����
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

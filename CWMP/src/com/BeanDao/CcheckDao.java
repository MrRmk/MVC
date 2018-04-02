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
			System.out.println("����ǰ��");
			Class.forName(driver);
			System.out.println("���Ӻ�");
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
			//�ر�����
			sqlClose();

			System.out.println("���ı����ݿ����ӳɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}
	//ͨ��ѧ�ź���ҵ�Ų�ѯ���е�һ����¼
	public Ccheck QueryByChno(String Sno,String Wno){
		System.out.println("����QueryByChnoִ��  "+"Sno="+Sno+",Wno="+Wno);
		Ccheck ch = new Ccheck();;
		//�������ݿ⣬��ȡ���ı��е�����
		List<Ccheck> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getSno().toString()).equals(Sno) && (list.get(i).getWno().toString()).equals(Wno)  ){
				//�������ı�����(�������Ĳ���)Sno��Wno��ȵ�������¼
				ch = list.get(i);
			}	
		}
		return ch;
	}
	//������ҵ�Ų�ѯ
	public List<Ccheck> QueryByWno(String Wno){
		List<Ccheck> listCcheck = new ArrayList<>();
		//�������ݿ⣬��ȡ���ı��е�����
		List<Ccheck> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( ( (list.get(i).getWno().toString()).equals(Wno)  )){
				//�������ı�����(�������Ĳ���)Sno��Wno��ȵ�������¼
				listCcheck.add( list.get(i));//System.out.println("CcheckDao:************************"+listCcheck.get(i).getSno());
			}	
		}
		return listCcheck;
	}
	//ͨ��ѧ�Ų�ѯ���ı�Ccheck�е����м�¼
	public List<Ccheck> QueryBySno(String Sno){
		System.out.println("����QueryBySnoִ��  "+"Sno="+Sno);
		List<Ccheck> ch =  new ArrayList<Ccheck>();
		//�������ݿ⣬��ȡ���ı��е�����
		List<Ccheck> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getSno().toString()).equals(Sno) ){
				//�������ı�����(�������Ĳ���)Sno��ȵ�������¼
				ch.add( list.get(i) );
			}	
		}
		return ch;
	}
	//����һ�����ļ�¼
	public int Insert_one(Ccheck check){		
		System.out.println(check.getChscore()+check.getChremark()+check.getChtime());
		
		String sql = "update Ccheck set Chscore = '"+check.getChscore()+"',"+" Chremark= '"+check.getChremark()+"',"+" Chtime= '"+check.getChtime() +
		"' where Sno = '"+check.getSno()+"' and Wno = '"+check.getWno()+"';";
		
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);
		
	}
	//�����ı�Ccheck�и������ݵķ���
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("Ccheck���ı�������ݳɹ�! ");
			}else{
				System.out.println("Ccheck���ı��������ʧ��!");
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

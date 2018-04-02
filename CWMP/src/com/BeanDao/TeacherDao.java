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
	
	//���ҽ�ʦTeacher���е��������ݣ����ؽ�ʦ��ļ���list
	public List<Teacher> QueryAll(){
		List<Teacher> list = new ArrayList<Teacher>();		
		try {
			System.out.println("����ǰ��");
			Class.forName(driver);
			System.out.println("���Ӻ�");
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
			//�ر�����
			sqlClose();

			System.out.println("��ʦ���ݿ����ӳɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return list;
	}
	
	//�ڽ�ʦTeacher���в������ʦ��Tno��ͬ��һ�����ݼ�¼��������t�з���
	public Teacher QueryByTno(String Tno){
		System.out.println("����QueryByTnoִ��  "+Tno);
		Teacher t = new Teacher();
		//�������ݿ⣬��ȡ��ʦ���е�����
		List<Teacher> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getTno().toString()).equals(Tno) ){
				//���ҽ�ʦ������(�������Ĳ���)Tno��ȵ�������¼
				t = list.get(i);
			}	
		}
		return t;
	}
	
	/*public List<Teacher> QueryByTno(String Tno){
		System.out.println("����QueryByTnoִ��  "+Tno);
		List<Teacher> li = new ArrayList();
		Teacher t = null;
		//�������ݿ⣬��ȡ��ʦ���е�����
		List<Teacher> list = QueryAll();
		for(int i=0; i<list.size(); i++){
			if( (list.get(i).getTno().toString()).equals(Tno) ){
				//���ҽ�ʦ������(�������Ĳ���)Tno��ȵ�������¼
				t = list.get(i);
				li = (List<Teacher>) t;
			}	
		}
		return li;
	}*/
	//����һ������
	public int Update_one(Teacher t){
		
		System.out.println("Update_one֮ǰ");
		String sql = "update Teacher set Toffice='"+t.getToffice()+"',Tpassword='"+t.getTpassword()+"',Tdept='"+t.getTdept()+"'  where Tno='"+t.getTno()+"'; ";
		JDBCUtils jdbc = new JDBCUtils();
		return jdbc.sqlUpdate(sql);

	}
	//���ʦTeacher���и������ݵķ���
	public int UpdateData(String sql){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection( url, "sa", "314159");
			pstm = con.prepareStatement(sql);
			i = pstm.executeUpdate();
			if( i == 1 ){
				System.out.println("Teacher��ʦ��������ݳɹ�! ");
			}else{
				System.out.println("Teacher��ʦ���������ʧ��!");
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

package com.Servlet.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.CourseDao;
import com.BeanDao.TCCLDao;
import com.BeanDao.TeacherDao;
import com.JavaBean.Course;
import com.JavaBean.Teacher;

/**
 * Servlet implementation class mAddTCCLServlet
 */
@WebServlet("/mAddTCCLServlet")
public class mAddTCCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
	String Tno = null;
	String Cno =  null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mAddTCCLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//�����������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		
		System.out.println("***������̿γ̱���������ݵ�mAddCourseServletҳ��**");
		
		//������ӿγ���Ϣ����(addCourse.jsp)���ݹ���������
		String Tname = request.getParameter("select1");
		String CLno = request.getParameter("select2");
		String Cname = request.getParameter("select3");
		
		//ͨ����ʦ���ҵ���ʦ��
		TeacherDao tDao = new TeacherDao();
		List<Teacher> listTeacher = tDao.QueryAll();
		for(int i=0; i<listTeacher.size(); i++){
			if( Tname.equals(listTeacher.get(i).getTname()) ){
				Tno = listTeacher.get(i).getTno();
			}	
		}
		//ͨ���γ����ҵ��γ̺�
		CourseDao c = new CourseDao();
		List<Course> listCourse = c.QueryAll();
		for(int j=0; j<listCourse.size(); j++){
			if( Cname.equals(listCourse.get(j).getCname()) ){
				Cno = listCourse.get(j).getCno();
			}
		}
		
		//�����������
		/*Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname .getBytes("iso8859-1"),"utf-8"); 
		Ccredit = new String(Ccredit .getBytes("iso8859-1"),"utf-8"); */
		
		System.out.println("***mAddTCCLServletҳ��"+Tno+CLno+Cno);
		
		
		/*//����sql���Ϳγ̱�Dao�Ķ���ͨ��CourseDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "insert into Course(Cno,Cname,Ccredit) values('"+Tno+"','"+CLno+"','"+Cno+"')";
		CourseDao stu = new CourseDao();
		//flag����ִ�е�����
		flag = stu.UpdateData(sql);
		//����תmTeacher.jspҳ��ʱ���Ͱ�Ҫ��ʾ�����ݣ���mTeacher.jsp׼����
		TeacherDao tDao = new TeacherDao();
		List<Teacher> list = tDao.QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tname.equals(list.get(i).getTname()) ){
				Tno = list.get(i).getTno(); 
			}
		}*/
		
		//����sql�����ڿα�Dao�Ķ���ͨ��TCCLDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql2 = "insert into TCCL(Tno,CLno,Cno) values('"+Tno+"','"+CLno+"','"+Cno+"')";
		TCCLDao tc = new TCCLDao();
		//flag����ִ�е�����
		flag = tc.UpdateData(sql2);
		
		//�������ݺ�ҳ�淵�ص��γ����(page/manager/addCourse.jsp)ҳ�棨��ǰҳ�棩
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/addTCCL.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.StudentDao;
import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mAddStudentServlet
 */
@WebServlet("/mAddStudentServlet")
public class mAddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mAddStudentServlet() {
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
		
		System.out.println("***�������ѧ������������ݵ�mAddStudentServletҳ��**");
		
		//�������ѧ����Ϣ����(addStudent.jsp)���ݹ���������
		String Sno = request.getParameter("Sno");
		String Sname = request.getParameter("Sname");
		String sex = request.getParameter("sex");
		String Spassword = request.getParameter("Spassword");
		String Sdept= request.getParameter("Sdept");
		String CLno = request.getParameter("CLno");
		
		//�����������
		/*Sno = new String(Sno .getBytes("iso8859-1"),"utf-8"); 
		Sname = new String(Sname .getBytes("iso8859-1"),"utf-8"); 
  		sex = new String(sex .getBytes("iso8859-1"),"utf-8"); 
  		Spassword = new String(Spassword .getBytes("iso8859-1"),"utf-8"); 
  		Sdept = new String(Sdept .getBytes("iso8859-1"),"utf-8"); 
  		CLno = new String(CLno .getBytes("iso8859-1"),"utf-8"); */
		
		System.out.println("***mAddStudentServletҳ��"+Sno+Sname+sex+Spassword+Sdept+CLno);
		
		
		//����sql���ͽ�ʦ��Dao�Ķ���ͨ��StudentDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "insert into Student(Sno,Sname,Ssex,Spassword,Sdept,CLno) values('"+Sno+"','"+Sname+"','"+sex+"','"+Spassword+"','"+Sdept+"','"+CLno+"')";
		StudentDao stu = new StudentDao();
		//flag����ִ�е�����
		flag = stu.UpdateData(sql);
		
		//�������ݺ�ҳ�淵�ص�ѧ�����(page/manager/addStudent.jsp)ҳ�棨��ǰҳ�棩
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/addStudent.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

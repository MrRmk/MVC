package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mAddTeacherServlet
 */
@WebServlet("/mAddTeacherServlet")
public class mAddTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mAddTeacherServlet() {
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
		
		/*response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");*/

		
		
		System.out.println("***�������ʦ����������ݵ�mAddTeacherServlet***");
		
		//������ӽ�ʦ��Ϣ����(addTeacher.jsp)���ݹ���������
		String Tno = request.getParameter("Tno");
		String Tname = request.getParameter("Tname");
		String sex = request.getParameter("sex");
		String Toffice = request.getParameter("Toffice");
		String Tpassword = request.getParameter("Tpassword");
		String Tdept= request.getParameter("Tdept");
		
		/*//�����������
  		Tno = new String(Tno .getBytes("iso8859-1"),"utf-8"); 
  		Tname = new String(Tname .getBytes("iso8859-1"),"utf-8"); 
  		sex = new String(sex .getBytes("iso8859-1"),"utf-8"); 
  		Toffice = new String(Toffice .getBytes("iso8859-1"),"utf-8"); 
  		Tpassword = new String(Tpassword .getBytes("iso8859-1"),"utf-8"); 
  		Tdept = new String(Tdept .getBytes("iso8859-1"),"utf-8"); */
  		
		System.out.println("mAddTeachrServletҳ��"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
		
		//����sql���ͽ�ʦ��Dao�Ķ���ͨ��TeacherDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "insert into Teacher(Tno,Tname,Tsex,Toffice,Tpassword,Tdept) values('"+Tno+"','"+Tname+"','"+sex+"','"+Toffice+"','"+Tpassword+"','"+Tdept+"')"; 
		TeacherDao tea = new TeacherDao();	
		//flag����ִ�е�����
		flag = tea.UpdateData(sql);
		
		//�������ݺ�ҳ�淵�ص���ʦ���(page/manager/addTeacher.jsp)ҳ�棨��ǰҳ�棩
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/addTeacher.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

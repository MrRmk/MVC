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
 * Servlet implementation class mDeleteStudentServlet
 */
@WebServlet("/mDeleteStudentServlet")
public class mDeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mDeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����������
		request.setCharacterEncoding("UTF-8");			
		System.out.println("***����ɾ��ѧ���������ݵ�mDeleteStudentServletҳ��***");
		
		
		String Sno = request.getParameter("Sno").toString();
		//�����������
  		Sno = new String(Sno .getBytes("iso8859-1"),"utf-8"); 		
  		System.out.println("mDeleteStudentServletҳ��"+Sno);
		
		//����sql����ѧ����Dao�Ķ���ͨ��StudentDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "DELETE FROM Student WHERE Sno='"+Sno+"'; "; 
		StudentDao stu = new StudentDao();
		//flag����ִ�е�����
		flag = stu.UpdateData(sql);
		
		//�޸����ݺ�ҳ�淵�ص�ѧ����(mStudent.jsp)ҳ��
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("mStudentServlet").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

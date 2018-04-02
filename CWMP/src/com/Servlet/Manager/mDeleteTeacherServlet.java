package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mDeleteTeacherServlet
 */
@WebServlet("/mDeleteTeacherServlet")
public class mDeleteTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mDeleteTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//�����������
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("***����ɾ����ʦ�������ݵ�mDeleteTeacherServlet***");
		
		String Tno = request.getParameter("Tno").toString();
		//�����������
  		Tno = new String(Tno .getBytes("iso8859-1"),"utf-8"); 		
  		System.out.println("mDeleteTeacherServletҳ��"+Tno);
		
		//����sql���ͽ�ʦ��Dao�Ķ���ͨ��TeacherDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "DELETE FROM Teacher WHERE Tno='"+Tno+"'; "; 
		TeacherDao tea = new TeacherDao();
		//flag����ִ�е�����
		flag = tea.UpdateData(sql);
		
		//�޸����ݺ�ҳ�淵�ص���ʦ��(mTeacher.jsp)ҳ��
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("mTeacherServlet").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

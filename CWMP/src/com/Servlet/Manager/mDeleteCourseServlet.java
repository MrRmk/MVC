package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.CourseDao;

/**
 * Servlet implementation class mDeleteCourseServlet
 */
@WebServlet("/mDeleteCourseServlet")
public class mDeleteCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mDeleteCourseServlet() {
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
		System.out.println("***����ɾ���γ̱������ݵ�mDeleteCourseServletҳ��***");
				
		/*String Tno = request.getParameter("Tno").toString();
		String CLno = request.getParameter("CLno").toString();*/
		String Cno = request.getParameter("Cno").toString();
		//�����������
  		//Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 		
  		System.out.println("mDeleteCourseServletҳ��"+Cno);
		
		//����sql���Ϳγ̱�Dao�Ķ���ͨ��CourseDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "DELETE FROM Course WHERE Cno='"+Cno+"'; "; 
		CourseDao cDao = new CourseDao();
  		//����sql�����ڿα�TCCLDao�Ķ���ͨ��TCCLDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		/*String sql = "DELETE FROM TCCL WHERE Tno='"+Tno+"' and CLno='"+CLno+"' and Cno='"+Cno+"'; "; 
		TCCLDao tc = new TCCLDao();*/
		//flag����ִ�е�����
		flag = cDao.UpdateData(sql);
		
		//�޸����ݺ�ҳ�淵�ص��γ̱�(mCourse.jsp)ҳ��
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("mCourseServlet").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

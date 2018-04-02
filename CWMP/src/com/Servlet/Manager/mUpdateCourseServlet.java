package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.CourseDao;
import com.BeanDao.StudentDao;

/**
 * Servlet implementation class mUpdateCourseServlet
 */
@WebServlet("/mUpdateCourseServlet")
public class mUpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mUpdateCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request �����������
		//request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("text/html; charset=UTF-8");
		
		System.out.println("***������̿γ̱���������ݵ�mUpdateCourseServletҳ��**");
  		
		//������ӿγ���Ϣ����(updateCourse.jsp)���ݹ���������
		String Cno = request.getParameter("Cno");
		String Cname = request.getParameter("Cname");
		String Ccredit = request.getParameter("Ccredit");
		
		//�����������
		Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname .getBytes("iso8859-1"),"utf-8"); 
		Ccredit = new String(Ccredit .getBytes("iso8859-1"),"utf-8");
  		
  		System.out.println("***mUpdateCourseServletҳ��***"+Cno+Cname+Ccredit);
		
		//����sql���Ϳγ̱�Dao�Ķ���ͨ��CourseDao�Ķ�����ø��º���Updatedata()�����뵽���ݿ�
  		String sql = "UPDATE Course SET Cname='"+Cname+"',Ccredit='"+Ccredit+"' WHERE Cno='"+Cno+"'; "; 
  		CourseDao stu = new CourseDao();
  		//flag����ִ�е�����
  		flag = stu.UpdateData(sql);
		
  		//�����Ӵ���ֵ֮ǰ�ñ���һ��(en����)
  		Cno = java.net.URLEncoder.encode(Cno,"UTF-8");//����
  		Cname = java.net.URLEncoder.encode(Cname,"UTF-8");//����
  		Ccredit = java.net.URLEncoder.encode(Ccredit,"UTF-8");//����
  		
		//�޸����ݺ�ҳ�淵�ص���ǰ(page/manager/updateCourse.jsp)ҳ��
  		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateCourse.jsp").forward(request, response);
		
		System.out.println("mUpdateCourseServletҳ��"+Cno+Cname +Ccredit);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

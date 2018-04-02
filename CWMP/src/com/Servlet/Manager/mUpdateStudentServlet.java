package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.StudentDao;

/**
 * Servlet implementation class mUpdateStudentServlet
 */
@WebServlet("/mUpdateStudentServlet")
public class mUpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mUpdateStudentServlet() {
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
		
		System.out.println("***�������ѧ������������ݵ�mUpdateStudentServletҳ��**");
  		
		//�������ѧ����Ϣ����(updateStudent.jsp)���ݹ���������
		String Sno = request.getParameter("Sno");
		String Sname = request.getParameter("Sname");
		String sex = request.getParameter("sex");
		String Spassword = request.getParameter("Spassword");
		String Sdept= request.getParameter("Sdept");
		String CLno = request.getParameter("CLno");
		
		//�����������
		Sno = new String(Sno .getBytes("iso8859-1"),"utf-8"); 
		Sname = new String(Sname .getBytes("iso8859-1"),"utf-8"); 
  		sex = new String(sex .getBytes("iso8859-1"),"utf-8"); 
  		Spassword = new String(Spassword .getBytes("iso8859-1"),"utf-8"); 
  		Sdept = new String(Sdept .getBytes("iso8859-1"),"utf-8"); 
  		CLno = new String(CLno .getBytes("iso8859-1"),"utf-8");
  		
  		System.out.println("***mUpdateStudentServletҳ��1***"+Sno+Sname+sex+Spassword+Sdept+CLno);
		
		//����sql����ѧ����Dao�Ķ���ͨ��StudentDao�Ķ�����ø��º���Updatedata()�����뵽���ݿ�
  		String sql = "UPDATE Student SET Sname='"+Sname+"',Ssex='"+sex+"',Spassword='"+Spassword+"',Sdept='"+Sdept+"' WHERE Sno='"+Sno+"'; "; 
  		StudentDao stu = new StudentDao();
  		//flag����ִ�е�����
  		flag = stu.UpdateData(sql);
  		
  		//�����Ӵ���ֵ֮ǰ�ñ���һ��(en����)
  		Sno = java.net.URLEncoder.encode(Sno,"UTF-8");//����
  		Sname = java.net.URLEncoder.encode(Sname,"UTF-8");//����
  		sex = java.net.URLEncoder.encode(sex,"UTF-8");//����
  		Spassword = java.net.URLEncoder.encode(Spassword,"UTF-8");//����
  		Sdept = java.net.URLEncoder.encode(Sdept,"UTF-8");//����
  		CLno = java.net.URLEncoder.encode(CLno,"UTF-8");//����
		
		//�޸����ݺ�ҳ�淵�ص���ǰ(page/manager/updateStudent.jsp)ҳ��
  		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateStudent.jsp").forward(request, response);
		
		System.out.println("mUpdateStudentServletҳ��2"+Sno+Sname +sex+Spassword +Sdept+CLno);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

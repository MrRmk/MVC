package com.Servlet.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.TeacherDao;
import com.JavaBean.Teacher;

/**
 * Servlet implementation class mTeacher
 */
//ע�⣬ע��servlet
@WebServlet("/mTeacherServlet")
public class mTeacherServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    //��manager.jspҳ�浽mTeacherServlet.java��ת��mTeacher.jspҳ��****************************
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����������
		request.setCharacterEncoding("UTF-8");
		/*response.setContentType("text/html;charset=UTF-8");*/
		//request.setCharacterEncoding("UTF-8");
		/*response.setContentType("text/html;charset = utf-8");//Ŀ����Ϊ�˿������������Ϊ���������������UTF-8���н��룻
		response.setCharacterEncoding("UTF-8");//Ϊ�˽�response�����е�������UTF-8��������������
		response.setHeader("content-type","text/html;charset=UTF-8");*/
		
		System.out.println("***���ǻ�ȡ��ʦ���mTeacherServlet***");
		
		//����תmTeacher.jspҳ��ʱ���Ͱ�Ҫ��ʾ�����ݣ���mTeacher.jsp׼����
		TeacherDao tDao = new TeacherDao();
		List<Teacher> list = tDao.QueryAll();
		
		if( list==null ){
			System.out.println("mTeacher�н�ʦ��û�л�ȡ��!");
			request.getRequestDispatcher("/page/manager/manager.jsp").forward(request,response);
		}else{
			
			//��װ�н�ʦ���list���浽request�У�����mTeacher.jspҳ��
			request.setAttribute("list", list);
			System.out.println("***mTeacher.jsp***");
			request.getRequestDispatcher("/page/manager/mTeacher.jsp").forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

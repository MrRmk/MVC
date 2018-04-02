package com.Servlet.Student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.CcheckDao;
import com.BeanDao.StudentDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Ccheck;
import com.JavaBean.Student;
import com.JavaBean.Work;

/**
 * Servlet implementation class sCheckServlet
 */
@WebServlet("/sCheckServlet")
public class sCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		
		System.out.println("***���ǻ�ȡ��ҵ���ı��sCheckservlet****");
		//��ȡѧ��
		HttpSession session = request.getSession(); 
		String account = session.getAttribute("account").toString();
		System.out.println("sDownloadServletҳ��:"+account);
		//ͨ��ѧ�Ų�����ҵ���ı��ж�Ӧ�ļ�¼���浽list��
		CcheckDao ch = new CcheckDao();
		List<Ccheck> list = ch.QueryBySno(account);
		
		
		/*//��ȡ��ѧ���İ༶��
		StudentDao stu = new StudentDao();
		Student s = stu.QueryBySno(account);
		String CLno = s.getCLno().toString();
		System.out.println("studentServletҳ��CLno:"+CLno);
		//���༶������ҵ���в�ѯ�����е���ҵ��¼���ŵ�����wList��
		WorkDao  wDao = new WorkDao();
		List<Work> wList = (List<Work>) wDao.QueryByCLno(CLno);*/
	
		if( list == null ){
			System.out.println("û�л�ȡ��������ҵ���е�����!***");
			request.getRequestDispatcher("/page/student/sCheck.jsp").forward(request, response);
		}else{
			//��װ��������ҵ���list���浽request�У�����sDownload.jspҳ��
			request.setAttribute("list", list);
			System.out.println("****sDownload.jsp******");
			request.getRequestDispatcher("/page/student/sCheck.jsp").forward(request, response);	
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

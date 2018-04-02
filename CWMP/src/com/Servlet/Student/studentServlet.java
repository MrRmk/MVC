package com.Servlet.Student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.NoticeDao;
import com.BeanDao.StudentDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Notice;
import com.JavaBean.Student;
import com.JavaBean.Work;

/**
 * Servlet implementation class studentServlet
 */
@WebServlet("/studentServlet")
public class studentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//解决中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		//获取学号
		HttpSession session = request.getSession(); 
		String account = session.getAttribute("account").toString();
		System.out.println("studentServlet页面:"+account);
		//获取该学生的班级号
		StudentDao stu = new StudentDao();
		Student s = stu.QueryBySno(account);
		String CLno = s.getCLno().toString();
		System.out.println("studentServlet页面CLno:"+CLno);		
		//按班级号在公告表中查询到所有的班级公告记录，放到数组NtList中
		NoticeDao Nt = new NoticeDao();
		List<Notice> NtList = Nt.QueryByCLno( CLno );
		//按班级号在作业表中查询到所有的作业记录，放到数组wList中
		WorkDao  wDao = new WorkDao();
		List<Work> wList = (List<Work>) wDao.QueryByCLno(CLno);
		
		//设置属性,传递到jsp页面
		request.setAttribute("NtList", NtList);
		request.setAttribute("wList", wList);
		request.getRequestDispatcher("/page/student/student.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

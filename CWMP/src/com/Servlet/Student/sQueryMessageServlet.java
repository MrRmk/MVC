package com.Servlet.Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.StudentDao;
import com.JavaBean.Student;

/**
 * Servlet implementation class sQueryMessageServlet
 */
@WebServlet("/sQueryMessageServlet")
public class sQueryMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sQueryMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request 解决中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		//获取学号
		HttpSession session = request.getSession(); 
		String account = session.getAttribute("account").toString();
		System.out.println("studentServlet页面:"+account);
		//获取该学生的班级号
		StudentDao stu = new StudentDao();
		Student s = stu.QueryBySno(account);
		//设置属性,传递到jsp页面
		request.setAttribute("student", s);
		request.getRequestDispatcher("/page/student/sQueryMessage.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

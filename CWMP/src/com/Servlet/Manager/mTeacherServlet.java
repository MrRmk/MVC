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
//注解，注册servlet
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
    
    //从manager.jsp页面到mTeacherServlet.java在转到mTeacher.jsp页面****************************
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//解决中文乱码
		request.setCharacterEncoding("UTF-8");
		/*response.setContentType("text/html;charset=UTF-8");*/
		//request.setCharacterEncoding("UTF-8");
		/*response.setContentType("text/html;charset = utf-8");//目的是为了控制浏览器的行为，即控制浏览器用UTF-8进行解码；
		response.setCharacterEncoding("UTF-8");//为了将response对象中的数据以UTF-8解码后发向浏览器；
		response.setHeader("content-type","text/html;charset=UTF-8");*/
		
		System.out.println("***这是获取教师表的mTeacherServlet***");
		
		//在跳转mTeacher.jsp页面时，就把要显示的数据，给mTeacher.jsp准备好
		TeacherDao tDao = new TeacherDao();
		List<Teacher> list = tDao.QueryAll();
		
		if( list==null ){
			System.out.println("mTeacher中教师表没有获取到!");
			request.getRequestDispatcher("/page/manager/manager.jsp").forward(request,response);
		}else{
			
			//将装有教师表的list保存到request中，传到mTeacher.jsp页面
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

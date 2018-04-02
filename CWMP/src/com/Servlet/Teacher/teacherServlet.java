package com.Servlet.Teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.WorkDao;
import com.JavaBean.Work;

/**
 * Servlet implementation class teacherServlet
 */
@WebServlet("/teacherServlet")
public class teacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public teacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//解决中文乱码
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String account = null;
		WorkDao  wDao = null;
		List<Work> wList = null;
		HttpSession session = request.getSession();
		//获取教师账号
		account = session.getAttribute("account").toString();
		System.out.println("teacherServlet:"+account);
		
		//*******************************************查询该教师已发布的作业
		wDao = new WorkDao();
		wList = (List<Work>) wDao.QueryByTno(account);
		
		//设置属性,传递到jsp页面
		request.setAttribute("wList", wList);
		if(wList.size()!=0)
			System.out.println("teacherServlet:-----"+wList.get(0).getWtitle().toString()+wList.get(0).getCLno()+wList.get(0).getCno()+wList.get(0).getWcontent());
		
		//删除作业
		
		if(request.getParameter("Wno")!=null){
			String Wno = request.getParameter("Wno");
			
			int flag = wDao.DeleteByWno(Wno);
			request.setAttribute("flag", flag);
			System.out.println("teacherServlet:---Wno--"+Wno+"\nflag:"+flag);
		}
		request.getRequestDispatcher("page/teacher/teacher.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

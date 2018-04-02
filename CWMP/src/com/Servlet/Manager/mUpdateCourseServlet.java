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
		//request 解决中文乱码
		//request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("text/html; charset=UTF-8");
		
		System.out.println("***这是向教课程表中添加数据的mUpdateCourseServlet页面**");
  		
		//接收添加课程信息界面(updateCourse.jsp)传递过来的数据
		String Cno = request.getParameter("Cno");
		String Cname = request.getParameter("Cname");
		String Ccredit = request.getParameter("Ccredit");
		
		//解决中文乱码
		Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname .getBytes("iso8859-1"),"utf-8"); 
		Ccredit = new String(Ccredit .getBytes("iso8859-1"),"utf-8");
  		
  		System.out.println("***mUpdateCourseServlet页面***"+Cno+Cname+Ccredit);
		
		//创建sql语句和课程表Dao的对象，通过CourseDao的对象调用更新函数Updatedata()，插入到数据库
  		String sql = "UPDATE Course SET Cname='"+Cname+"',Ccredit='"+Ccredit+"' WHERE Cno='"+Cno+"'; "; 
  		CourseDao stu = new CourseDao();
  		//flag返回执行的行数
  		flag = stu.UpdateData(sql);
		
  		//超链接传递值之前用编码一次(en编码)
  		Cno = java.net.URLEncoder.encode(Cno,"UTF-8");//解码
  		Cname = java.net.URLEncoder.encode(Cname,"UTF-8");//解码
  		Ccredit = java.net.URLEncoder.encode(Ccredit,"UTF-8");//解码
  		
		//修改数据后，页面返回到当前(page/manager/updateCourse.jsp)页面
  		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateCourse.jsp").forward(request, response);
		
		System.out.println("mUpdateCourseServlet页面"+Cno+Cname +Ccredit);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

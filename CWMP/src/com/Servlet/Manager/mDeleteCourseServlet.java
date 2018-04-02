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
		
		//解决中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		System.out.println("***这是删除课程表中数据的mDeleteCourseServlet页面***");
				
		/*String Tno = request.getParameter("Tno").toString();
		String CLno = request.getParameter("CLno").toString();*/
		String Cno = request.getParameter("Cno").toString();
		//解决中文乱码
  		//Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 		
  		System.out.println("mDeleteCourseServlet页面"+Cno);
		
		//创建sql语句和课程表Dao的对象，通过CourseDao的对象调用插入函数UpdateData()，插入到数据库
		String sql = "DELETE FROM Course WHERE Cno='"+Cno+"'; "; 
		CourseDao cDao = new CourseDao();
  		//创建sql语句和授课表TCCLDao的对象，通过TCCLDao的对象调用插入函数UpdateData()，插入到数据库
		/*String sql = "DELETE FROM TCCL WHERE Tno='"+Tno+"' and CLno='"+CLno+"' and Cno='"+Cno+"'; "; 
		TCCLDao tc = new TCCLDao();*/
		//flag返回执行的行数
		flag = cDao.UpdateData(sql);
		
		//修改数据后，页面返回到课程表(mCourse.jsp)页面
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

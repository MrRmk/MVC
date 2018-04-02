package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mAddTeacherServlet
 */
@WebServlet("/mAddTeacherServlet")
public class mAddTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mAddTeacherServlet() {
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
		
		/*response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");*/

		
		
		System.out.println("***这是向教师表中添加数据的mAddTeacherServlet***");
		
		//接收添加教师信息界面(addTeacher.jsp)传递过来的数据
		String Tno = request.getParameter("Tno");
		String Tname = request.getParameter("Tname");
		String sex = request.getParameter("sex");
		String Toffice = request.getParameter("Toffice");
		String Tpassword = request.getParameter("Tpassword");
		String Tdept= request.getParameter("Tdept");
		
		/*//解决中文乱码
  		Tno = new String(Tno .getBytes("iso8859-1"),"utf-8"); 
  		Tname = new String(Tname .getBytes("iso8859-1"),"utf-8"); 
  		sex = new String(sex .getBytes("iso8859-1"),"utf-8"); 
  		Toffice = new String(Toffice .getBytes("iso8859-1"),"utf-8"); 
  		Tpassword = new String(Tpassword .getBytes("iso8859-1"),"utf-8"); 
  		Tdept = new String(Tdept .getBytes("iso8859-1"),"utf-8"); */
  		
		System.out.println("mAddTeachrServlet页面"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
		
		//创建sql语句和教师表Dao的对象，通过TeacherDao的对象调用插入函数UpdateData()，插入到数据库
		String sql = "insert into Teacher(Tno,Tname,Tsex,Toffice,Tpassword,Tdept) values('"+Tno+"','"+Tname+"','"+sex+"','"+Toffice+"','"+Tpassword+"','"+Tdept+"')"; 
		TeacherDao tea = new TeacherDao();	
		//flag返回执行的行数
		flag = tea.UpdateData(sql);
		
		//插入数据后，页面返回到教师添加(page/manager/addTeacher.jsp)页面（当前页面）
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/addTeacher.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

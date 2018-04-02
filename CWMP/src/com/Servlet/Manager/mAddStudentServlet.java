package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.StudentDao;
import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mAddStudentServlet
 */
@WebServlet("/mAddStudentServlet")
public class mAddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mAddStudentServlet() {
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
		
		System.out.println("***这是向教学生表中添加数据的mAddStudentServlet页面**");
		
		//接收添加学生信息界面(addStudent.jsp)传递过来的数据
		String Sno = request.getParameter("Sno");
		String Sname = request.getParameter("Sname");
		String sex = request.getParameter("sex");
		String Spassword = request.getParameter("Spassword");
		String Sdept= request.getParameter("Sdept");
		String CLno = request.getParameter("CLno");
		
		//解决中文乱码
		/*Sno = new String(Sno .getBytes("iso8859-1"),"utf-8"); 
		Sname = new String(Sname .getBytes("iso8859-1"),"utf-8"); 
  		sex = new String(sex .getBytes("iso8859-1"),"utf-8"); 
  		Spassword = new String(Spassword .getBytes("iso8859-1"),"utf-8"); 
  		Sdept = new String(Sdept .getBytes("iso8859-1"),"utf-8"); 
  		CLno = new String(CLno .getBytes("iso8859-1"),"utf-8"); */
		
		System.out.println("***mAddStudentServlet页面"+Sno+Sname+sex+Spassword+Sdept+CLno);
		
		
		//创建sql语句和教师表Dao的对象，通过StudentDao的对象调用插入函数UpdateData()，插入到数据库
		String sql = "insert into Student(Sno,Sname,Ssex,Spassword,Sdept,CLno) values('"+Sno+"','"+Sname+"','"+sex+"','"+Spassword+"','"+Sdept+"','"+CLno+"')";
		StudentDao stu = new StudentDao();
		//flag返回执行的行数
		flag = stu.UpdateData(sql);
		
		//插入数据后，页面返回到学生添加(page/manager/addStudent.jsp)页面（当前页面）
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/addStudent.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

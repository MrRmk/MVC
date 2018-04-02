package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.StudentDao;

/**
 * Servlet implementation class mUpdateStudentServlet
 */
@WebServlet("/mUpdateStudentServlet")
public class mUpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mUpdateStudentServlet() {
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
		
		System.out.println("***这是向教学生表中添加数据的mUpdateStudentServlet页面**");
  		
		//接收添加学生信息界面(updateStudent.jsp)传递过来的数据
		String Sno = request.getParameter("Sno");
		String Sname = request.getParameter("Sname");
		String sex = request.getParameter("sex");
		String Spassword = request.getParameter("Spassword");
		String Sdept= request.getParameter("Sdept");
		String CLno = request.getParameter("CLno");
		
		//解决中文乱码
		Sno = new String(Sno .getBytes("iso8859-1"),"utf-8"); 
		Sname = new String(Sname .getBytes("iso8859-1"),"utf-8"); 
  		sex = new String(sex .getBytes("iso8859-1"),"utf-8"); 
  		Spassword = new String(Spassword .getBytes("iso8859-1"),"utf-8"); 
  		Sdept = new String(Sdept .getBytes("iso8859-1"),"utf-8"); 
  		CLno = new String(CLno .getBytes("iso8859-1"),"utf-8");
  		
  		System.out.println("***mUpdateStudentServlet页面1***"+Sno+Sname+sex+Spassword+Sdept+CLno);
		
		//创建sql语句和学生表Dao的对象，通过StudentDao的对象调用更新函数Updatedata()，插入到数据库
  		String sql = "UPDATE Student SET Sname='"+Sname+"',Ssex='"+sex+"',Spassword='"+Spassword+"',Sdept='"+Sdept+"' WHERE Sno='"+Sno+"'; "; 
  		StudentDao stu = new StudentDao();
  		//flag返回执行的行数
  		flag = stu.UpdateData(sql);
  		
  		//超链接传递值之前用编码一次(en编码)
  		Sno = java.net.URLEncoder.encode(Sno,"UTF-8");//解码
  		Sname = java.net.URLEncoder.encode(Sname,"UTF-8");//解码
  		sex = java.net.URLEncoder.encode(sex,"UTF-8");//解码
  		Spassword = java.net.URLEncoder.encode(Spassword,"UTF-8");//解码
  		Sdept = java.net.URLEncoder.encode(Sdept,"UTF-8");//解码
  		CLno = java.net.URLEncoder.encode(CLno,"UTF-8");//解码
		
		//修改数据后，页面返回到当前(page/manager/updateStudent.jsp)页面
  		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateStudent.jsp").forward(request, response);
		
		System.out.println("mUpdateStudentServlet页面2"+Sno+Sname +sex+Spassword +Sdept+CLno);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

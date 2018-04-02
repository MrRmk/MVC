package com.Servlet.Manager;

/*import java.net.*;
import java.util.*;*/
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mUpdateTeacherServlet
 */
@WebServlet("/mUpdateTeacherServlet")
public class mUpdateTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mUpdateTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request 解决中文乱码
		/*request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");*/
		
		//接收添加教师信息界面(updateTeacher.jsp)传递过来的数据
		String Tno = request.getParameter("Tno");
		String Tname = request.getParameter("Tname");
		String sex = request.getParameter("sex");
		String Toffice = request.getParameter("Toffice");
		String Tpassword = request.getParameter("Tpassword");
		String Tdept= request.getParameter("Tdept");
		System.out.println("mUpdateTeachrServlet页面1****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
		//解决中文乱码
  		Tno = new String(Tno.getBytes("ISO-8859-1"),"utf-8"); 
  		Tname = new String(Tname.getBytes("ISO-8859-1"),"utf-8"); 
  		sex = new String(sex.getBytes("ISO-8859-1"),"utf-8"); 
  		Toffice = new String(Toffice.getBytes("ISO-8859-1"),"utf-8"); 
  		Tpassword = new String(Tpassword.getBytes("ISO-8859-1"),"utf-8"); 
  		Tdept = new String(Tdept.getBytes("ISO-8859-1"),"utf-8"); 
  		
		System.out.println("mUpdateTeachrServlet页面2****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
		
		//创建sql语句和教师表Dao的对象，通过TeacherDao的对象调用插入函数Updatedata()，插入到数据库
		String sql = "UPDATE Teacher SET Tname='"+Tname+"',Tsex='"+sex+"',Toffice='"+Toffice+"',Tpassword='"+Tpassword+"',Tdept='"+Tdept+"' WHERE Tno='"+Tno+"'; "; 
		TeacherDao tea = new TeacherDao();
		//flag返回执行的行数
		flag = tea.UpdateData(sql);
		
		//修改数据后，页面返回到当前(page/manager/updateTeacher.jsp)页面
		/*request.setAttribute("flag", flag);
		request.setAttribute("Tno",Tno);
		request.setAttribute("Tname",Tname);
		request.setAttribute("sex",sex);
		request.setAttribute("Toffice",Toffice);
		request.setAttribute("Tpassword",Tpassword);
		request.setAttribute("Tdept",Tdept);*/
		
		//超链接传递值之前用编码一次(en编码)
  		Tno = java.net.URLEncoder.encode(Tno,"UTF-8");//解码
  		Tname = java.net.URLEncoder.encode(Tname,"UTF-8");//解码
  		sex = java.net.URLEncoder.encode(sex,"UTF-8");//解码
  		Toffice = java.net.URLEncoder.encode(Toffice,"UTF-8");//解码
  		Tpassword = java.net.URLEncoder.encode(Tpassword,"UTF-8");//解码
  		Tdept = java.net.URLEncoder.encode(Tdept,"UTF-8");//解码
  		
 		System.out.println("mUpdateTeachrServlet页面3****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
 		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateTeacher.jsp?Tno="+Tno+"&Tname="+Tname+"&sex="+sex+"&Toffice="+Toffice+"&Tpassword="+Tpassword+"&Tdept="+Tdept+" ").forward(request, response);
		
		/*System.out.println("mUpdateTeachrServlet页面2****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

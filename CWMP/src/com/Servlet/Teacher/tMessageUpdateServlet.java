package com.Servlet.Teacher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.TeacherDao;
import com.JavaBean.Teacher;

/**
 * Servlet implementation class tMessageUpdateServlet
 */
@WebServlet("/tMessageUpdateServlet")
public class tMessageUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tMessageUpdateServlet() {
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
		
		Teacher t = new Teacher();
		TeacherDao tDao = new TeacherDao();
		if(request.getParameter("Tno") != null){
			//获取表单数据
			String Tno = request.getParameter("Tno");
			String Tname = request.getParameter("Tname");
			String Tsex = request.getParameter("Tsex");
			String Toffice = request.getParameter("Toffice");
			String Tpassword = request.getParameter("Tpassword");
			String Tdept = request.getParameter("Tdept");
			System.out.println("从jsp接收的数据:"+Tdept+Tname+Tno+Toffice+Tpassword+Tsex);
			//将数据写入teacher对象中
			t.setTno(Tno);
			t.setTname(Tname);
			t.setTsex(Tsex);
			t.setToffice(Toffice);
			t.setTpassword(Tpassword);
			t.setTdept(Tdept);
			//调用TeacherDao进行修改
			int flag = tDao.Update_one(t);
			request.setAttribute("flag", flag);
			System.out.println("teacher对象中的数据:"+t.getTdept()+t.getTname()+t.getTno()+t.getToffice()+t.getTpassword()+t.getTsex());
		}else{
			HttpSession session = request.getSession();
			String Tno = session.getAttribute("account").toString();
			t = new Teacher();
			tDao = new TeacherDao();
			t = tDao.QueryByTno(Tno);
			
		}
		request.setAttribute("teacher", t);
		//String Tname= request.getParameter("Tname").toString();
		System.out.println( );
		
		
		request.getRequestDispatcher("page/teacher/tMessageUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mDeleteTeacherServlet
 */
@WebServlet("/mDeleteTeacherServlet")
public class mDeleteTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mDeleteTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//解决中文乱码
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("***这是删除教师表中数据的mDeleteTeacherServlet***");
		
		String Tno = request.getParameter("Tno").toString();
		//解决中文乱码
  		Tno = new String(Tno .getBytes("iso8859-1"),"utf-8"); 		
  		System.out.println("mDeleteTeacherServlet页面"+Tno);
		
		//创建sql语句和教师表Dao的对象，通过TeacherDao的对象调用插入函数UpdateData()，插入到数据库
		String sql = "DELETE FROM Teacher WHERE Tno='"+Tno+"'; "; 
		TeacherDao tea = new TeacherDao();
		//flag返回执行的行数
		flag = tea.UpdateData(sql);
		
		//修改数据后，页面返回到教师表(mTeacher.jsp)页面
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("mTeacherServlet").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

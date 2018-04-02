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
 * Servlet implementation class mDeleteStudentServlet
 */
@WebServlet("/mDeleteStudentServlet")
public class mDeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mDeleteStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//解决中文乱码
		request.setCharacterEncoding("UTF-8");			
		System.out.println("***这是删除学生表中数据的mDeleteStudentServlet页面***");
		
		
		String Sno = request.getParameter("Sno").toString();
		//解决中文乱码
  		Sno = new String(Sno .getBytes("iso8859-1"),"utf-8"); 		
  		System.out.println("mDeleteStudentServlet页面"+Sno);
		
		//创建sql语句和学生表Dao的对象，通过StudentDao的对象调用插入函数UpdateData()，插入到数据库
		String sql = "DELETE FROM Student WHERE Sno='"+Sno+"'; "; 
		StudentDao stu = new StudentDao();
		//flag返回执行的行数
		flag = stu.UpdateData(sql);
		
		//修改数据后，页面返回到学生表(mStudent.jsp)页面
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("mStudentServlet").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.Servlet.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.CourseDao;
import com.BeanDao.TCCLDao;
import com.BeanDao.TeacherDao;
import com.JavaBean.Course;
import com.JavaBean.TCCL;
import com.JavaBean.Teacher;

/**
 * Servlet implementation class mTCCLServlet
 */
@WebServlet("/mTCCLServlet")
public class mTCCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mTCCLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//解决中文乱码
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("***这是获取授课表的mTCCLServlet***");
		
		//在跳转mTCCL.jsp页面时，就把要显示的数据，给mTCCL.jsp准备好
		TCCLDao tc = new TCCLDao();
		List<TCCL> listTCCL = tc.QueryAll();
		/*//在跳转mTeacher.jsp页面时，就把要显示的数据，给mTeacher.jsp准备好
		TeacherDao tDao = new TeacherDao();
		List<Teacher> listTeacher = tDao.QueryAll();
		//创建课程表CourseDao的对象，连接数据库，获取Course课程表，放到list中
		CourseDao ct = new CourseDao();
		List<Course> listCourse = ct.QueryAll();		*/
		
		if( (listTCCL == null) ){			
			System.out.println("mTCCL中授课表没有获取到!");
			request.getRequestDispatcher("/page/manager/mTCCL.jsp").forward(request,response);
		}else{
			
			//将装有授课表的list保存到request中，传到mTCCL.jsp页面
			//request.setAttribute("listCourse", listCourse);
			request.setAttribute("listTCCL", listTCCL);
			//request.setAttribute("listTeacher", listTeacher);
			System.out.println("***mTCCLServlet跳转到mTCCL.jsp***");
			request.getRequestDispatcher("/page/manager/mTCCL.jsp").forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

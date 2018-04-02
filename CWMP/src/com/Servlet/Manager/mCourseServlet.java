package com.Servlet.Manager;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class mCourseServlet
 */
@WebServlet("/mCourseServlet")
public class mCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("*****这是获取课程表的servlet******");
		
		//创建课程表CourseDao的对象，连接数据库，获取Course课程表，放到list中
		CourseDao ct = new CourseDao();
		List<Course> listCourse = ct.QueryAll();	
		/*//在跳转mTeacher.jsp页面时，就把要显示的数据，给mTeacher.jsp准备好
		TeacherDao tDao = new TeacherDao();
		List<Teacher> listTeacher = tDao.QueryAll();
		//创建授课表TCCLDao的对象，连接数据库，获取TCCL授课表，放到list中
		TCCLDao tc = new TCCLDao();
		List<TCCL> listTCCL = tc.QueryAll();
		
		//把授课表中教师号对应的教师表记录放到数组a中
		Teacher[] t = new Teacher[listTCCL.size()];//(Teacher[]) new List[listTCCL.size()];
		for( int i=0; i<listTCCL.size(); i++ ){
			t[i] = tDao.QueryByTno(listTCCL.get(i).getTno().toString());
		}
		//把授课表中课程号Cno对应的课程表记录放到数组c中
		Course[] c = new Course[listTCCL.size()];
		int j = 0;
		for( int i=0; i<listTCCL.size(); i++ ){
			if( (ct.QueryByCno(listTCCL.get(i).getCno().toString())) !=null ){
				c[j] = ct.QueryByCno(listTCCL.get(i).getCno().toString());
				j = j + 1;
			}			
		}*/
		
		
		
		/*if( (listCourse == null)||(listTCCL == null)||(listTeacher == null) ){		*/
		if( listCourse == null ){		
			System.out.println("***没有获取到Course课程表中的数据*******");
			request.getRequestDispatcher("/page/manager/mCourse.jsp").forward(request,response);
		}else{
			
			//将装有课程表的list存到request中，传到mCourse.jsp页面
			//request.setAttribute("c", c);
			request.setAttribute("listCourse", listCourse);
			//request.setAttribute("t", t);
			System.out.println("****mCourse.jsp****");
			request.getRequestDispatcher("/page/manager/mCourse.jsp").forward(request, response);
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

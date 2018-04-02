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
import com.JavaBean.Teacher;

/**
 * Servlet implementation class mAddTCCLServlet
 */
@WebServlet("/mAddTCCLServlet")
public class mAddTCCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
	String Tno = null;
	String Cno =  null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mAddTCCLServlet() {
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
		
		System.out.println("***这是向教课程表中添加数据的mAddCourseServlet页面**");
		
		//接收添加课程信息界面(addCourse.jsp)传递过来的数据
		String Tname = request.getParameter("select1");
		String CLno = request.getParameter("select2");
		String Cname = request.getParameter("select3");
		
		//通过教师名找到教师号
		TeacherDao tDao = new TeacherDao();
		List<Teacher> listTeacher = tDao.QueryAll();
		for(int i=0; i<listTeacher.size(); i++){
			if( Tname.equals(listTeacher.get(i).getTname()) ){
				Tno = listTeacher.get(i).getTno();
			}	
		}
		//通过课程名找到课程号
		CourseDao c = new CourseDao();
		List<Course> listCourse = c.QueryAll();
		for(int j=0; j<listCourse.size(); j++){
			if( Cname.equals(listCourse.get(j).getCname()) ){
				Cno = listCourse.get(j).getCno();
			}
		}
		
		//解决中文乱码
		/*Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname .getBytes("iso8859-1"),"utf-8"); 
		Ccredit = new String(Ccredit .getBytes("iso8859-1"),"utf-8"); */
		
		System.out.println("***mAddTCCLServlet页面"+Tno+CLno+Cno);
		
		
		/*//创建sql语句和课程表Dao的对象，通过CourseDao的对象调用插入函数UpdateData()，插入到数据库
		String sql = "insert into Course(Cno,Cname,Ccredit) values('"+Tno+"','"+CLno+"','"+Cno+"')";
		CourseDao stu = new CourseDao();
		//flag返回执行的行数
		flag = stu.UpdateData(sql);
		//在跳转mTeacher.jsp页面时，就把要显示的数据，给mTeacher.jsp准备好
		TeacherDao tDao = new TeacherDao();
		List<Teacher> list = tDao.QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tname.equals(list.get(i).getTname()) ){
				Tno = list.get(i).getTno(); 
			}
		}*/
		
		//创建sql语句和授课表Dao的对象，通过TCCLDao的对象调用插入函数UpdateData()，插入到数据库
		String sql2 = "insert into TCCL(Tno,CLno,Cno) values('"+Tno+"','"+CLno+"','"+Cno+"')";
		TCCLDao tc = new TCCLDao();
		//flag返回执行的行数
		flag = tc.UpdateData(sql2);
		
		//插入数据后，页面返回到课程添加(page/manager/addCourse.jsp)页面（当前页面）
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/addTCCL.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

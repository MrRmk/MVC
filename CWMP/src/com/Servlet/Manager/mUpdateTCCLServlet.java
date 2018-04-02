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
 * Servlet implementation class mUpdateTCCLServlet
 */
@WebServlet("/mUpdateTCCLServlet")
public class mUpdateTCCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
	String Tno = "";
	String Cno = "";
	String YCno = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mUpdateTCCLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request 解决中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		
		System.out.println("***这是向授课表中添加数据的mUpdateTCCLServlet页面**");
  		
		//接收添加授课信息界面(updateTCCL.jsp)传递过来的数据
		String Tname = request.getParameter("Tname");//教师名
		String CLno = request.getParameter("CLno");//修改后的班级号
		String Cname = request.getParameter("Cname");//修改后的课程名
		String YCLno = request.getParameter("YCLno");//原来的班级号
		
		String YCname = request.getParameter("YCname");//原来的课程名
		System.out.println("***mUpdateTCCLServlet页面***"+Tname+CLno+Cname+YCname+YCLno);
		
		/*//解决中文乱码
		Tname = new String(Tname.getBytes("iso8859-1"),"utf-8"); 
		CLno = new String(CLno.getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname.getBytes("iso8859-1"),"utf-8"); 
		System.out.println("***mUpdateTCCLServlet页面***"+Tname+CLno+Cname);*/
		
		
		
		//通过教师名查找教师号
		TeacherDao tDao = new TeacherDao();
		List<Teacher> list = tDao.QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tname.equals(list.get(i).getTname()) ){
				Tno = list.get(i).getTno();
				System.out.println("***mUpdateTCCLServlet页面***Tno:"+Tno);
			}
		}
		//通过课程名查找课程号
		CourseDao c = new CourseDao();
		List<Course> listCourse = c.QueryAll();
		for(int i=0; i<listCourse.size(); i++){
			if( Cname.equals(listCourse.get(i).getCname()) ){
				Cno = listCourse.get(i).getCno();
				System.out.println("***mUpdateTCCLServlet页面***Cno:"+Cno);
			}
			if( YCname.equals(listCourse.get(i).getCname()) ){
				YCno = listCourse.get(i).getCno();
				System.out.println("***mUpdateTCCLServlet页面***YCno:"+YCno);
			}
		}
		
		//解决中文乱码
		/*Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname .getBytes("iso8859-1"),"utf-8"); 
		Ccredit = new String(Ccredit .getBytes("iso8859-1"),"utf-8");*/
  		
  		System.out.println("***mUpdateTCCLServlet页面***"+Tno+CLno+Cno);
		
		//创建sql语句和授课表Dao的对象，通过TCCLDao的对象调用更新函数Updatedata()，插入到数据库
  		String sql = "UPDATE TCCL SET Cno='"+Cno+"',CLno='"+CLno+"' WHERE Tno='"+Tno+"' and CLno='"+YCLno+"' and Cno='"+YCno+"'; "; 
  		TCCLDao stu = new TCCLDao();
  		//flag返回执行的行数
  		flag = stu.UpdateData(sql);
  		System.out.println("***mUpdateTCCLServlet页面***flag:"+flag);
		//修改数据后，页面返回到当前(page/manager/updateTCCL.jsp)页面
  		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateTCCL.jsp").forward(request, response);
		
		System.out.println("mUpdateTCCLServlet页面"+Cno+CLno+Tno);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

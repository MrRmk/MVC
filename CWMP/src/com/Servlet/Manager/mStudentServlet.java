package com.Servlet.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.CLassDao;
import com.BeanDao.StudentDao;
import com.JavaBean.CLass;
import com.JavaBean.Student;

/**
 * Servlet implementation class mStudentServlet
 */
@WebServlet("/mStudentServlet")
public class mStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Student> list = null;//存放学生表中的所有数据
	List<Student> listCLno = null;//存放这个班的所有学生的数据
	String CLno = null;//班级号
	
	StudentDao stu = null;//学生Dao对象
	CLassDao cl = null;//班级Dao对象
	CLass c = null;//班级表的对象，用来存放通过班级号查找到的一条班级表记录
	int CLnumber = 0;//班级人数
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");		
		System.out.println("***这是获取学生表的servlet****");
		
		//************首次跳转获取所有学生数据*********************************************************************************
		//创建StudentDao对象，通过StudentDao对象连接数据，获取学生表中的所有数据
		stu = new StudentDao();
		list = stu.QueryAll();//存放学生表中的所有数据
		
		//************点击查询之后，把选中的班级号传过来，接收，判断是否为空*********************************************************************************
		if( request.getParameter("CLno") != null ){
			CLno = request.getParameter("CLno");		
			//通过班级号从学生表中查找到，这个班的所有学生的数据
			listCLno = stu.QueryByCLno(CLno);
			
			//创建班级CLassDao的对象，通过CLassDao的对象连接数据获取班级表中与该班级号CLno相同的一条记录
			cl = new CLassDao();
			c = cl.QueryByCLno(CLno);
			CLnumber = c.getCLnumber();//班级人数
			request.setAttribute("CLnumber", CLnumber);
			request.setAttribute("ClassCLno", CLno);
			request.setAttribute("listCLno", listCLno);
		}
		
		if( list == null ){
			System.out.println("没有获取到学生表中的数据!***");
			request.getRequestDispatcher("/page/manager/mStudent.jsp").forward(request, response);
		}else{
			
			//将装有学生表的list保存到request中，传到mStudent.jsp页面
			System.out.println("****mStudent.jsp******");
			request.setAttribute("list", list);	
			request.getRequestDispatcher("/page/manager/mStudent.jsp").forward(request, response);
		
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

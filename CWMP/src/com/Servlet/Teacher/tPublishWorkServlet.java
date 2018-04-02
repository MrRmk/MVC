package com.Servlet.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.CourseDao;
import com.BeanDao.TCCLDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Course;
import com.JavaBean.TCCL;
import com.JavaBean.Work;

/**
 * Servlet implementation class tPublishWorkServlet
 */
@WebServlet("/tPublishWorkServlet")
public class tPublishWorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tPublishWorkServlet() {
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
		
		//得到表单数据
		String Wno = null;
		String Wtitle = null;
		String Wcontent  = null;
		String Wdeadline = null;
		String Tno = null;
		String CLno = null;
		String Cno = null;
		String Cname = null;//通过课程名获得课程号
		Work w = new Work();
		HttpSession session = request.getSession();
		//从jsp跳转过来,属性非空插入数据库
		if(request.getParameter("Tno") != null){
			Wno = request.getParameter("Wno");
			Wtitle = request.getParameter("Wtitle");
			Wcontent = request.getParameter("Wcontent");
			Wdeadline = request.getParameter("Wdeadline");
			Tno = request.getParameter("Tno");
			CLno = request.getParameter("CLno");
			//通过课程名查询课程号
			Cname = request.getParameter("Cname");
			CourseDao cDao = new CourseDao();
			Cno = (cDao.QueryByCname(Cname)).getCno();
			
			//创建对象
			w.setTno(Tno);
			w.setWcontent(Wcontent);
			w.setWdeadline(Wdeadline);
			w.setWno(Wno);
			w.setWtitle(Wtitle);
			w.setCLno(CLno);
			w.setCno(Cno);
			
			//调用WorkDao插入数据
			WorkDao wDao = new WorkDao();
			int flag = 0;
			flag = wDao.Insert_one(w);
			
			System.out.println("#####################tPublishWorkServlet:"+Wno+Wcontent+Tno+Wdeadline+w.getCLno()+w.getCno());
			request.setAttribute("flag", flag);//判断数据是否插入成功
		}//每次进入取数据
			//获得教师号Tno
			
			Tno = session.getAttribute("account").toString();
			
			//创建TCCLDao对象,即授课表
			TCCLDao tcclDao = new TCCLDao();
			List<TCCL> listTCCL = new ArrayList<>();
			listTCCL = tcclDao.QueryByTno(Tno);
			request.setAttribute("listTCCL", listTCCL);
			
			//根据班级号查询课程号
			String[] arrayCno = new String[listTCCL.size()];
			for(int  i=0; i<arrayCno.length; i++){
				arrayCno[i] =listTCCL.get(i).getCno();
			}
			
			//根据课程号查询课程名
			List<Course> listCourse = new ArrayList<>();
			CourseDao cDao  = new CourseDao();
			for(int i=0; i<listTCCL.size(); i++){
			
				listCourse.add(cDao.QueryByCno(arrayCno[i]));
			}
			//去除重复课程
			for(int i=0; i<listCourse.size()-1; i++){
				if(listCourse.get(i).getCname().equals(listCourse.get(i+1).getCname()))
					listCourse.remove(i);
			}
			if(listCourse.get(listCourse.size()-1).getCname().equals(listCourse.get(listCourse.size()-2).getCname()))
				listCourse.remove(listCourse.size()-2);
			
			//将课程对象传到jsp页面
			request.setAttribute("listCourse", listCourse);
			
		
		
		request.getRequestDispatcher("page/teacher/tPublishWork.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

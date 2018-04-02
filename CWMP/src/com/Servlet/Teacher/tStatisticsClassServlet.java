package com.Servlet.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.CcheckDao;
import com.BeanDao.CourseDao;
import com.BeanDao.StudentDao;
import com.BeanDao.TCCLDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Ccheck;
import com.JavaBean.Course;
import com.JavaBean.Student;
import com.JavaBean.TCCL;
import com.JavaBean.Work;

/**
 * Servlet implementation class tStatisticsClassServlet
 */
@WebServlet("/tStatisticsClassServlet")
public class tStatisticsClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tStatisticsClassServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//解决中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		//获得教师号Tno
		HttpSession session = request.getSession();
		String Tno = session.getAttribute("account").toString();
		//******************************************每次进入查询数据************************************
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
		Course[] arrayCourse =new Course[listTCCL.size()];
		CourseDao cDao  = new CourseDao();
		for(int i=0; i<listTCCL.size(); i++){
		
			arrayCourse[i] = cDao.QueryByCno(arrayCno[i]);
		}
		for(int i=0; i<arrayCourse.length;i++){
	  		System.out.println(arrayCourse[i].getCname());
	  	}
		//将课程对象传到jsp页面
			request.setAttribute("arrayCourse", arrayCourse);
			//将课程数组中的对象传到jsp页面
			for(int j=0; j<arrayCourse.length; j++){
				request.setAttribute("Course"+j, arrayCourse[j]);
			}
		//**************************************从jsp提交数据过来进行处理再返回**********************************
		if(request.getParameter("CLno") != null){
			String CLno ;
			String Cname ;
			if(!(request.getParameter("Cname").equals("==  课  程  =="))){
				CLno = request.getParameter("CLno");
				Cname = request.getParameter("Cname");
			}else{
				CLno = request.getParameter("CLno_");
				Cname = request.getParameter("Cname_");
			}
			StudentDao sDao = new StudentDao();
			WorkDao wDao = new WorkDao();
			CcheckDao CchDao = new CcheckDao();
			//将CLno和Cname传回jsp
			request.setAttribute("CLno", CLno);
			request.setAttribute("Cname", Cname);
			
			//分页变量设置
			int pageSize = 5;
			int pageNow;
			int pageCount;
			int rowCount;
			String sqlCount  = "select count(*) from student";
			
			
			
			//将班级号和课程名传回jsp页面
			request.setAttribute("CLno", CLno);
			request.setAttribute("Cname", Cname);
			//根据班级查询要显示学生信息
			List<Student> listStudent = new ArrayList<>();
			listStudent = sDao.QueryByCLno(CLno);
			request.setAttribute("listStudent", listStudent);
			//根据班级查询要的作业信息
			List<Work> listWork= new ArrayList<>();
			listWork = wDao.QueryByCLno(CLno);
			
			//根据课程名查询对应的课程号
			String Cno = (cDao.QueryByCname(Cname)).getCno();
			//根据课程号过滤,得到要显示的作业信息
			List<Work> listResultWork= new ArrayList<>();
			for(int i=0; i<listWork.size(); i++){
				if(listWork.get(i).getCno().equals(Cno)){
					listResultWork.add(listWork.get(i));
				}
			}for(int i=0; i<listResultWork.size(); i++){
	  			
	  			System.out.println("jsp-listResultWork:##########***************************************"+listResultWork.get(i).getWno()+"   "
	  			+listResultWork.get(i).getWtitle()+"  "+listResultWork.get(i).getWcontent());
	  			
	  		}
			request.setAttribute("listResultWork", listResultWork);
			//根据作业号和学号查询每个学生在批改表里的记录
				//先通过学号查到每个学生对应的批改表集合 的集合
			List<List<Ccheck>> listListCcheck = new ArrayList<>();
			for(int i=0; i<listStudent.size(); i++){
				listListCcheck.add(CchDao.QueryBySno(listStudent.get(i).getSno()));
			}
			for(int i=0; i<listListCcheck.size(); i++){
	  			for(int j=0; j<listListCcheck.get(i).size(); j++){
	  			System.out.println("Servlet+++++++++++++++++++++++++++++++-listListCcheck:##########"+listListCcheck.get(i).get(j).getSno()+"  "
	  			+listListCcheck.get(i).get(j).getWno()+"  "+listListCcheck.get(i).get(j).getChscore());
	  			}
	  		}
				//再通过作业号过滤得到要使用的修改表对象集合 的集合
			List<List<Ccheck>> listListResultCcheck = new ArrayList<>();
			for(int i=0; i<listListCcheck.size(); i++){
				for(int j=0; j<listListCcheck.get(i).size(); j++){
					for(int k=0; k<listResultWork.size(); k++){
						System.out.println("Servlet$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$listListCcheck:##########"+listListCcheck.get(i).get(j).getSno()+"  "
					  			+listListCcheck.get(i).get(j).getWno()+"  "+listListCcheck.get(i).get(j).getChscore());
						System.out.println("Servlet$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$listResultWork:##########"+listResultWork.get(k).getWno()+"   "
					  			+listResultWork.get(k).getWtitle()+"  "+listResultWork.get(k).getWcontent());
						if(listListCcheck.get(i).get(j).getWno().equals(listResultWork.get(k).getWno())){
							listListResultCcheck.add(listListCcheck.get(i));
						}
					}
					
				}
			}
			request.setAttribute("listListResultCcheck", listListResultCcheck);
	
		}
		
		request.getRequestDispatcher("page/teacher/tStatisticsClass.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

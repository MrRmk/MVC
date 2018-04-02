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

import org.w3c.dom.ls.LSInput;

import com.BeanDao.CcheckDao;
import com.BeanDao.StudentDao;
import com.BeanDao.TCCLDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Ccheck;
import com.JavaBean.Student;
import com.JavaBean.TCCL;
import com.JavaBean.Work;

/**
 * Servlet implementation class tStatisticsStudentServlet
 */
@WebServlet("/tStatisticsStudentServlet")
public class tStatisticsStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tStatisticsStudentServlet() {
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
		
		//**********************************每次进入查询数据********************************
		
				//创建TCCLDao对象,即授课表
				TCCLDao tcclDao = new TCCLDao();
				List<TCCL> listTCCL = new ArrayList<>();
				listTCCL = tcclDao.QueryByTno(Tno);
				request.setAttribute("listTCCL", listTCCL);
				
				//根据班级查询学生
				StudentDao sDao = new StudentDao();
				List<Student>[]  arrayListStudent = new List[listTCCL.size()];//将查询结果集合放入集合数组中
				List<Student> ListStudent = new ArrayList<>();
				//将查询到的学生集合存入学生集合数组中
				for(int i = 0; i<listTCCL.size(); i++){
					arrayListStudent[i] =sDao.QueryByCLno(listTCCL.get(i).getCLno().toString());
				}
				
				//将集合数组中的集合传到jsp页面
				for(int j=0; j<listTCCL.size(); j++){
					request.setAttribute("listStudent"+j, arrayListStudent[j]);
				}
		
		//**********************************从jsp跳转过来获取数据********************************
		if(request.getParameter("CLno") != null){
			String CLno = request.getParameter("CLno");//班级号用于查作业
			String Sname = request.getParameter(CLno);//姓名用于通过学号到批改表里查找否提交
			//先查询教师对应的作业集合,再用班级号过滤
			WorkDao  wDao = null;
			List<Work> wList = null;
			List<Work> listResultWork = new ArrayList<>();//用于存储最终显示的作业对象集合
			wDao = new WorkDao();
			wList = (List<Work>) wDao.QueryByTno(Tno);
			//通过班级号过滤得到要显示的作业
			for(int i=0; i<wList.size(); i++){
				if(CLno.equals(wList.get(i).getCLno())){
					listResultWork.add(wList.get(i));   
					for(int j=0; j<listResultWork.size();j++)
						System.out.println("servlet---要显示的作业:"+listResultWork.get(j).getWcontent());
				}
			}
			request.setAttribute("listResultWork", listResultWork);
			//通过姓名得到相应的学号
			String Sno = null;
			Sno = sDao.QueryBySname(Sname).getSno();
			System.out.println("servlet((((((((-Sno"+Sno);
			request.setAttribute("Sno",Sno);
			request.setAttribute("Sname", Sname);
			/*//通过要显示的作业号到批改表中将该作业对应的记录查询出来放入集合数组中
			List<Ccheck>[] arrayListCcheck = new ArrayList[listResultWork.size()]; 
			CcheckDao ccheckDao = new CcheckDao();
			for(int i=0; i<arrayListCcheck.length; i++){
				arrayListCcheck[i] = ccheckDao.QueryByWno(listResultWork.get(i).getWno());
				for(int j=0; j<arrayListCcheck[i].size(); j++)
					System.out.println("servlet---要显示的批阅表信息:"+arrayListCcheck[i].get(j).getSno()+" "+arrayListCcheck[i].get(j).getWno());
			}*/
			//通过要显示的学号到批改表中将该作业对应的记录查询出来放入集合数组中
			List<Ccheck> listCcheck = null; 
			CcheckDao ccheckDao = new CcheckDao();
			listCcheck = ccheckDao.QueryBySno(Sno);
			for(int j=0; j<listCcheck.size(); j++)
				System.out.println("servlet---要显示的批阅表信息:"+listCcheck.get(j).getSno()+" "+listCcheck.get(j).getWno());
			request.setAttribute("listCcheck", listCcheck);
			
		}
		System.out.println("查询数据结束,开始跳转....../////");
		
		request.getRequestDispatcher("page/teacher/tStatisticsStudent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

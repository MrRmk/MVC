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

import com.BeanDao.NoticeDao;
import com.BeanDao.TCCLDao;
import com.JavaBean.Notice;
import com.JavaBean.TCCL;

/**
 * Servlet implementation class tPublishNotice
 */
@WebServlet("/tPublishNoticeServlet")
public class tPublishNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tPublishNoticeServlet() {
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
		
		//从jsp提交过来,属性非空,插入数据库
		if(request.getParameter("NtContent") != null){
			String NtTitle = null;
			String NtContent = null;
			String NtTime = null;
			String NtCLno = null;
			String NtTno = null;
			 NtTitle = request.getParameter("NtTitle");
			 NtContent = request.getParameter("NtContent");
			 NtTime = request.getParameter("NtTime");
			 NtCLno = request.getParameter("NtCLno");
			 NtTno = Tno;
			 Notice n = new Notice();
			 n.setNtTitle(NtTitle);
			 n.setNtContent(NtContent);
			 n.setNtTime(NtTime);
			 n.setNtCLno(NtCLno);
			 n.setNtTno(NtTno);			System.out.println("TPNServlet:"+n.getNtCLno()+n.getNtTime()+
					 n.getNtTno()+n.getNtNo()+n.getNtTitle()+n.getNtContent());
			 NoticeDao nDao = new NoticeDao();
			 int flag = nDao.Insert_one(n);
			request.setAttribute("flag", flag);
			System.out.println("TPNServlet:after"+n.getNtNo()+n.getNtTitle()+n.getNtContent());
		}
		
		//每次进入取数据
		
		//创建TCCLDao对象,即授课表
		TCCLDao tcclDao = new TCCLDao();
		List<TCCL> listTCCL = new ArrayList<>();
		listTCCL = tcclDao.QueryByTno(Tno);
		request.setAttribute("listTCCL", listTCCL);
		request.getRequestDispatcher("/page/teacher/tPublishNotice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

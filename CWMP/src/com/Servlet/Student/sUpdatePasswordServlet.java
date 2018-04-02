package com.Servlet.Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.StudentDao;
import com.JavaBean.Student;

/**
 * Servlet implementation class sUpdateServlet
 */
@WebServlet("/sUpdatePasswordServlet")
public class sUpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sUpdatePasswordServlet() {
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
		
		//接收学生信息界面(sUpdate.jsp)传递过来的数据
		HttpSession session = request.getSession();
		String account = session.getAttribute("account").toString();
		String password = request.getParameter("password");//输入的原密码
		String password1 = request.getParameter("password1");//输入的新密码
		String password2 = request.getParameter("password2");//输入的确认密码
		StudentDao stu = new StudentDao();
		Student s = stu.QueryBySno(account);
		String ps = s.getSpassword();//数据库中的原密码
		
		System.out.println("sUpdateServlet页面1****"+account+password+password1+password2);
		
		if( ps.equals(password) ){//如果 '输入的原密码' 与 '数据库中的原密码' 相同
			if( !password.equals(password1) ){//如果 '输入的原密码' 与 '输入的新密码' 不相同
				if( password1.equals(password2) ){//如果 '输入的新密码' 与 '输入的确认密码' 相同
					//创建sql语句和学生表Dao的对象，通过StudentDao的对象调用插入函数Updatedata()，插入到数据库
					String sql = "UPDATE Student SET Spassword='"+password1+"' WHERE Sno='"+account+"'; "; 					
					//flag返回执行的行数
					flag = stu.UpdateData(sql);	//此时flag=1；			
					//修改数据后，页面返回到当前(page/student/sUpdate.jsp)页面
					request.setAttribute("flag", flag);
					request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);	
				}else{
					flag = 2;
					//修改数据后，页面返回到当前(page/student/sUpdate.jsp)页面
					request.setAttribute("flag", flag);
					request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);
				}
			
			}else{
				flag = 3;
				//修改数据后，页面返回到当前(page/student/sUpdate.jsp)页面
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);
			}
		}else{
			flag = 4;
			//修改数据后，页面返回到当前(page/student/sUpdate.jsp)页面
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);
		}
		
		System.out.println("mUpdateTeachrServlet页面2****"+password+password1+password2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

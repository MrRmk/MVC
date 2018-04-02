package com.Servlet.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.BeanDao.ManagerDao;
import com.BeanDao.StudentDao;
import com.BeanDao.TeacherDao;
import com.JavaBean.Manager;
import com.JavaBean.Student;
import com.JavaBean.Teacher;

/**
 * Servlet implementation class welcome
 */
//注解，注册servlet
@WebServlet("/welcome")
public class welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public welcome() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//=====================================使用servlet每次更改都要重启tomcat===========================================   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//解决中文乱码
		request.setCharacterEncoding("UTF-8");
		/*response.setContentType("text/html;charset = utf-8");*/
		
		/*//登录的三种类型
		String type1 = "Manager";//管理员
		String type2 = "Teacher";//教师
		String type3 = "Student";//学生
		*/
		//获取登录页面输入的--账号、密码和类型--------getParamenter()得到的是Object类型的值----------
		String account = request.getParameter("account").toString();
		String password = request.getParameter("password").toString();
		String type = request.getParameter("type").toString();
		
  		
		//打印账号、密码和类型
		System.out.println("acccount="+account);
		System.out.println("password="+password);
		System.out.println("type="+type);
		
		//验证身份
		
		switch( type ){
			case "Manager"://管理员
				
				System.out.println("管理员类型验证通过");
				//定义ManagerDao的对象
				ManagerDao mdao = new ManagerDao();
				//连接数据库，获取管理员表中的数据
				//找到与该管理员账号Manager匹配的这条(数据库)记录
				Manager man = mdao.QueryByMno(account);
				if( man==null ){
					System.out.println("没有获取到与管理员账号对应的这条记录");
					request.setAttribute("flag", "1");
					request.getRequestDispatcher("login.jsp").forward(request,response);
					
				}else{//如果man不为空，说明管理员表中有这个账号，即(账号没错)
					System.out.println("管理员账号验证通过");
					if(password.equals(man.getMpassword()) ){//验证密码是否正确
						System.out.println("管理员密码验证通过");
						//验证通过
						System.out.println("管理员验证通过，登录成功!");
						/*//设置request属性
						request.setAttribute("account", account);
						request.setAttribute("name", man.getMname().toString());
						request.setAttribute("password", password);
						request.setAttribute("type", "管理员");*/
						//设置session属性
						HttpSession session = request.getSession();
						session.setAttribute("account", account);//账号
				  		session.setAttribute("name", man.getMname().toString());//姓名
				  		session.setAttribute("type", "管理员");//类型
						
				  		System.out.println("welcome页面:"+account+man.getMname().toString()+type);
						//重定向
						request.getRequestDispatcher("/page/manager/manager.jsp").forward(request,response);  
					}else{
						System.out.println("管理员密码错误");
						request.setAttribute("flag", "1");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
				}
				break;
				
			case "Teacher" ://教师
				
				System.out.println("教师类型验证通过");
				//定义TeacherDao的对象
				TeacherDao tdao = new TeacherDao();
				//连接数据库，获取教师表中的数据
				//找到与该教师号匹配的这条(数据库)记录
				Teacher tea = tdao.QueryByTno(account);
				if( tea==null ){
					System.out.println("没有获取到与教师账号对应的这条记录");
					request.setAttribute("flag", "1");
					request.getRequestDispatcher("login.jsp").forward(request,response);
				}else{//如果tea不为空，说明教师表中有这个账号，即(账号没错)
					System.out.println("教师账号验证通过");
					
					if(password.equals(tea.getTpassword()) ){//验证密码是否正确
						System.out.println("教师密码验证通过");
						
						//验证通过
						System.out.println("教师验证通过，登录成功!");
						/*//设置request属性
						request.setAttribute("account", account);
						request.setAttribute("name", tea.getTname().toString());
						request.setAttribute("password", password);
						request.setAttribute("type", "教师");
						*/
						//设置session属性
						HttpSession session = request.getSession();
						session.setAttribute("account", account);//账号
				  		session.setAttribute("name", tea.getTname().toString());//姓名
				  		session.setAttribute("type", "教师");//类型
						//重定向
						request.getRequestDispatcher("/teacherServlet").forward(request,response);  
						
					}else{
						System.out.println("教师密码错误");
						request.setAttribute("flag", "1");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
				}
				break;
				
			case "Student" ://学生
				
				System.out.println("学生类型验证通过");
				//定义StudentDao的对象
				StudentDao sdao = new StudentDao();
				//连接数据库，获取学生表中的数据
				//找到与该学生号匹配的这条(数据库)记录
				Student stu = sdao.QueryBySno(account);
				if( stu==null ){
					System.out.println("没有获取到与学生账号对应的这条记录");
					request.setAttribute("flag", "1");
					request.getRequestDispatcher("login.jsp").forward(request,response);
				}else{//如果stu不为空，说明学生表中有这个账号，即(账号没错)
					System.out.println("学生账号验证通过");
					
					if(password.equals(stu.getSpassword()) ){//验证密码是否正确
						System.out.println("学生密码验证通过");
						
						//验证通过
						System.out.println("学生验证通过，登录成功!");
						/*//设置request属性
						request.setAttribute("account", account);
						request.setAttribute("name", stu.getSname().toString());
						request.setAttribute("password", password);
						request.setAttribute("type", "学生");*/
						//设置session属性
						HttpSession session = request.getSession();
						session.setAttribute("account", account);//账号
				  		session.setAttribute("name", stu.getSname().toString());//姓名
				  		session.setAttribute("type", "学生");//类型
						//重定向
						request.getRequestDispatcher("/studentServlet").forward(request,response);  
						
					}else{
						System.out.println("学生密码错误");
						request.setAttribute("flag", "1");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
				}
				break;
				
			default :
				System.out.println("账号或密码错误");
				request.setAttribute("flag", "1");
				request.getRequestDispatcher("login.jsp").forward(request,response);
				break;				
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

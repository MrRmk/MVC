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
//ע�⣬ע��servlet
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
//=====================================ʹ��servletÿ�θ��Ķ�Ҫ����tomcat===========================================   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//�����������
		request.setCharacterEncoding("UTF-8");
		/*response.setContentType("text/html;charset = utf-8");*/
		
		/*//��¼����������
		String type1 = "Manager";//����Ա
		String type2 = "Teacher";//��ʦ
		String type3 = "Student";//ѧ��
		*/
		//��ȡ��¼ҳ�������--�˺š����������--------getParamenter()�õ�����Object���͵�ֵ----------
		String account = request.getParameter("account").toString();
		String password = request.getParameter("password").toString();
		String type = request.getParameter("type").toString();
		
  		
		//��ӡ�˺š����������
		System.out.println("acccount="+account);
		System.out.println("password="+password);
		System.out.println("type="+type);
		
		//��֤���
		
		switch( type ){
			case "Manager"://����Ա
				
				System.out.println("����Ա������֤ͨ��");
				//����ManagerDao�Ķ���
				ManagerDao mdao = new ManagerDao();
				//�������ݿ⣬��ȡ����Ա���е�����
				//�ҵ���ù���Ա�˺�Managerƥ�������(���ݿ�)��¼
				Manager man = mdao.QueryByMno(account);
				if( man==null ){
					System.out.println("û�л�ȡ�������Ա�˺Ŷ�Ӧ��������¼");
					request.setAttribute("flag", "1");
					request.getRequestDispatcher("login.jsp").forward(request,response);
					
				}else{//���man��Ϊ�գ�˵������Ա����������˺ţ���(�˺�û��)
					System.out.println("����Ա�˺���֤ͨ��");
					if(password.equals(man.getMpassword()) ){//��֤�����Ƿ���ȷ
						System.out.println("����Ա������֤ͨ��");
						//��֤ͨ��
						System.out.println("����Ա��֤ͨ������¼�ɹ�!");
						/*//����request����
						request.setAttribute("account", account);
						request.setAttribute("name", man.getMname().toString());
						request.setAttribute("password", password);
						request.setAttribute("type", "����Ա");*/
						//����session����
						HttpSession session = request.getSession();
						session.setAttribute("account", account);//�˺�
				  		session.setAttribute("name", man.getMname().toString());//����
				  		session.setAttribute("type", "����Ա");//����
						
				  		System.out.println("welcomeҳ��:"+account+man.getMname().toString()+type);
						//�ض���
						request.getRequestDispatcher("/page/manager/manager.jsp").forward(request,response);  
					}else{
						System.out.println("����Ա�������");
						request.setAttribute("flag", "1");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
				}
				break;
				
			case "Teacher" ://��ʦ
				
				System.out.println("��ʦ������֤ͨ��");
				//����TeacherDao�Ķ���
				TeacherDao tdao = new TeacherDao();
				//�������ݿ⣬��ȡ��ʦ���е�����
				//�ҵ���ý�ʦ��ƥ�������(���ݿ�)��¼
				Teacher tea = tdao.QueryByTno(account);
				if( tea==null ){
					System.out.println("û�л�ȡ�����ʦ�˺Ŷ�Ӧ��������¼");
					request.setAttribute("flag", "1");
					request.getRequestDispatcher("login.jsp").forward(request,response);
				}else{//���tea��Ϊ�գ�˵����ʦ����������˺ţ���(�˺�û��)
					System.out.println("��ʦ�˺���֤ͨ��");
					
					if(password.equals(tea.getTpassword()) ){//��֤�����Ƿ���ȷ
						System.out.println("��ʦ������֤ͨ��");
						
						//��֤ͨ��
						System.out.println("��ʦ��֤ͨ������¼�ɹ�!");
						/*//����request����
						request.setAttribute("account", account);
						request.setAttribute("name", tea.getTname().toString());
						request.setAttribute("password", password);
						request.setAttribute("type", "��ʦ");
						*/
						//����session����
						HttpSession session = request.getSession();
						session.setAttribute("account", account);//�˺�
				  		session.setAttribute("name", tea.getTname().toString());//����
				  		session.setAttribute("type", "��ʦ");//����
						//�ض���
						request.getRequestDispatcher("/teacherServlet").forward(request,response);  
						
					}else{
						System.out.println("��ʦ�������");
						request.setAttribute("flag", "1");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
				}
				break;
				
			case "Student" ://ѧ��
				
				System.out.println("ѧ��������֤ͨ��");
				//����StudentDao�Ķ���
				StudentDao sdao = new StudentDao();
				//�������ݿ⣬��ȡѧ�����е�����
				//�ҵ����ѧ����ƥ�������(���ݿ�)��¼
				Student stu = sdao.QueryBySno(account);
				if( stu==null ){
					System.out.println("û�л�ȡ����ѧ���˺Ŷ�Ӧ��������¼");
					request.setAttribute("flag", "1");
					request.getRequestDispatcher("login.jsp").forward(request,response);
				}else{//���stu��Ϊ�գ�˵��ѧ������������˺ţ���(�˺�û��)
					System.out.println("ѧ���˺���֤ͨ��");
					
					if(password.equals(stu.getSpassword()) ){//��֤�����Ƿ���ȷ
						System.out.println("ѧ��������֤ͨ��");
						
						//��֤ͨ��
						System.out.println("ѧ����֤ͨ������¼�ɹ�!");
						/*//����request����
						request.setAttribute("account", account);
						request.setAttribute("name", stu.getSname().toString());
						request.setAttribute("password", password);
						request.setAttribute("type", "ѧ��");*/
						//����session����
						HttpSession session = request.getSession();
						session.setAttribute("account", account);//�˺�
				  		session.setAttribute("name", stu.getSname().toString());//����
				  		session.setAttribute("type", "ѧ��");//����
						//�ض���
						request.getRequestDispatcher("/studentServlet").forward(request,response);  
						
					}else{
						System.out.println("ѧ���������");
						request.setAttribute("flag", "1");
						request.getRequestDispatcher("login.jsp").forward(request,response);
					}
				}
				break;
				
			default :
				System.out.println("�˺Ż��������");
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

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

		//request �����������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		
		//����ѧ����Ϣ����(sUpdate.jsp)���ݹ���������
		HttpSession session = request.getSession();
		String account = session.getAttribute("account").toString();
		String password = request.getParameter("password");//�����ԭ����
		String password1 = request.getParameter("password1");//�����������
		String password2 = request.getParameter("password2");//�����ȷ������
		StudentDao stu = new StudentDao();
		Student s = stu.QueryBySno(account);
		String ps = s.getSpassword();//���ݿ��е�ԭ����
		
		System.out.println("sUpdateServletҳ��1****"+account+password+password1+password2);
		
		if( ps.equals(password) ){//��� '�����ԭ����' �� '���ݿ��е�ԭ����' ��ͬ
			if( !password.equals(password1) ){//��� '�����ԭ����' �� '�����������' ����ͬ
				if( password1.equals(password2) ){//��� '�����������' �� '�����ȷ������' ��ͬ
					//����sql����ѧ����Dao�Ķ���ͨ��StudentDao�Ķ�����ò��뺯��Updatedata()�����뵽���ݿ�
					String sql = "UPDATE Student SET Spassword='"+password1+"' WHERE Sno='"+account+"'; "; 					
					//flag����ִ�е�����
					flag = stu.UpdateData(sql);	//��ʱflag=1��			
					//�޸����ݺ�ҳ�淵�ص���ǰ(page/student/sUpdate.jsp)ҳ��
					request.setAttribute("flag", flag);
					request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);	
				}else{
					flag = 2;
					//�޸����ݺ�ҳ�淵�ص���ǰ(page/student/sUpdate.jsp)ҳ��
					request.setAttribute("flag", flag);
					request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);
				}
			
			}else{
				flag = 3;
				//�޸����ݺ�ҳ�淵�ص���ǰ(page/student/sUpdate.jsp)ҳ��
				request.setAttribute("flag", flag);
				request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);
			}
		}else{
			flag = 4;
			//�޸����ݺ�ҳ�淵�ص���ǰ(page/student/sUpdate.jsp)ҳ��
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/page/student/sUpdatePassword.jsp").forward(request, response);
		}
		
		System.out.println("mUpdateTeachrServletҳ��2****"+password+password1+password2);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

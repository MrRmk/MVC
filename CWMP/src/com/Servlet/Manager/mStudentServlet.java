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
	List<Student> list = null;//���ѧ�����е���������
	List<Student> listCLno = null;//�������������ѧ��������
	String CLno = null;//�༶��
	
	StudentDao stu = null;//ѧ��Dao����
	CLassDao cl = null;//�༶Dao����
	CLass c = null;//�༶��Ķ����������ͨ���༶�Ų��ҵ���һ���༶���¼
	int CLnumber = 0;//�༶����
	
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

		//���������������
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");		
		System.out.println("***���ǻ�ȡѧ�����servlet****");
		
		//************�״���ת��ȡ����ѧ������*********************************************************************************
		//����StudentDao����ͨ��StudentDao�����������ݣ���ȡѧ�����е���������
		stu = new StudentDao();
		list = stu.QueryAll();//���ѧ�����е���������
		
		//************�����ѯ֮�󣬰�ѡ�еİ༶�Ŵ����������գ��ж��Ƿ�Ϊ��*********************************************************************************
		if( request.getParameter("CLno") != null ){
			CLno = request.getParameter("CLno");		
			//ͨ���༶�Ŵ�ѧ�����в��ҵ�������������ѧ��������
			listCLno = stu.QueryByCLno(CLno);
			
			//�����༶CLassDao�Ķ���ͨ��CLassDao�Ķ����������ݻ�ȡ�༶������ð༶��CLno��ͬ��һ����¼
			cl = new CLassDao();
			c = cl.QueryByCLno(CLno);
			CLnumber = c.getCLnumber();//�༶����
			request.setAttribute("CLnumber", CLnumber);
			request.setAttribute("ClassCLno", CLno);
			request.setAttribute("listCLno", listCLno);
		}
		
		if( list == null ){
			System.out.println("û�л�ȡ��ѧ�����е�����!***");
			request.getRequestDispatcher("/page/manager/mStudent.jsp").forward(request, response);
		}else{
			
			//��װ��ѧ�����list���浽request�У�����mStudent.jspҳ��
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

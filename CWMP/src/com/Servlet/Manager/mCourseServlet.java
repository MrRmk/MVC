package com.Servlet.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.CourseDao;
import com.BeanDao.TCCLDao;
import com.BeanDao.TeacherDao;
import com.JavaBean.Course;
import com.JavaBean.TCCL;
import com.JavaBean.Teacher;

/**
 * Servlet implementation class mCourseServlet
 */
@WebServlet("/mCourseServlet")
public class mCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���������������
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("*****���ǻ�ȡ�γ̱��servlet******");
		
		//�����γ̱�CourseDao�Ķ����������ݿ⣬��ȡCourse�γ̱��ŵ�list��
		CourseDao ct = new CourseDao();
		List<Course> listCourse = ct.QueryAll();	
		/*//����תmTeacher.jspҳ��ʱ���Ͱ�Ҫ��ʾ�����ݣ���mTeacher.jsp׼����
		TeacherDao tDao = new TeacherDao();
		List<Teacher> listTeacher = tDao.QueryAll();
		//�����ڿα�TCCLDao�Ķ����������ݿ⣬��ȡTCCL�ڿα��ŵ�list��
		TCCLDao tc = new TCCLDao();
		List<TCCL> listTCCL = tc.QueryAll();
		
		//���ڿα��н�ʦ�Ŷ�Ӧ�Ľ�ʦ���¼�ŵ�����a��
		Teacher[] t = new Teacher[listTCCL.size()];//(Teacher[]) new List[listTCCL.size()];
		for( int i=0; i<listTCCL.size(); i++ ){
			t[i] = tDao.QueryByTno(listTCCL.get(i).getTno().toString());
		}
		//���ڿα��пγ̺�Cno��Ӧ�Ŀγ̱��¼�ŵ�����c��
		Course[] c = new Course[listTCCL.size()];
		int j = 0;
		for( int i=0; i<listTCCL.size(); i++ ){
			if( (ct.QueryByCno(listTCCL.get(i).getCno().toString())) !=null ){
				c[j] = ct.QueryByCno(listTCCL.get(i).getCno().toString());
				j = j + 1;
			}			
		}*/
		
		
		
		/*if( (listCourse == null)||(listTCCL == null)||(listTeacher == null) ){		*/
		if( listCourse == null ){		
			System.out.println("***û�л�ȡ��Course�γ̱��е�����*******");
			request.getRequestDispatcher("/page/manager/mCourse.jsp").forward(request,response);
		}else{
			
			//��װ�пγ̱��list�浽request�У�����mCourse.jspҳ��
			//request.setAttribute("c", c);
			request.setAttribute("listCourse", listCourse);
			//request.setAttribute("t", t);
			System.out.println("****mCourse.jsp****");
			request.getRequestDispatcher("/page/manager/mCourse.jsp").forward(request, response);
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

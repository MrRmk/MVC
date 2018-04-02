package com.Servlet.Manager;

import java.io.IOException;
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
 * Servlet implementation class mTCCLServlet
 */
@WebServlet("/mTCCLServlet")
public class mTCCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mTCCLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����������
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("***���ǻ�ȡ�ڿα��mTCCLServlet***");
		
		//����תmTCCL.jspҳ��ʱ���Ͱ�Ҫ��ʾ�����ݣ���mTCCL.jsp׼����
		TCCLDao tc = new TCCLDao();
		List<TCCL> listTCCL = tc.QueryAll();
		/*//����תmTeacher.jspҳ��ʱ���Ͱ�Ҫ��ʾ�����ݣ���mTeacher.jsp׼����
		TeacherDao tDao = new TeacherDao();
		List<Teacher> listTeacher = tDao.QueryAll();
		//�����γ̱�CourseDao�Ķ����������ݿ⣬��ȡCourse�γ̱��ŵ�list��
		CourseDao ct = new CourseDao();
		List<Course> listCourse = ct.QueryAll();		*/
		
		if( (listTCCL == null) ){			
			System.out.println("mTCCL���ڿα�û�л�ȡ��!");
			request.getRequestDispatcher("/page/manager/mTCCL.jsp").forward(request,response);
		}else{
			
			//��װ���ڿα��list���浽request�У�����mTCCL.jspҳ��
			//request.setAttribute("listCourse", listCourse);
			request.setAttribute("listTCCL", listTCCL);
			//request.setAttribute("listTeacher", listTeacher);
			System.out.println("***mTCCLServlet��ת��mTCCL.jsp***");
			request.getRequestDispatcher("/page/manager/mTCCL.jsp").forward(request, response);
		
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

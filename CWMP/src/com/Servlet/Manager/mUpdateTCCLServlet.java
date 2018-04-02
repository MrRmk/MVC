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
import com.JavaBean.Teacher;

/**
 * Servlet implementation class mUpdateTCCLServlet
 */
@WebServlet("/mUpdateTCCLServlet")
public class mUpdateTCCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
	String Tno = "";
	String Cno = "";
	String YCno = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mUpdateTCCLServlet() {
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
		
		System.out.println("***�������ڿα���������ݵ�mUpdateTCCLServletҳ��**");
  		
		//��������ڿ���Ϣ����(updateTCCL.jsp)���ݹ���������
		String Tname = request.getParameter("Tname");//��ʦ��
		String CLno = request.getParameter("CLno");//�޸ĺ�İ༶��
		String Cname = request.getParameter("Cname");//�޸ĺ�Ŀγ���
		String YCLno = request.getParameter("YCLno");//ԭ���İ༶��
		
		String YCname = request.getParameter("YCname");//ԭ���Ŀγ���
		System.out.println("***mUpdateTCCLServletҳ��***"+Tname+CLno+Cname+YCname+YCLno);
		
		/*//�����������
		Tname = new String(Tname.getBytes("iso8859-1"),"utf-8"); 
		CLno = new String(CLno.getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname.getBytes("iso8859-1"),"utf-8"); 
		System.out.println("***mUpdateTCCLServletҳ��***"+Tname+CLno+Cname);*/
		
		
		
		//ͨ����ʦ�����ҽ�ʦ��
		TeacherDao tDao = new TeacherDao();
		List<Teacher> list = tDao.QueryAll();
		for(int i=0; i<list.size(); i++){
			if( Tname.equals(list.get(i).getTname()) ){
				Tno = list.get(i).getTno();
				System.out.println("***mUpdateTCCLServletҳ��***Tno:"+Tno);
			}
		}
		//ͨ���γ������ҿγ̺�
		CourseDao c = new CourseDao();
		List<Course> listCourse = c.QueryAll();
		for(int i=0; i<listCourse.size(); i++){
			if( Cname.equals(listCourse.get(i).getCname()) ){
				Cno = listCourse.get(i).getCno();
				System.out.println("***mUpdateTCCLServletҳ��***Cno:"+Cno);
			}
			if( YCname.equals(listCourse.get(i).getCname()) ){
				YCno = listCourse.get(i).getCno();
				System.out.println("***mUpdateTCCLServletҳ��***YCno:"+YCno);
			}
		}
		
		//�����������
		/*Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 
		Cname = new String(Cname .getBytes("iso8859-1"),"utf-8"); 
		Ccredit = new String(Ccredit .getBytes("iso8859-1"),"utf-8");*/
  		
  		System.out.println("***mUpdateTCCLServletҳ��***"+Tno+CLno+Cno);
		
		//����sql�����ڿα�Dao�Ķ���ͨ��TCCLDao�Ķ�����ø��º���Updatedata()�����뵽���ݿ�
  		String sql = "UPDATE TCCL SET Cno='"+Cno+"',CLno='"+CLno+"' WHERE Tno='"+Tno+"' and CLno='"+YCLno+"' and Cno='"+YCno+"'; "; 
  		TCCLDao stu = new TCCLDao();
  		//flag����ִ�е�����
  		flag = stu.UpdateData(sql);
  		System.out.println("***mUpdateTCCLServletҳ��***flag:"+flag);
		//�޸����ݺ�ҳ�淵�ص���ǰ(page/manager/updateTCCL.jsp)ҳ��
  		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateTCCL.jsp").forward(request, response);
		
		System.out.println("mUpdateTCCLServletҳ��"+Cno+CLno+Tno);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

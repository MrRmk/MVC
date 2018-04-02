package com.Servlet.Manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.TCCLDao;

/**
 * Servlet implementation class mDeleteTCCLServlet
 */
@WebServlet("/mDeleteTCCLServlet")
public class mDeleteTCCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mDeleteTCCLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����������
		request.setCharacterEncoding("UTF-8");			
		System.out.println("***����ɾ���ڿα������ݵ�mDeleteTCCLServletҳ��***");
				
		String Tno = request.getParameter("Tno").toString();
		String CLno = request.getParameter("CLno").toString();
		String Cno = request.getParameter("Cno").toString();
		//�����������
		Tno = new String(Tno .getBytes("iso8859-1"),"utf-8"); 		
  		CLno = new String(CLno .getBytes("iso8859-1"),"utf-8"); 		
  		Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 		
  		System.out.println("mDeleteTCCLServletҳ��"+Cno);
		
		/*//����sql���Ϳγ̱�Dao�Ķ���ͨ��CourseDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "DELETE FROM Course WHERE Cno='"+Cno+"'; "; 
		CourseDao stu = new CourseDao();*/
  		
  		//����sql�����ڿα�TCCLDao�Ķ���ͨ��TCCLDao�Ķ�����ò��뺯��UpdateData()�����뵽���ݿ�
		String sql = "DELETE FROM TCCL WHERE Tno='"+Tno+"' and CLno='"+CLno+"' and Cno='"+Cno+"'; "; 
		TCCLDao tc = new TCCLDao();
		//flag����ִ�е�����
		flag = tc.UpdateData(sql);
		
		//�޸����ݺ�ҳ�淵�ص��γ̱�(mCourse.jsp)ҳ��
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("mTCCLServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

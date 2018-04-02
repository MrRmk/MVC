package com.Servlet.Manager;

/*import java.net.*;
import java.util.*;*/
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BeanDao.TeacherDao;

/**
 * Servlet implementation class mUpdateTeacherServlet
 */
@WebServlet("/mUpdateTeacherServlet")
public class mUpdateTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mUpdateTeacherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request �����������
		/*request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");*/
		
		//������ӽ�ʦ��Ϣ����(updateTeacher.jsp)���ݹ���������
		String Tno = request.getParameter("Tno");
		String Tname = request.getParameter("Tname");
		String sex = request.getParameter("sex");
		String Toffice = request.getParameter("Toffice");
		String Tpassword = request.getParameter("Tpassword");
		String Tdept= request.getParameter("Tdept");
		System.out.println("mUpdateTeachrServletҳ��1****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
		//�����������
  		Tno = new String(Tno.getBytes("ISO-8859-1"),"utf-8"); 
  		Tname = new String(Tname.getBytes("ISO-8859-1"),"utf-8"); 
  		sex = new String(sex.getBytes("ISO-8859-1"),"utf-8"); 
  		Toffice = new String(Toffice.getBytes("ISO-8859-1"),"utf-8"); 
  		Tpassword = new String(Tpassword.getBytes("ISO-8859-1"),"utf-8"); 
  		Tdept = new String(Tdept.getBytes("ISO-8859-1"),"utf-8"); 
  		
		System.out.println("mUpdateTeachrServletҳ��2****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
		
		//����sql���ͽ�ʦ��Dao�Ķ���ͨ��TeacherDao�Ķ�����ò��뺯��Updatedata()�����뵽���ݿ�
		String sql = "UPDATE Teacher SET Tname='"+Tname+"',Tsex='"+sex+"',Toffice='"+Toffice+"',Tpassword='"+Tpassword+"',Tdept='"+Tdept+"' WHERE Tno='"+Tno+"'; "; 
		TeacherDao tea = new TeacherDao();
		//flag����ִ�е�����
		flag = tea.UpdateData(sql);
		
		//�޸����ݺ�ҳ�淵�ص���ǰ(page/manager/updateTeacher.jsp)ҳ��
		/*request.setAttribute("flag", flag);
		request.setAttribute("Tno",Tno);
		request.setAttribute("Tname",Tname);
		request.setAttribute("sex",sex);
		request.setAttribute("Toffice",Toffice);
		request.setAttribute("Tpassword",Tpassword);
		request.setAttribute("Tdept",Tdept);*/
		
		//�����Ӵ���ֵ֮ǰ�ñ���һ��(en����)
  		Tno = java.net.URLEncoder.encode(Tno,"UTF-8");//����
  		Tname = java.net.URLEncoder.encode(Tname,"UTF-8");//����
  		sex = java.net.URLEncoder.encode(sex,"UTF-8");//����
  		Toffice = java.net.URLEncoder.encode(Toffice,"UTF-8");//����
  		Tpassword = java.net.URLEncoder.encode(Tpassword,"UTF-8");//����
  		Tdept = java.net.URLEncoder.encode(Tdept,"UTF-8");//����
  		
 		System.out.println("mUpdateTeachrServletҳ��3****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);
 		request.setAttribute("flag", flag);
		request.getRequestDispatcher("page/manager/updateTeacher.jsp?Tno="+Tno+"&Tname="+Tname+"&sex="+sex+"&Toffice="+Toffice+"&Tpassword="+Tpassword+"&Tdept="+Tdept+" ").forward(request, response);
		
		/*System.out.println("mUpdateTeachrServletҳ��2****"+Tno+Tname +sex +Toffice +Tpassword +Tdept);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

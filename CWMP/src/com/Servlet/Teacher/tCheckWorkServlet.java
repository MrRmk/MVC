package com.Servlet.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BeanDao.CcheckDao;
import com.BeanDao.StudentDao;
import com.BeanDao.TCCLDao;
import com.BeanDao.WorkDao;
import com.JavaBean.*;

/**
 * Servlet implementation class tCheckWorkServlet
 */
@WebServlet("/tCheckWorkServlet")
public class tCheckWorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       static int count = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tCheckWorkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//解决中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//count++;
		
		//获取教师账号,即教师号
		HttpSession session = request.getSession();
		String Tno = session.getAttribute("account").toString();
		
		
		//**********************************每次进入查询数据********************************
		
			//根据教师号查询作业表,获得作业对象列表(作业号\班级号)
		WorkDao wDao = new WorkDao();
		List<Work>  listWork = new ArrayList();
		listWork = wDao.QueryByTno(Tno);
		request.setAttribute("listWork", listWork);
			
			//根据作业表对象列表listListWork_CLno里的班级号查询学生每个班级对应的学生对象列表
			//根据班级查询学生
		StudentDao sDao = new StudentDao();
		List<List<Student>> listListStudent  = new ArrayList();//将查询结果集合放入集合集合中
			//将查询到的学生集合存入学生集合 集合中
		for(int i = 0; i<listWork.size(); i++){
			listListStudent.add(sDao.QueryByCLno(listWork.get(i).getCLno().toString()));
		}
			//将学生集合中的集合传到jsp页面
		request.setAttribute("listListStudent", listListStudent);

		
		
		//**********************************从jsp跳转过来获取数据********************************
		
			
			//获取表单提交的数据_下拉列表
		if((request.getParameter("Wno") != null) && (!request.getParameter("Wno").equals("== 作 业 =="))){
			String Wno = request.getParameter("Wno");
			String CLno = request.getParameter("CLno");
			String Sname = request.getParameter("Sname");
			request.setAttribute("Wno", Wno);
			request.setAttribute("CLno", CLno);
			request.setAttribute("Sname", Sname);
			System.out.println(""
					+ "**********************************从jsp跳转过来获取数据********************************Wno:"+Wno+"\n"
					+ "**********************************从jsp跳转过来获取数据********************************Wno:"+CLno+"\n"
					+"**********************************从jsp跳转过来获取数据********************************Sname:"+Sname);
			//通过学号和提交时间到系统盘读取文件
				//根据姓名查询对应的学号
			String Sno = sDao.QueryBySname(Sname).getSno();
			request.setAttribute("Sno", Sno);
				//根据作业号和学号到批改表查询提交时间
			CcheckDao checkDao = new CcheckDao();
			List<Ccheck> listCcheck = new ArrayList<>();
			listCcheck = checkDao.QueryByWno(Wno);
			String Uptime = null;
			String path_ = null;
			String work = null;
			for(int i=0; i<listCcheck.size(); i++){
				if(listCcheck.get(i).getSno().equals(Sno)){
					Uptime = listCcheck.get(i).getUptime();
					path_ = listCcheck.get(i).getChanswer();
				}
			}
			//读取文件
			String path = path_+Sno+"-"+Uptime+"-作业备注.txt";
				//创建文件读对象
			try{
//			File f  = new File(path);  
//	        FileInputStream in = new FileInputStream(f);  
//	        	// 指定读取文件时以UTF-8的格式读取  
//	        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));  
//	        work = br.readLine();  
//	        br.close();
				work = readFromFile(path);
			request.setAttribute("work", work);
			}catch(Exception e){
				int flag = 1;
				request.setAttribute("flag", flag);
				e.printStackTrace();
			}
			System.out.println("path:"+path+"\n"+"work:"+work);
			count++;
		}
		/*//获取表单提交的数据_批改内容
		if(request.getParameter("Wno") != null){
			
			String Wno = request.getParameter("Wnoa");
			String CLno = request.getParameter("Lnoa");
			String Sno = request.getParameter("Snoa");
			String Chscore = request.getParameter("Chscore");
			String Chremark = request.getParameter("Chremark");
			System.out.println("wcs:"+Wno+CLno+Sno
					+ "&&&&&&&&&&&&&&&&&&&&&&&&从jsp跳转过来获取数据&&&&&&&&&&&&&&&&&&&&&&&&Chscore:"+Chscore+"\n"
					+"&&&&&&&&&&&&&&&&&&&&&&&&从jsp跳转过来获取数据&&&&&&&&&&&&&&&&&&&&&&&&Chremark:"+Chremark);
			String Chtime = "";
			//获取当前批改时间
			Calendar now = Calendar.getInstance();
			Chtime = now.get(Calendar.YEAR)+"."+(now.get(Calendar.MONTH) + 1)+"."+now.get(Calendar.DAY_OF_MONTH)+"-"+
			now.get(Calendar.HOUR_OF_DAY)+"."+now.get(Calendar.MINUTE)+"."+now.get(Calendar.SECOND);
			System.out.println(Chtime+"\ncount:####"+count);
			Ccheck check = new Ccheck();
			check.setWno(Wno);
			check.setSno(Sno);
			check.setChscore(Integer.parseInt(Chscore));
			check.setChremark(Chremark);
			check.setChtime(Chtime);
			CcheckDao checkDao = new CcheckDao();
			int flag1 = checkDao.Insert_one(check);
			request.setAttribute("flag1", flag1);
			System.out.println("flag1:"+flag1);
		}*/
		
		
		
		
		System.out.println("开始跳转jsp!!!!!!!!!!!");
		request.getRequestDispatcher("/page/teacher/tCheckWork.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static String readFromFile(String path) throws IOException {  
        File file = new File(path);  
        if (!file.exists() || file.isDirectory())  
            throw new FileNotFoundException();  
        BufferedReader br = new BufferedReader(new FileReader(file));  
        String temp = null;  
        StringBuffer sb = new StringBuffer();  
        temp = br.readLine();  
        while (temp != null) {  
            // 读取的每一行内容后面加上一个空格用于拆分成语句  
            sb.append(temp + " <br>");  
            temp = br.readLine();  
        }  
        return sb.toString();  
    } 
	
	
	
	
	/*//排序
	private List<Work> Sort(List<Work> list){
		for (i = 0; i < 10; i++)  
	    {  
	        for (j = i + 1; j < 10; j++)  
	        {  
	            if (str[i] > str[j])  
	            {  
	                swap(&str[i], &str[j]);  
	            }  
	        }  
	    }  
		
		List<Work> listResult= new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			for(int j=i+1; j<list.size(); j++){
				if(Integer.parseInt(list.get(i).getWno())<Integer.parseInt(list.get(j).getWno())){
					
				}
			}
		}
		return list;
	}*/

}




/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
//	response.getWriter().append("Served at: ").append(request.getContextPath());
//	response.getWriter().append("Served at: ").append(request.getContextPath());
	//解决中文乱码
	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html");
	
	
	
	//获取教师账号,即教师号
	HttpSession session = request.getSession();
	String Tno = session.getAttribute("account").toString();
	
	
	//**********************************每次进入查询数据********************************
	
		//根据教师号查询作业表,获得作业对象列表(作业号\班级号)
	WorkDao wDao = new WorkDao();
	List<Work>  listWork = new ArrayList();
	List<List<Work>> listListWork_CLno = new ArrayList();//存储同一班级的作业对象列表 集合
	listWork = wDao.QueryByTno(Tno);
	request.setAttribute("listWork", listWork);
		//各类同一班级的作业对象列表集合
	for(int i=0; i<listWork.size(); i++){
		List<Work> listWorkTemp = new ArrayList();
		for(int j=i; j<listWork.size()-1; j++){
			if(listWork.get(j).getCLno().equals(listWork.get(j+1).getCLno())){
				listWorkTemp.add(listWork.get(j));
			}
			else{
				i = j+1;
				j = listWork.size()-1;
			}
		}
		listWorkTemp.add(listWork.get(i-1));
		listListWork_CLno.add(listWorkTemp);
	}
	if(listWork.get(listWork.size()-2).getCLno().equals(listWork.get(listWork.size()-1).getCLno())){
		listListWork_CLno.get(listListWork_CLno.size()-1).add(listWork.get(listWork.size()-1));
	}else{
		List<Work> listWorkTemp = new ArrayList();
		listWorkTemp.add(listWork.get(listWork.size()-1));
		listListWork_CLno.add(listWorkTemp);
	}
	
	for(int i=0; i<listListWork_CLno.size(); i++){
		System.out.println("\n**************listListWork_CLno"+" "+i+":");
		for(int j=0; j<listListWork_CLno.get(i).size(); j++){
			System.out.println("**************"+listListWork_CLno.get(i).get(j).getCLno()+listListWork_CLno.get(i).get(j).getWcontent());
			
		}
	}
	
	
	
		//去除listListWork_CLno中重复的班级号
	for(int i=0; i<listListWork_CLno.size(); i++){
		for(int j=0; j<listListWork_CLno.get(i).size(); j++){
			listListWork_CLno.get(i).remove(0);
//			for(int k=j; k<listListWork_CLno.get(i).size()-1; k++){
//				
//			}
		}
	}
	List<List<Work>> listListResultWork_CLn0 = new ArrayList();
	for(int i=0; i<listListWork_CLno.size(); i++){
		listListResultWork_CLn0.add(new ArrayList<Work>());
		for(int j=0; j<listListWork_CLno.get(i).size(); j++){
			listListResultWork_CLn0.get(i).add(listListWork_CLno.get(i).get(j));
			j=listListResultWork_CLn0.get(i).size();
//			for(int k=j; k<listListWork_CLno.get(i).size()-1; k++){
//				
//			}
		}
	}
	
	for(int i=0; i<listListResultWork_CLn0.size(); i++){
		System.out.println("\n@@@@@@@@@@@@@@@listListWork_CLno"+" "+i+":");
		for(int j=0; j<listListResultWork_CLn0.get(i).size(); j++){
			System.out.println("@@@@@@@@@@@@@@@@@@"+listListResultWork_CLn0.get(i).get(j).getCLno()+listListResultWork_CLn0.get(i).get(j).getWcontent());
			
		}
	}
	
	request.setAttribute("listListWork_CLno", listListResultWork_CLn0);
	
		//根据作业表对象列表listListWork_CLno里的班级号查询学生每个班级对应的学生对象列表
		//根据班级查询学生
	StudentDao sDao = new StudentDao();
	List<List<Student>> listListStudent  = new ArrayList();//将查询结果集合放入集合集合中
		//将查询到的学生集合存入学生集合 集合中
	for(int i = 0; i<listListResultWork_CLn0.size(); i++){
		for(int j=0; j<listListResultWork_CLn0.get(i).size(); j++){
			listListStudent.add(sDao.QueryByCLno(listListResultWork_CLn0.get(i).get(j).getCLno().toString()));
		}
	}
	
//	for(int i = 0; i<listWork.size(); i++){
//		listListStudent.add(sDao.QueryByCLno(listWork.get(i).getCLno().toString()));
//	}
		//将学生集合中的集合传到jsp页面
	request.setAttribute("listListStudent", listListStudent);
	
	
	
	//创建TCCLDao对象,即授课表
	TCCLDao tcclDao = new TCCLDao();
	List<TCCL> listTCCL = new ArrayList<>();
	listTCCL = tcclDao.QueryByTno(Tno);
	request.setAttribute("listTCCL", listTCCL);
	
	
	
	
	
	
	
	//**********************************从jsp跳转过来获取数据********************************
	
	
		
		
		
		//创建文件读对象
	File f  = new File("D:/cwmp/test2.txt");  
    FileInputStream in = new FileInputStream(f);  
    // 指定读取文件时以UTF-8的格式读取  
    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));  
    String line = br.readLine();  
    br.close();
	request.setAttribute("work", line);
	
	
	System.out.println(line);
	request.getRequestDispatcher("/page/teacher/tCheckWork.jsp").forward(request, response);
}*/

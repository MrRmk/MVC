package com.Servlet.Teacher;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@WebServlet("/tCheckWorkServlet1")
public class tCheckWorkServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       static int count = 0;
       int flag2 = 0 ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tCheckWorkServlet1() {
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
		//�����������
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		//count++;
		
		//��ȡ��ʦ�˺�,����ʦ��
		HttpSession session = request.getSession();
		String Tno = session.getAttribute("account").toString();
		
		
		//**********************************ÿ�ν����ѯ����********************************
		
			//���ݽ�ʦ�Ų�ѯ��ҵ��,�����ҵ�����б�(��ҵ��\�༶��)
		WorkDao wDao = new WorkDao();
		List<Work>  listWork = new ArrayList();
		listWork = wDao.QueryByTno(Tno);
		request.setAttribute("listWork", listWork);
			
			//������ҵ������б�listListWork_CLno��İ༶�Ų�ѯѧ��ÿ���༶��Ӧ��ѧ�������б�
			//���ݰ༶��ѯѧ��
		StudentDao sDao = new StudentDao();
		List<List<Student>> listListStudent  = new ArrayList();//����ѯ������Ϸ��뼯�ϼ�����
			//����ѯ����ѧ�����ϴ���ѧ������ ������
		for(int i = 0; i<listWork.size(); i++){
			listListStudent.add(sDao.QueryByCLno(listWork.get(i).getCLno().toString()));
		}
			//��ѧ�������еļ��ϴ���jspҳ��
		request.setAttribute("listListStudent", listListStudent);

		
		
		//**********************************��jsp��ת������ȡ����********************************
		
			
			//��ȡ���ύ������_�����б�
		/*if((request.getParameter("Wno") != null) && (!request.getParameter("Wno").equals("== �� ҵ =="))){
			String Wno = request.getParameter("Wno");
			String CLno = request.getParameter("CLno");
			String Sname = request.getParameter("Sname");
			request.setAttribute("Wno", Wno);
			request.setAttribute("CLno", CLno);
			request.setAttribute("Sname", Sname);
			System.out.println(""
					+ "**********************************��jsp��ת������ȡ����********************************Wno:"+Wno+"\n"
					+ "**********************************��jsp��ת������ȡ����********************************Wno:"+CLno+"\n"
					+"**********************************��jsp��ת������ȡ����********************************Sname:"+Sname);
			//ͨ��ѧ�ź��ύʱ�䵽ϵͳ�̶�ȡ�ļ�
				//����������ѯ��Ӧ��ѧ��
			String Sno = sDao.QueryBySname(Sname).getSno();
			request.setAttribute("Sno", Sno);
				//������ҵ�ź�ѧ�ŵ����ı��ѯ�ύʱ��
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
			//��ȡ�ļ�
			String path = path_+Sno+"-"+Uptime+"-��ҵ��ע.txt";
				//�����ļ�������
			try{
//			File f  = new File(path);  
//	        FileInputStream in = new FileInputStream(f);  
//	        	// ָ����ȡ�ļ�ʱ��UTF-8�ĸ�ʽ��ȡ  
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
		}*/
		
		//������ҵ
		if((request.getParameter("Wno") != null) ){
			String Wno = request.getParameter("Wno");
			String CLno = request.getParameter("CLno");
			//String Sname = request.getParameter("Sname");
			//ͨ����ҵ�Ų�ѯ�ý�ʦ������������ҵ���ύ�б�
			List<Ccheck> listCcheck = new ArrayList<>();
			CcheckDao CcDao = new CcheckDao();
			listCcheck = CcDao.QueryByWno(Wno);
			for( int i=0;i<listCcheck.size();i++ ){
				//�õ�Ҫ���ص��ļ���
		        String fileName = listCcheck.get(i).getSno()+listCcheck.get(i).getUptime()+"-��ҵ��ע";  
				
				//23239283-92489-������.avi
		        /*fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");*/
		        //�ϴ����ļ����Ǳ�����/WEB-INF/uploadĿ¼�µ���Ŀ¼����
		        String fileSaveRootPath= "F:\\SaveWork\\";//��ʦ������ҵ·��
		       // String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
		        //ͨ���ļ����ҳ��ļ�������Ŀ¼
		        String path = "F:\\UploadWork" ;
		        //String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
		        //�õ�Ҫ���ص��ļ�
		        File file = new File(path + "\\" + fileName);
		        //����ļ�������
		        if(!file.exists()){
		        	flag2=5;//��Ҫ���ص���Դ�ѱ�ɾ������
		            request.setAttribute("flag2", flag2);
		            request.getRequestDispatcher("/page/teacher/tCheckWork.jsp").forward(request, response);
		            return;
		        }
		        //�����ļ���
		        String realname = fileName.substring(fileName.indexOf("_")+1);
		        //������Ӧͷ��������������ظ��ļ�
		        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
		        //��ȡҪ���ص��ļ������浽�ļ�������
		        FileInputStream in = new FileInputStream(path + "\\" + fileName);
		        //���������
		        OutputStream out = response.getOutputStream();
		        //����������
		        byte buffer[] = new byte[1024];
		        int len = 0;
		        //ѭ�����������е����ݶ�ȡ������������
		        while((len=in.read(buffer))>0){
		            //��������������ݵ��������ʵ���ļ�����
		            out.write(buffer, 0, len);
		        }
		        //�ر��ļ�������
		        in.close();
		        //�ر������
		        out.close();
			}
        
		}
		/*//��ȡ�ϴ��ļ���Ŀ¼
		       String uploadFilePath = "F:\\work\\";
		        //�洢Ҫ���ص��ļ���
		         Map<String,String> fileNameMap = new HashMap<String,String>();
		        //�ݹ����filepathĿ¼�µ������ļ���Ŀ¼�����ļ����ļ����洢��map������
		        listfile(new File(uploadFilePath),fileNameMap);//File�ȿ��Դ���һ���ļ�Ҳ���Դ���һ��Ŀ¼
	         //��Map���Ϸ��͵�listfile.jspҳ�������ʾ
		        request.setAttribute("fileNameMap", fileNameMap);
		        request.getRequestDispatcher("/listfile.jsp").forward(request, response);*/
		        
		
		
		//��ȡ���ύ������_��������
		if(request.getParameter("Chscore") != null){
			
			String Wno = request.getParameter("Wnoa");
			String CLno = request.getParameter("Lnoa");
			String Sno = request.getParameter("Snoa");
			String Chscore = request.getParameter("Chscore");
			String Chremark = request.getParameter("Chremark");
			System.out.println("wcs:"+Wno+CLno+Sno
					+ "&&&&&&&&&&&&&&&&&&&&&&&&��jsp��ת������ȡ����&&&&&&&&&&&&&&&&&&&&&&&&Chscore:"+Chscore+"\n"
					+"&&&&&&&&&&&&&&&&&&&&&&&&��jsp��ת������ȡ����&&&&&&&&&&&&&&&&&&&&&&&&Chremark:"+Chremark);
			String Chtime = "";
			//��ȡ��ǰ����ʱ��
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
		}
		
		
		
		
		System.out.println("��ʼ��תjsp!!!!!!!!!!!");
		request.getRequestDispatcher("/page/teacher/tCheckWork.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	/**
	    * @Method: findFileSavePathByFileName
	    * @Description: ͨ���ļ����ʹ洢�ϴ��ļ���Ŀ¼�ҳ�Ҫ���ص��ļ�������·��
	    * @Anthor:�°�����
	    * @param filename Ҫ���ص��ļ���
	    * @param saveRootPath �ϴ��ļ�����ĸ�Ŀ¼��Ҳ����/WEB-INF/uploadĿ¼
	    * @return Ҫ���ص��ļ��Ĵ洢Ŀ¼
	    */ 
	    public String findFileSavePathByFileName(String filename,String saveRootPath){
	        int hashcode = filename.hashCode();
	        int dir1 = hashcode&0xf;  //0--15
	        int dir2 = (hashcode&0xf0)>>4;  //0-15
	        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
	        File file = new File(dir);
	        if(!file.exists()){
	            //����Ŀ¼
	            file.mkdirs();
	        }
	        return dir;
	    }
	/*public void listfile(File file,Map<String,String> map){
    	//���file����Ĳ���һ���ļ�������һ��Ŀ¼
    	        if(!file.isFile()){
    	             //�г���Ŀ¼�µ������ļ���Ŀ¼
                 File files[] = file.listFiles();
    	           //����files[]����
              for(File f : files){
    	               //�ݹ�
    	                listfile(f,map);
    	            }
    	        }else{
    	             *//**
    	              * �����ļ������ϴ�����ļ�����uuid_�ļ�������ʽȥ���������ģ�ȥ���ļ�����uuid_����
    	               file.getName().indexOf("_")�����ַ����е�һ�γ���"_"�ַ���λ�ã�����ļ��������ڣ�9349249849-88343-8344_��_��_��.avi
                     ��ôfile.getName().substring(file.getName().indexOf("_")+1)����֮��Ϳ��Եõ���_��_��.avi����
                 *//*
    	             String realName = file.getName().substring(file.getName().indexOf("_")+1);
    	           //file.getName()�õ������ļ���ԭʼ���ƣ����������Ψһ�ģ���˿�����Ϊkey��realName�Ǵ����������ƣ��п��ܻ��ظ�
    	            map.put(file.getName(), realName);
    	         }
         }*/
	public static String readFromFile(String path) throws IOException {  
        File file = new File(path);  
        if (!file.exists() || file.isDirectory())  
            throw new FileNotFoundException();  
        BufferedReader br = new BufferedReader(new FileReader(file));  
        String temp = null;  
        StringBuffer sb = new StringBuffer();  
        temp = br.readLine();  
        while (temp != null) {  
            // ��ȡ��ÿһ�����ݺ������һ���ո����ڲ�ֳ����  
            sb.append(temp + " <br>");  
            temp = br.readLine();  
        }  
        return sb.toString();  
    } 
	
	
	
	
	/*//����
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
	//�����������
	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html");
	
	
	
	//��ȡ��ʦ�˺�,����ʦ��
	HttpSession session = request.getSession();
	String Tno = session.getAttribute("account").toString();
	
	
	//**********************************ÿ�ν����ѯ����********************************
	
		//���ݽ�ʦ�Ų�ѯ��ҵ��,�����ҵ�����б�(��ҵ��\�༶��)
	WorkDao wDao = new WorkDao();
	List<Work>  listWork = new ArrayList();
	List<List<Work>> listListWork_CLno = new ArrayList();//�洢ͬһ�༶����ҵ�����б� ����
	listWork = wDao.QueryByTno(Tno);
	request.setAttribute("listWork", listWork);
		//����ͬһ�༶����ҵ�����б���
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
	
	
	
		//ȥ��listListWork_CLno���ظ��İ༶��
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
	
		//������ҵ������б�listListWork_CLno��İ༶�Ų�ѯѧ��ÿ���༶��Ӧ��ѧ�������б�
		//���ݰ༶��ѯѧ��
	StudentDao sDao = new StudentDao();
	List<List<Student>> listListStudent  = new ArrayList();//����ѯ������Ϸ��뼯�ϼ�����
		//����ѯ����ѧ�����ϴ���ѧ������ ������
	for(int i = 0; i<listListResultWork_CLn0.size(); i++){
		for(int j=0; j<listListResultWork_CLn0.get(i).size(); j++){
			listListStudent.add(sDao.QueryByCLno(listListResultWork_CLn0.get(i).get(j).getCLno().toString()));
		}
	}
	
//	for(int i = 0; i<listWork.size(); i++){
//		listListStudent.add(sDao.QueryByCLno(listWork.get(i).getCLno().toString()));
//	}
		//��ѧ�������еļ��ϴ���jspҳ��
	request.setAttribute("listListStudent", listListStudent);
	
	
	
	//����TCCLDao����,���ڿα�
	TCCLDao tcclDao = new TCCLDao();
	List<TCCL> listTCCL = new ArrayList<>();
	listTCCL = tcclDao.QueryByTno(Tno);
	request.setAttribute("listTCCL", listTCCL);
	
	
	
	
	
	
	
	//**********************************��jsp��ת������ȡ����********************************
	
	
		
		
		
		//�����ļ�������
	File f  = new File("D:/cwmp/test2.txt");  
    FileInputStream in = new FileInputStream(f);  
    // ָ����ȡ�ļ�ʱ��UTF-8�ĸ�ʽ��ȡ  
    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));  
    String line = br.readLine();  
    br.close();
	request.setAttribute("work", line);
	
	
	System.out.println(line);
	request.getRequestDispatcher("/page/teacher/tCheckWork.jsp").forward(request, response);
}*/

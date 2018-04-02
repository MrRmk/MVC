package com.Servlet.Student;

import javax.servlet.http.HttpSession;

import com.BeanDao.StudentDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Student;
import com.JavaBean.Work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
* @ClassName: sUploadWork2Servlet
* @Description: TODO(���������ϴ��ļ���Servlet)
* @author: ���Ͽ�
* @date: 2017-3-7 ����11:35:50
*
* Servlet implementation class sUploadWork1Servlet
*/
@WebServlet("/sUploadWork1Servlet")
public class sUploadWork1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
    * @Method: makeFileName
    * @Description: �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
    * @Anthor:�°�����
    * @param filename �ļ���ԭʼ����
    * @return uuid+"_"+�ļ���ԭʼ����
    */ 
    private String makeFileName(String filename){  //2.jpg
    	
    	SimpleDateFormat simpleDateFormat;  
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss"); 
        Date date = new Date(); 
        String str = simpleDateFormat.format(date); // ��ǰʱ�� 
        
        //Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
        //return UUID.randomUUID().toString() + "_" + filename;
        return  str + "_" + filename;
    }
    
    /**
     * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
    * @Method: makePath
    * @Description: 
    * @Anthor:�°�����
    *
    * @param filename �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼
    * @param savePath �ļ��洢·��
    * @return �µĴ洢Ŀ¼
    */ 
    private String makePath(String filename,String savePath){
        //�õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //�����µı���Ŀ¼
        //String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        String dir = savePath;  //upload\2\3  upload\3\5
        //File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
        File file = new File(dir);
        //���Ŀ¼������
        if(!file.exists()){
            //����Ŀ¼
            file.mkdirs();
        }
        return dir;
    }
    
    public sUploadWork1Servlet() {
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
		
		System.out.println("***���ǻ�ȡ��ҵ���sUploadWork1Servlet****");
		//��ȡѧ��
		HttpSession session = request.getSession(); 
		String account = session.getAttribute("account").toString();
		System.out.println("sUploadWork1Servletҳ��:"+account);
		//��ȡ��ѧ���İ༶��
		StudentDao stu = new StudentDao();
		Student s = stu.QueryBySno(account);
		String CLno = s.getCLno().toString();
		System.out.println("sUploadWork1Servletҳ��CLno:"+CLno);
		//���༶������ҵ���в�ѯ�����е���ҵ��¼���ŵ�����wList��
		WorkDao  wDao = new WorkDao();
		List<Work> wList = (List<Work>) wDao.QueryByCLno(CLno);
	
		/*String text = request.getParameter("textarea");
		System.out.println("textarea:"+text);*/
		
		/*//*************************************************************************
		List<Work>[] AL = null;//�����е�Arraylist �ŵ�list�� ����AL��
		List<Work> ArrayList = null;//��һ���γ̺�Cno��Ӧ����ҵ���еļ�¼�ŵ�һ��Arraylist��
		for(int i=0; i<wList.size(); i++){					  		
	  		String Cno = wList.get(i).getCno().toString();
	  		
	  		//�ѿγ̺�Cno��Ӧ����ҵ���еļ�¼�ŵ�AL��
	  		AL = new List[wList.size()];
	  		ArrayList = new ArrayList<>();
	  		for(int j=0; j<wList.size(); j++){
				if( Cno.equals(wList.get(j).getCno().toString()) ){
					Work w = wList.get(j);//���
					ArrayList.add(w);
				}
			}
	  		AL[i] = ArrayList;//���Arraylist������
		}	
		//�����������еļ��ϴ���jspҳ��
		for( int j=0; j<wList.size(); j++ ){
			request.setAttribute("listWno"+j, AL[j]);
		}*/
		
		//********************************************************************************************
		
		/*if( request.getParameter("select") != null ){
			String select = request.getParameter("select").toString();
			
			System.out.println("***�����ϴ���ҵ����ҳ��sUploadWork2Servlet****"+select);
			
			//�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
	        //String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
			String savePath = "F:\\UploadWork";
			 File savePathFile = new File(savePath);
	            if (!savePathFile.exists()) {
	                //������ʱĿ¼
	            	savePathFile.mkdir();
	            }
	            
	        //�ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
	        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
	        File tmpFile = new File(tempPath);
	        if (!tmpFile.exists()) {
	            //������ʱĿ¼
	            tmpFile.mkdir();
	        }
	        
	        //��Ϣ��ʾ
	        //flag = 1;
	        try{
	            //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
	            //1������һ��DiskFileItemFactory����
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            //���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�
	            factory.setSizeThreshold(1024*10000);//���û������Ĵ�СΪ10000KB�������ָ������ô�������Ĵ�СĬ����10MB
	            //�����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
	            factory.setRepository(tmpFile);
	            //2������һ���ļ��ϴ�������
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            //�����ļ��ϴ�����
	            upload.setProgressListener(new ProgressListener(){
	                public void update(long pBytesRead, long pContentLength, int arg2) {
	                    System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ���" + pBytesRead);
	                    *//**
	                     * �ļ���СΪ��14608,��ǰ�Ѵ���4096
			                                �ļ���СΪ��14608,��ǰ�Ѵ���7367
			                                �ļ���СΪ��14608,��ǰ�Ѵ���11419
			                                �ļ���СΪ��14608,��ǰ�Ѵ���14608
	                     *//*
	                }
	            });
	             //����ϴ��ļ�������������
	            upload.setHeaderEncoding("UTF-8"); 
	            //3���ж��ύ�����������Ƿ����ϴ���������
	            if(!ServletFileUpload.isMultipartContent(request)){
	                //���մ�ͳ��ʽ��ȡ����
	                return;
	            }
	            
	            //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
	            upload.setFileSizeMax(1024*1024*1024);//1GB
	            //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
	            upload.setSizeMax(1024*1024*1024*10);//10GB
	            //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
	            List<FileItem> list = upload.parseRequest(request);
	            for(FileItem item : list){
	                //���fileitem�з�װ������ͨ�����������
	                if(item.isFormField()){
	                    String name = item.getFieldName();
	                    //�����ͨ����������ݵ�������������
	                    String value = item.getString("UTF-8");
	                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
	                    System.out.println(name + "=" + value);
	                }else{//���fileitem�з�װ�����ϴ��ļ�
	                    //�õ��ϴ����ļ����ƣ�
	                    String filename = item.getName();
	                    System.out.println(filename);//�ļ����Ƶľ���·��
	                    if(filename==null || filename.trim().equals("")){
	                        continue;
	                    }
	                    //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
	                    //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
	                    filename = filename.substring(filename.lastIndexOf("\\")+1);
	                    //�õ��ϴ��ļ�����չ��
	                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
	                    //�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
	                    System.out.println("�ϴ����ļ�����չ���ǣ�"+fileExtName);
	                    //��ȡitem�е��ϴ��ļ���������
	                    InputStream in = item.getInputStream();
	                    //�õ��ļ����������
	                    String saveFilename = makeFileName(filename);
	                    //�õ��ļ��ı���Ŀ¼
	                    String realSavePath = makePath(saveFilename, savePath);
	                    //����һ���ļ������
	                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
	                    //����һ��������
	                    byte buffer[] = new byte[1024];
	                    //�ж��������е������Ƿ��Ѿ�����ı�ʶ
	                    int len = 0;
	                    //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
	                    while((len=in.read(buffer))>0){
	                        //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
	                        out.write(buffer, 0, len);
	                    }
	                    //�ر�������
	                    in.close();
	                    //�ر������
	                    out.close();
	                    //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
	                    //item.delete();
	                    flag = 1; //message = "�ļ��ϴ��ɹ���";
	                }
	            }
	        }catch (FileUploadBase.FileSizeLimitExceededException e) {
	            e.printStackTrace();
	            flag = 2;//message = "�����ļ��������ֵ������";
	            request.setAttribute("flag", flag);
	            request.setAttribute("list", wList);
	            request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
	            return;
	        }catch (FileUploadBase.SizeLimitExceededException e) {
	            e.printStackTrace();
	            flag = 3;//message = "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������";
	            request.setAttribute("flag", flag);
	            request.setAttribute("list", wList);
	            request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
	            return;
	        }catch (Exception e) {
	        	flag = 4;//message= "�ļ��ϴ�ʧ�ܣ�";
	            e.printStackTrace();
	        }
		}    
			request.setAttribute("flag",flag);
	        request.setAttribute("list", wList);
	        request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
		
		*/
		
		if( wList == null ){
			System.out.println("û�л�ȡ����ҵ���е�����!***");
			request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
		}else{
			//��װ����ҵ���list���浽request�У�����sDownload.jspҳ��
			request.setAttribute("list", wList);
			System.out.println("****sUploadWork.jsp�ϴ���ҵҳ��******");
			request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);	
		}	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("sUploadWork");
	}

}

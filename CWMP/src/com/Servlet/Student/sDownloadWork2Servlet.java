package com.Servlet.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;   
import java.io.FileOutputStream;  
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;  

import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Random;
import java.util.UUID;//随机生成时间包

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/sDownloadWork2Servlet")
public class sDownloadWork2Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int flag = 0;
    //生成临时文件的路径(即把表中的内容读出来，存到的路径下面)
    private static String path = "F:\\Work\\";   
    //文件路径+名称
    private static String filenameTemp;
    
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sDownloadWork2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
    	//获取此条作业的信息
		String Wno = request.getParameter("Wno");				//作业号
		String Wcontent = request.getParameter("Wcontent");		//作业内容
		String Wtitle = request.getParameter("Wtitle");			//主题
		String Wdeadline = request.getParameter("Wdeadline");	//截止时间
		String Tname = request.getParameter("Tname");			//教师
		String Cno = request.getParameter("Cno");				//课程号
		String Cname = request.getParameter("Cname");			//课程名
		
		//解决中文乱码
		Wno = new String(Wno.getBytes("ISO-8859-1"),"utf-8"); 
		Wcontent = new String(Wcontent.getBytes("ISO-8859-1"),"utf-8"); 
		Wtitle = new String(Wtitle.getBytes("ISO-8859-1"),"utf-8"); 
		Wdeadline = new String(Wdeadline.getBytes("ISO-8859-1"),"utf-8"); 
		Tname = new String(Tname.getBytes("ISO-8859-1"),"utf-8"); 
		Cno = new String(Cno.getBytes("ISO-8859-1"),"utf-8"); 
		Cname = new String(Cname.getBytes("ISO-8859-1"),"utf-8"); 
		
		System.out.println("sDownloadWork2Servlet页面1"+Wno+Wcontent+Wtitle+Wdeadline+Tname+Cname);
		/*//获取学号
		HttpSession session = request.getSession();		//账号(即学号)
		String account = session.getAttribute("sccount").toString();*/
		
		//文件内容
		String fileContent = "\r\n"+"作 业 号："+Wno+"\r\n"+"作业内容："+Wcontent+"\r\n"+"主    题："+Wtitle+"\r\n"+"截止时间："+Wdeadline+"\r\n"+"教    师："+Tname+"\r\n"+"课 程 名："+Cname+"\r\n";		
		System.out.println("sDownloadWork2Servlet页面2"+Wno+Wcontent+Wtitle+Wdeadline+Tname+Cname);
		//生成"当前时间"当做文件名
		//UUID uuid = UUID.randomUUID();
		SimpleDateFormat simpleDateFormat;  
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss"); 
        Date date = new Date(); 
        String str = simpleDateFormat.format(date); // 当前时间 
        Random random = new Random();  
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
        String fN =  rannum+str;//文件名称
        System.out.println("sDownloadWork2Servlet页面3"+fN);
        createFile(str, fileContent);// createFile(文件名, 文件内容);
        
        String fileNameTemp = path+str+".txt";//文件路径+名称+文件类型    
        System.out.println("sDownloadWork2Servlet页面4"+fileNameTemp);
        download1(response,fileNameTemp); 
        
        
        //下载成功之后跳转页面
        flag = 1;
        request.setAttribute("flag", flag);
        request.getRequestDispatcher("/page/student/sDownloadWork.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
        
        /*String path = "F:\\temp\\";//创建文件路径
        String fileNameTemp = path+fileName+".txt";//文件路径+名称+文件类型
    	File file = new File(fileName);//创建文件
		try{
			//如果文件不存在，则创建新的文件
			if( !file.exists() ){
				file.createNewFile();
				//创建文件成功后，写内容到文件里
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}*/
    	// TODO Auto-generated method stub
        doGet(request, response);
    }
    //下载文件操作
    public void download1(HttpServletResponse response,String fileNameTemp ) throws IOException{  
    	
        //获取所要下载文件的路径  
    	String path1 = fileNameTemp;
    	//String path = this.getServletContext().getRealPath(/WEB-INF/web.xml); //从项目路径下找  
        String realPath = path1.substring(path1.lastIndexOf("\\")+1);  
      
        //告诉浏览器是以下载的方法获取到资源  
        //告诉浏览器以此种编码来解析URLEncoder.encode(realPath, "utf-8"))  
        response.setHeader("content-disposition","attachment; filename="+URLEncoder.encode(realPath, "utf-8"));  
        //获取到所下载的资源  
        FileInputStream fis = new FileInputStream(path1);    
        OutputStream out = response.getOutputStream();
        //FileInputStream fis = new FileInputStream("F:\\temp\\myfile.txt");  
        int len = 0;  
        byte [] buf = new byte[1024];  
        while((len=fis.read(buf))!=-1){  
        	out.write(buf,0,len);  
        } 
        //关闭输入流
        fis.close();
        //关闭输出流
        out.close();
    }
    
    
    /**
     * 创建文件
     * @param fileName  文件名称
     * @param filecontent   文件内容
     * @return  是否创建成功，成功则返回true
     */
    public static boolean createFile(String fileName,String filecontent){
        Boolean bool = false;
        filenameTemp = path+fileName+".txt";//文件路径+名称+文件类型
        File file = new File(filenameTemp);
        try {
            //如果文件不存在，则创建新的文件
            if(!file.exists()){
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is "+filenameTemp);
                //创建文件成功后，写入内容到文件里
                writeFileContent(filenameTemp, filecontent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return bool;
    }
    
    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,String newstr) throws IOException{
        Boolean bool = false;
        String filein = newstr+"\r\n";//新写入的行，换行
        String temp  = "";
        
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            
            //文件原有内容
            for(int i=0;(temp =br.readLine())!=null;i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);
            
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }
    
    /**
     * 删除文件
     * @param fileName 文件名称
     * @return
     */
    public static boolean delFile(String fileName){
        Boolean bool = false;
        filenameTemp = path+fileName+".txt";
        File file  = new File(filenameTemp);
        try {
            if(file.exists()){
                file.delete();
                bool = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return bool;
    }

}


package com.Servlet.Student;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.BeanDao.CcheckDao;
import com.BeanDao.StudentDao;
import com.BeanDao.WorkDao;
import com.JavaBean.Student;
import com.JavaBean.Work;

	/**
* @ClassName: sUploadWork2Servlet
* @Description: TODO(用来处理上传文件的Servlet)
* @author: 任孟凯
* @date: 2017-3-7 下午11:35:50
*
*/ 
@WebServlet("/sUploadWork2Servlet")
public class sUploadWork2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag = 0;
	int row = 0;
	String Sno = "";//学号
	String CLno = "";//班级号
	String Wno = "";//作业号
	String Wtitle = "";//作业主题
	String BeiZhu = "";//备注
	String Uptime = "";//当前时间
	CcheckDao chDao = new CcheckDao();
	String Canswer = "F:\\UploadWork";//答案即提交路径，上传文件的路径
	static String path = "F:\\UploadWork\\";//设置，备注内容存到文件 的路径
	//文件路径+名称
    private static String filenameTemp;
    /**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//解决中文乱码问题
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("text/html; charset=UTF-8");
		System.out.println("***这是上传作业处理页面sUploadWork2Servlet****");
		
		//获取学号
		HttpSession session = request.getSession(); 
		Sno = session.getAttribute("account").toString();
		System.out.println("sUploadWork2Servlet页面:学号="+Sno);
		//获取班级号
		StudentDao sDao = new StudentDao();
		Student stu = sDao.QueryBySno(Sno);
		CLno = stu.getCLno();
		/*//获取作业主题
		chDao = new CcheckDao();
		String Wtitle = request.getParameter("select").toString();
		System.out.println("sUploadWork2Servlet页面:主题="+Wtitle);
		//获取作业号
		WorkDao wDao = new WorkDao();
		Work w = wDao.QueryByWtitleCLno(Wtitle, CLno);
		Wno = w.getWno();
		System.out.println("sUploadWork2Servlet页面:作业号="+Wno);*/
		
		//获取当前时间 
    	SimpleDateFormat simpleDateFormat;  
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss"); 
        Date date = new Date(); 
        Uptime = simpleDateFormat.format(date); //当前提交时间 
        System.out.println("sUploadWork2Servlet页面:提交时间="+Uptime);
		//获取作业提交路径
        //String Canswer = "F:\\UploadWork";
        System.out.println("sUploadWork2Servlet页面:答案路径="+Canswer);
       /* //把数据插入到Ccheck表里面
        String sql = "insert into Ccheck(Sno,Wno,Chscore,Chremark,Chtime,Uptime,Canswer) values('"+Sno+"','"+Wno+"','0','','','"+Uptime+"','"+Canswer+"') ";
		//flag返回执行的行数
		row = chDao.UpdateData(sql);*/
		
		//************************************************************************************************
		//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        //String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		String savePath = "F:\\UploadWork";
		 File savePathFile = new File(savePath);
            if (!savePathFile.exists()) {
                //创建临时目录
            	savePathFile.mkdir();
            }
            
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        
        //消息提示
        flag = 1;
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024*10000);//设置缓冲区的大小为10000KB，如果不指定，那么缓冲区的大小默认是10MB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener(){
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                    /**
                     * 文件大小为：14608,当前已处理：4096
		                                文件大小为：14608,当前已处理：7367
		                                文件大小为：14608,当前已处理：11419
		                                文件大小为：14608,当前已处理：14608
                     */
                }
            });
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024*1024*1024);//1GB
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024*1024*1024*10);//10GB
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                    //获取作业主题
                    if( name.equals("select") ){
                    	Wtitle = value;
                    	System.out.println("作业主题=" + Wtitle);
                    }
                    //获取备注
                    if( name.equals("textarea") ){
                    	BeiZhu = value;
                    	System.out.println("备注=" + BeiZhu);
                    } 
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);//文件名称的绝对路径
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是："+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //得到文件保存的名称
                    String saveFilename = makeFileName(filename);
                    //得到文件的保存目录
                    String realSavePath = makePath(saveFilename, savePath);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    //item.delete();
                    flag = 1; //message = "文件上传成功！";
                }
            }
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            flag = 2;//message = "单个文件超出最大值！！！";
            request.setAttribute("flag", flag);
            request.setAttribute("row",row);
            request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            flag = 3;//message = "上传文件的总的大小超出限制的最大值！！！";
            request.setAttribute("flag", flag);
            request.setAttribute("row",row);
            request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
            return;
        }catch (Exception e) {
        	flag = 4;//message= "文件上传失败！";
            e.printStackTrace();
        }
        //*******************把备注的内容写到一个.txt文件当中*********************************
        createFile(Sno+"-"+Uptime+"-作业备注", BeiZhu);// createFile(文件名, 文件内容);
        //*********************************************************************************
        //如果作业主题不为空
        if( Wtitle!=null ){
        	//获取作业号
    		WorkDao wDao = new WorkDao();
    		Work w = wDao.QueryByWtitleCLno(Wtitle, CLno);
    		Wno = w.getWno();
    		System.out.println("sUploadWork2Servlet页面:作业号="+Wno);
    		//把数据插入到Ccheck表里面
            String sql = "insert into Ccheck(Sno,Wno,Chscore,Chremark,Chtime,Uptime,Canswer) values('"+Sno+"','"+Wno+"','0','','','"+Uptime+"','"+Canswer+"') ";
    		//flag返回执行的行数
    		row = chDao.UpdateData(sql);
    		System.out.println("sUploadWork2Servlet页面:row="+row);
        }
        //传递数据
        request.setAttribute("flag",flag);
        request.setAttribute("row",row);
        request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
    }
    
    /**
    * @Method: makeFileName
    * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
    * @Anthor:孤傲苍狼
    * @param filename 文件的原始名称
    * @return uuid+"_"+文件的原始名称
    */ 
    private String makeFileName(String filename){  //2.jpg
    	//得到上传文件的扩展名
        String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
    	// 当前时间 
    	SimpleDateFormat simpleDateFormat;  
        simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss"); 
        Date date = new Date(); 
        String str = simpleDateFormat.format(date); // 当前时间 
        
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        //return UUID.randomUUID().toString() + "_" + filename;
        return  Sno+"-"+Uptime+"."+fileExtName;//文件名字为 “学号-提交时间+文件扩展名”
    }
    
    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
    * @Method: makePath
    * @Description: 
    * @Anthor:孤傲苍狼
    *
    * @param filename 文件名，要根据文件名生成存储目录
    * @param savePath 文件存储路径
    * @return 新的存储目录
    */ 
    private String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        //构造新的保存目录
        //String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        String dir = savePath;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        //如果目录不存在
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	//request.getRequestDispatcher("/page/student/sUploadWork.jsp").forward(request, response);
        doGet(request, response);
    }
    
    //*******************************创建文件,把备注里面的内容写到一个.txt文件当中*********************************************************
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

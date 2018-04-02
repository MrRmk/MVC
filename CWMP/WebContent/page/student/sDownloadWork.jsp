<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.JavaBean.*" %>
<%@ page import = "com.BeanDao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学生界面</title>
	    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
	    <link type="text/css" href="/CWMP/css/tableStyle.css" rel="stylesheet">
	    <!-- <style type="text/css">
	    	table{
	    		width:700px;
	    		border:0;
	    		text-align: center;
	    	}
	    	table tr td{
	    		margin:0;
	    		background-color: #666;
	    	}
	    </style> -->
	    <script type="text/javascript">
			window.onload=function(){	
				Alert(<%=request.getAttribute("flag")%>);
				//定时器每秒调用一次fnDate()		
				setInterval(function(){		
				fnDate();		
				},1000);		
			}		
			//js 获取当前时间		
			function fnDate(){		
				var oDiv=document.getElementById("div1");		
				var date=new Date();		
				var year=date.getFullYear();//当前年份		
				var month=date.getMonth();//当前月份		
				var data=date.getDate();//天		
				var hours=date.getHours();//小时		
				var minute=date.getMinutes();//分		
				var second=date.getSeconds();//秒		
				var time=year+"-"+fnW((month+1))+"-"+fnW(data)+" "+fnW(hours)+":"+fnW(minute)+":"+fnW(second);
				oDiv.innerHTML=time;		
			}	
			//补位 当某个字段不是两位数时补0		
			function fnW(str){		
				var num;		
				str>=10?num=str:num="0"+str;		
				return num;		
			}
			//下载是否成功提示
			function Alert(f) {
				var flag = f;
				if( flag == 1 ){					
					alert("下载成功!");						
				}
				else if(flag != null){
					alert("下载失败!");
				}
			}
		</script>
	</head>
	<body class = "top" style="background: url(/CWMP/img/1.jpg);">
  		
  		<!-- 接收session数据 -->
  		<%
  			//获取在登录界面设置的session值，在jsp页面间传递值用
	  		String account = session.getAttribute("account").toString();//学号
  			String name = session.getAttribute("name").toString();
	  		String type = session.getAttribute("type").toString();
	  		
	  		/* //获取学号
			HttpSession session = request.getSession(); 
			String account = session.getAttribute("account").toString();
			System.out.println("sDownloadWork1Servlet页面:"+account); */
			//通过学号获取该学生的班级号
			StudentDao stu = new StudentDao();
			Student s = stu.QueryBySno(account);
			String CLno = s.getCLno().toString();
			System.out.println("sDownloadWork1Servlet页面CLno:"+CLno);
			//按班级号在作业表中查询到所有的作业记录，放到数组wList中
			WorkDao  wDao = new WorkDao();
			List<Work> list = (List<Work>) wDao.QueryByCLno(CLno);
  		%>
  		
		<!-- 头部 -->
		<div class = "top">
		
			<!-- 头部标题 -->
			<span style="font-size: 45px; font-family:sans-serif;margin-left: 35%">
				课程作业管理平台
			</span>
			<span style="margin-left:80%;">
				欢迎您: [<%=type%>]&nbsp;<%=name %>&nbsp;&nbsp;
				<div style="height: 28px; float: right;margin-right:22px">
					<a href = "/CWMP/login.jsp" >注销</a>&nbsp;&nbsp;
				</div>
				
			</span>
			<hr>
			<span style="font-size:15px; margin-left: 80%">当前时间: <span id="div1"></span></span>
		</div>
		
		<!-- 功能区 -->
		<div style="margin:0% 5% 0% 5%; ">
		
			<!-- 功能选择区 -->
			<div class = "function">
				<div class = "menu">
				<ul>
					<li>功能选项
						<ul>
							<li><a href = "/CWMP/studentServlet">查看作业</a></li>
							<li><a href = "/CWMP/page/student/sUploadWork.jsp">上传作业</a></li>
							<li><a href = "/CWMP/page/student/sDownloadWork.jsp">下载作业</a></li>
							<li><a href = "/CWMP/sCheckServlet">查看批阅情况</a></li>
						</ul>
					</li><br>
					<li>个人设置
						<ul>
							<li><a href = "/CWMP/page/student/sUpdatePassword.jsp">修改密码</a></li>
							<li><a href = "/CWMP/sQueryMessageServlet">查看信息</a></li>
						</ul>
					<li><br>
				</ul>
				</div>
			</div>
			
			<!-- 操作区 -->
			<div class = "operation">
				<div style="height: 28px;"><a href="/CWMP/studentServlet" >首 页</a></div>当前位置: 学生&gt;下载作业<br/><br/>
				<center>
				<table class = "tableSeting">
					<thead>教师发布的作业信息</thead>
					<tr >
						<td>作业号</td>
						<td>主题</td>
						<td>截止时间</td>
						<td>发布者</td>
						<td>操 &nbsp;作</td>
					</tr>
					<%
						//List<Work> list = (List<Work>)request.getAttribute("list");
						if( list != null ){
							for(int i=0; i<list.size(); i++){
								System.out.println("***sDownloadWork.jsp页面**作业表添加了"+(i+1)+"条数据********************");
						  		String Wno = list.get(i).getWno().toString();
						  		String Wcontent = list.get(i).getWcontent().toString();
						  		String Wtitle = list.get(i).getWtitle().toString();
						  		String Wdeadline = list.get(i).getWdeadline().toString();
						  		String Tno = list.get(i).getTno().toString();
						  		//获取教师名
						  		TeacherDao tDao = new TeacherDao();
						  		String Tname = tDao.QueryByTno(Tno).getTname().toString();
						  		
						  		String Cno = list.get(i).getCno().toString();
						  		//获取课程名
						  		CourseDao cDao = new CourseDao();
						  		String Cname = cDao.QueryByCno(Cno).getCname().toString();
						  		System.out.println("sDownloadWork.jsp页面"+Wno +Wtitle +Wdeadline +Tno+Cno+Cname);
					%>
							<tr>
								<td><%=Wno%></td>
								<td><%=Wtitle %></td>
								<td><%=Wdeadline %></td>
								<td><%=Tname%></td>
								<td><a href="/CWMP/sDownloadWork2Servlet?Wno=<%=Wno%>&Wcontent=<%=Wcontent%>&Wtitle=<%=Wtitle%>&Wdeadline=<%=Wdeadline%>&Tname=<%=Tname%>&Cno=<%=Cno%>&Cname=<%=Cname%>" >下载</a></td>
							</tr>		
					<%	
							}
						}
					%>
					
				</table>
				</center>
			</div>
			
		<!-- foot -->
		<div class = "foot">
			<center>
				<hr>
				<p >
					版权：南昌航空大学 软件学院 142011班&nbsp;邮编：330036 &nbsp;联系电话：13672205014
				</p><br>
			</center>
		</div>

    </div>
	
	</body>
</html>
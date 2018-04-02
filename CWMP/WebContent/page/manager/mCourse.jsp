<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.JavaBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 <title>管理员界面</title>
	    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
	    <link type="text/css" href="/CWMP/css/tableStyle.css" rel="stylesheet">
	    <style type="text/css">
	    
	    </style>
	    <script type="text/javascript">
			window.onload=function(){	
				Alert(<%=request.getAttribute("flag")%>);
				//定时器每秒调用一次fnDate()		
				setInterval(function(){		
				fnDate();		
				},1);		
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
			
			//发布是否成功提示
			function Alert(f){
				var flag = f;
				if(flag ==1){
					alert("删除成功!");
				}
				else if(flag != null){
					alert("删除失败!");
				}
			}
			
		</script>
	</head>
	<body class = "top" style="background: url(/CWMP/img/1.jpg);">
		
		<!-- 接收session数据 -->
  		<%
  			//获取设置的session值，在jsp页面间传递值用
	  		String account = session.getAttribute("account").toString();
  			String name = session.getAttribute("name").toString();
	  		String type = session.getAttribute("type").toString();
	  	%>
		<%
			System.out.println("***mCourse********************");
		%>	
		<!-- 头部 -->
		<div class = "top">
		
			<!-- 头部标题 -->
			<span style="font-size: 45px; font-family:sans-serif;margin-left: 35%">
				课程作业管理平台
			</span>
			<span style="margin-left: 80%;">
				欢迎您: [<%=type%>]&nbsp;<%=name %>&nbsp;&nbsp;
				<div style="height: 28px; float: right;margin-right:22px">
					<a href = "/CWMP/login.jsp" >注销</a>&nbsp;&nbsp;
				</div>
					
			</span>
			<hr>
			<span style="font-size:15px; margin-left: 80%">当前时间: <span id="div1"></span></span>
		</div>
		
		<!-- 功能区 -->
		<div class = "total" style="margin:0% 5% 0% 5%; height: 680px;" >
		
			<!-- 功能选择区 -->
			<div class = "function">
				<div class = "menu">
					<ul>
						<li><a href="mTeacherServlet">教师管理</a><br>
							<ul>
								<li><a href="/CWMP/page/manager/addTeacher.jsp">添	&nbsp;加</a></li>
								<li><a href="mTeacherServlet">删&nbsp;除</a></li>
								<li><a href="mTeacherServlet">修&nbsp;改</a></li>
							</ul>
						</li><br>
						<li><a href="mStudentServlet">班级管理</a>
							<ul>
								<li><a href="/CWMP/page/manager/addStudent.jsp">添	&nbsp;加</a></li>
								<li><a href="mStudentServlet">删&nbsp;除</a></li>
								<li><a href="mStudentServlet">修&nbsp;改</a></li>
							</ul>
						</li><br>
						<li><a href="mCourseServlet">课程管理</a>
							<ul>
								<li><a href="/CWMP/page/manager/addCourse.jsp">添	&nbsp;加</a></li>
								<li><a href="mCourseServlet">删&nbsp;除</a></li>
								<li><a href="mCourseServlet">修&nbsp;改</a></li>
							</ul>
						</li><br>
						<li><a href="/CWMP/mTCCLServlet">授课管理</a>
							<ul>
								<li><a href="/CWMP/page/manager/addTCCL.jsp">添	&nbsp;加</a></li>
								<li><a href="/CWMP/mTCCLServlet">删&nbsp;除</a></li>
								<!-- <li><a href="/CWMP/mTCCLServlet">修&nbsp;改</a></li> -->
							</ul>
						</li><br>
					</ul>
				</div>
			</div>
			
			<!-- 操作区 -->
			<div class = "operation">
				<div style="height: 28px;"><a href="/CWMP/page/manager/manager.jsp">主页</a></div>
				当前位置 : 管理员&gt;课程管理
				<div class = "showTable">
					<center>
						<table class = "tableSeting">
							<thead>课程管理</thead>
							<tr>
								<td>课程号</td>
								<td>课程名</td>
								<td>学分</td>
								<td>操作1</td>
								<td>操作2</td>
							</tr>
							<%	
						  		List<Course> list = (List<Course>)request.getAttribute("listCourse");
						  		System.out.println("***课程表添加数据前********************");
						  		//获取设置的list值，在jsp页面间传递值用
						  		if( list!=null ){
							  		for(int i=0; i<list.size(); i++){
							  		System.out.println("***课程表添加了"+(i+1)+"条数据********************");
							  		String Cno = list.get(i).getCno().toString();
							  		String Cname = list.get(i).getCname().toString();
							  		String Ccredit = list.get(i).getCcredit().toString();
							  		
							  		//*********超链接 传递值 之前用编码一次(en编码)*********
							  		System.out.println("mCourse.jsp页面"+Cno+Cname +Ccredit);
							  		Cno = java.net.URLEncoder.encode(Cno,"UTF-8");//解码
							  		Cname = java.net.URLEncoder.encode(Cname,"UTF-8");//解码
							  		Ccredit = java.net.URLEncoder.encode(Ccredit,"UTF-8");//解码
							  		System.out.println("mCourse.jsp页面"+Cno+Cname+Ccredit);
							%>
						  			<tr>
						  				<td><%=list.get(i).getCno() %></td>
						  				<td><%=list.get(i).getCname() %></td>
						  				<td><%=list.get(i).getCcredit() %></td>
						  				<td><a href="/CWMP/page/manager/updateCourse.jsp?Cno=<%=Cno%>&Cname=<%=Cname%>&Ccredit=<%=Ccredit%>">修改</a></td>
										<td><a href="mDeleteCourseServlet?Cno=<%=Cno%>">删除</a></td>
						  			</tr>
						  			
							<%		
							  		}
							  		System.out.println("***课程表添加数据后********************");
						  		}
						  	%>
							<%-- <%
								
								List<TCCL> listTCCL = (List<TCCL>)request.getAttribute("listTCCL");
								Course[] c = (Course[])request.getAttribute("c");
								Teacher[] t = (Teacher[])request.getAttribute("t");
								for(int i=0; i<listTCCL.size(); i++){
									for(int j=0; j<c.length; j++){
										for(int k=0; k<t.length; k++){									
											if( ((t[k].getTno()).equals(listTCCL.get(i).getTno())) ){ 
												if(  ((c[j].getCno()).equals(listTCCL.get(i).getCno()))  ){	
											
										System.out.println("***课程表添加了"+(i+1)+"条数据********************");
										String Tno = t[k].getTno().toString();
										String Tname = t[k].getTname().toString();
										String CLno = listTCCL.get(i).getCLno().toString();
								  		String Cno = c[j].getCno().toString();
								  		String Cname = c[j].getCname().toString();
								  		String Ccredit = c[j].getCcredit().toString();
								  		System.out.println("mCourse.jsp页面"+Tno+Tname+CLno+Cno+Cname +Ccredit);
							%>
										<tr>
											<td><%=Tname %></td>
											<td><%=listTCCL.get(i).getCLno() %></td>
											<td><%=Cno %></td>
											<td><%=Cname %></td>
											<td><%=Ccredit %></td>
											<td><a href="/CWMP/page/manager/updateCourse.jsp?Cno=<%=Cno%>&Cname=<%=Cname%>&Ccredit=<%=Ccredit%>">修改</a></td>
											<td><a href="mDeleteCourseServlet?Cno=<%=Cno%>&Tno=<%=Tno%>&CLno=<%=CLno%>" >删除</a></td>
										</tr>		
							<%				 }
										}
										}
									}
								}
							%> --%>
						</table>						
					</center>
				</div>
			</div>
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
		
	</body>
</html>
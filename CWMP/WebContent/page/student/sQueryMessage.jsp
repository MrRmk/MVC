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
	    <style type="text/css">
	    	/* table{
	    		width:700px;
	    		border-collapse:collapse;
	    	}
	    	 */
	    	table tr td {
    		border: 1px solid #A6A6A6;
    		height: 35px;
    		padding: 3px;
    	}
	    </style>
	    <script type="text/javascript">
			window.onload=function(){		
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
		</script>
	</head>
	<body class = "top" style="background: url(/CWMP/img/1.jpg);">
  		
  		<!-- 接收session数据 -->
  		<%
  			//获取在登录界面设置的session值，在jsp页面间传递值用
	  		String account = session.getAttribute("account").toString();
  			String name = session.getAttribute("name").toString();
	  		String type = session.getAttribute("type").toString();
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
				<div style="height: 28px;"><a href="/CWMP/studentServlet" >首 页</a></div>当前位置: 学生&gt;个人设置&gt;查看信息
				<center>
						<br/><br/><br/><br/>
						<%-- <table class = "tableSeting"  style="text-align:left; " >
							<thead>我的个人信息</thead>
							<%
								Student s = (Student)request.getAttribute("student");
								System.out.println("sQueryMessage.jsp页面"+s.getSno() +s.getSname() +s.getSsex() +s.getSpassword() +s.getSdept() +s.getCLno() );
								if( s!=null ){
							%>
							<tr>
								<td>学 号</td>
								<td><%=s.getSno()%></td>
							</tr>
							<tr>
								<td>姓 名</td>
								<td><%=s.getSname() %></td>
							</tr>
							<tr>
								<td>性 别</td>
								<td><%=s.getSsex() %></td>
							</tr>
							<tr>
								<td>密 码</td>
								<td><%=s.getSpassword()%></td>
							</tr>
							<tr>
								<td>学 院</td>
								<td><%=s.getSdept()%></td>
							</tr>
							<tr> 
								<td>班 级</td>
								<td><%=s.getCLno()%></td>
							</tr>
							<%
								}
							%>	
						</table> --%>
						<%-- <div style="margin-top:50px; margin-left:35%; float:left; border:0px solid red; text-align:right; font-size:20px;">
							学 号&nbsp;:<br>
							姓 名 :<br>
							性 别 :<br>
							密 码 :<br>
							学 院 :<br>
							班 级 :<br>
						</div>
						<div style="margin-top:50px; margin-left:5px; float:left; border:0px solid red; text-align:left; font-size:20px">
							<%
								Student s = (Student)request.getAttribute("student");
								System.out.println("sQueryMessage.jsp页面"+s.getSno() +s.getSname() +s.getSsex() +s.getSpassword() +s.getSdept() +s.getCLno() );
								if( s!=null ){
							%>
									<%=s.getSno()%><br>
									<%=s.getSname() %><br>
									<%=s.getSsex() %><br>
									<%=s.getSpassword()%><br>
									<%=s.getSdept()%><br>
									<%=s.getCLno()%><br>
							<%
								}
							%>	
						</div> --%>
						
						<table class = "tableSeting"  >
							<thead>我的个人信息</thead>
							<tr>
								<td>学号</td>
								<td>姓名</td>
								<td>性别</td>
								<td>密码</td>
								<td>学院</td>
								<td>班级</td>
							</tr>
							<%
								Student s = (Student)request.getAttribute("student");
								System.out.println("sQueryMessage.jsp页面"+s.getSno() +s.getSname() +s.getSsex() +s.getSpassword() +s.getSdept() +s.getCLno() );
								if( s!=null ){
							%>
								<tr>
									<td><%=s.getSno()%></td>
									<td><%=s.getSname() %></td>
									<td><%=s.getSsex() %></td>
									<td><%=s.getSpassword()%></td>
									<td><%=s.getSdept()%></td>
									<td><%=s.getCLno()%></td>
								</tr>	
							<%
								}
							%>	
						</table>
						
						
					</center>
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
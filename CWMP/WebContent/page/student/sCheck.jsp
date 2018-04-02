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
	    	table tr td{
	    		margin:0;  		
	    		border:1px solid black;
	    	} */
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
								<li><a href = "/CWMP/page/student/sUploadPhoto.jsp">上传头像</a></li>
							</ul>
						<li><br>
					</ul>
				</div>
			</div>
			
			<!-- 操作区 -->
			<div class = "operation">
				<div style="height: 28px;"><a href="/CWMP/studentServlet" >首 页</a></div>当前位置: 学生&gt;查看作业批改情况
				<center>
						<br/>
						<table class = "tableSeting"  >
							<thead>查看作业批阅表</thead>
							<tr>
								<td>作业号</td>
								<td>主题</td>
								<td>提交时间</td>
								<td>发布人(教师)</td>
								<td>截止时间</td>
								<td>评语</td>
								<td>得分</td>
								<td>是&nbsp;否&nbsp; &nbsp;批&nbsp;阅</td>
							</tr>
							
							<%
								List<Ccheck> list = (List<Ccheck>)request.getAttribute("list");
								for(int i=0; i<list.size(); i++){
									System.out.println("***作业批改表添加了"+(i+1)+"条数据********************");
							  		String Wno = list.get(i).getWno().toString();//作业号
							  		//获取作业表中的 主题、截止时间
							  		WorkDao wDao = new WorkDao();
							  		String Wtitle = wDao.QueryByWno(Wno).getWtitle().toString();//主题
							  		String Uptime = list.get(i).getUptime().toString();//提交时间 
							  		String Wdeadline = wDao.QueryByWno(Wno).getWdeadline().toString();//截止时间
							  		String Tno = wDao.QueryByWno(Wno).getTno().toString();//教师号
							  		//获取教师名
							  		TeacherDao tDao = new TeacherDao();
							  		String Tname = tDao.QueryByTno(Tno).getTname().toString();//教师名
							  		String remark = list.get(i).getChremark().toString();//评语
							  		int score = list.get(i).getChscore();//得分
							  		String ck = "";
							  		System.out.println("sCheck.jsp页面"+Wno +Wtitle +Wdeadline +Tno +Tname +remark +score);
							%>
								<tr>
									<td><%=Wno%></td>
									<td><%=Wtitle %></td>
									<td><%=Uptime %></td>
									<td><%=Tname%></td>
									<td><%=Wdeadline %></td>
									<td><%=remark%></td>
									<td><%=score%></td>
									<%
										if( score==0 ){
											ck = "未批阅"; 
									%>
											<td><sapn style="color:#CD2626;"><%=ck %></sapn></td>
									<%
										}else{
											ck = "已批阅";
									%>
											<td><%=ck %></td>
									<%
										}
									%>
									
								</tr>		
							<%	
								}
							%>
							
							<!-- <tr>
								<td>01</td>
								<td>1</td>
								<td>2017/2/18</td>
								<td>舒坚</a></td>
								<td>中等(B)</td>
								<td>86</td>
								<td><a href="#">口 已批阅</a></td>
							</tr>
							<tr>
								<td>02</td>
								<td>1</td>
								<td>2017/3/1</td>
								<td>张恒峰</td>
								<td>优秀(A)</td>
								<td>95</td>
								<td><a href="#">口 未批阅</a></td>
							</tr> -->
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
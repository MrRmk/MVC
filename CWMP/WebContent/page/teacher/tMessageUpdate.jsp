<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.JavaBean.*" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教师界面</title>
    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
    <link type="text/css" href="/CWMP/css/tableStyle.css" rel="stylesheet">
    <style type="text/css">
    	select {
  	  	color:white;
		background: transparent;
		margin-top: 5%;
		font-size: 15px;
		}
		input{
			color:white;
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
	
	//修改是否成功提示
	function Alert(f) {
		var flag = f;
		if(flag ==1){
			alert("修 改 成 功!");
		}
		else if(flag != null){
			alert("修 改 失 败!");
		}
	}
	window.onload = function(){
		Alert(<%=request.getAttribute("flag")%>);
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
	  		
	  		//接收teacher对象
	  		Teacher teacher = (Teacher)request.getAttribute("teacher");
  		%>
		<!-- 头部 -->
		<div class = "top">
		
			<!-- 头部标题 -->
			<span style="font-size: 45px; font-family:sans-serif;margin-left: 35%">
				课程作业管理平台
			</span>
			<span style="margin-left:80%;">
				欢迎您: [<%=type%>]&nbsp;<%=name %>&nbsp;&nbsp;
				<div style="height: 28px; float: right;margin-right:2px">
					<a href = "login.jsp" >注销</a>&nbsp;&nbsp;
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
					<li><a href="/CWMP/teacherServlet">作业管理</a>
						<ul>
							<li><a href="/CWMP/tPublishWorkServlet">发布作业</a></li>
							<li><a href="/CWMP/tCheckWorkServlet">批改作业</a></li>
						</ul>
					</li><br>
					<li><a href="/CWMP/tStatisticsStudentServlet">提交统计</a>
						<ul>
							<li><a href=" /CWMP/tStatisticsStudentServlet">学生个人</a></li>
							<li><a href=" /CWMP/tStatisticsClassServlet">班 &nbsp;&nbsp;级</a></li>
						</ul>
					</li><br>
					<li><a href=" #">个人设置</a>
						<ul>
							<li><a href=" /CWMP/tPublishNoticeServlet">发布公告</a></li>
							<li><a href=" /CWMP/tMessageUpdateServlet">信息修改</a></li>
						</ul>
					</li><br>
				</ul>
			</div>
			</div>
			<!-- 操作区 -->
			<div class = "operation">
			<div style="height: 28px;"><a href="/CWMP/teacherServlet" >首 页</a></div>当前位置: 教师&gt;个人设置&gt;信息修改
				<center>
				<form action="/CWMP/tMessageUpdateServlet" method = "post" >
				<br><br>
				教 师 号 : <input name = "Tno"  type = "text" value = "<%=teacher.getTno() %>" size= "10" readonly = "true" class = "input_text" ><br><br>
				姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名 : <input name = "Tname"  type = "text" value = "<%=teacher.getTname() %>" size= "10" readonly = "true" class = "input_text" ><br><br>
				性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别 : <input name = "Tsex" type = "text" value = "<%=teacher.getTsex() %>" size= "10" readonly = "true"  class = "input_text"><br><br>
				办 公 室 : <input name = "Toffice" type = "text" value = "<%=teacher.getToffice() %>" size= "10" class = "input_text"><br><br>
				密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码 : <input name = "Tpassword" type = "text" value = "<%=teacher.getTpassword() %>" size= "10" class = "input_text"><br><br>
				系&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别 : <input name = "Tdept" type = "text" value = "<%=teacher.getTdept() %>" size= "10" class = "input_text"><br><br>
				<%-- <table class = "tableSeting" style = "margin-top: 10%;">
							<tr>
								<td>序 号</td>
								<td> 教 师 号</td>
								<td>姓 名</td>
								<td>性 别</td>
								<td>办 公 室</td>
								<td>密 码</td>
								<td>系 别</td>
							</tr>
							<tr>
								<td><input type = "text" value = " " size= "10" class = "input_text"></td>
								<td><input name = "Tno"  type = "text" value = "<%=teacher.getTno() %>" size= "10" readonly = "true" class = "input_text" ></td>
								<td><input name = "Tname"  type = "text" value = "<%=teacher.getTname() %>" size= "10" readonly = "true" class = "input_text" ></td>
								<td><input name = "Tsex" type = "text" value = "<%=teacher.getTsex() %>" size= "10" readonly = "true"  class = "input_text"></td>
								<td><input name = "Toffice" type = "text" value = "<%=teacher.getToffice() %>" size= "10" class = "input_text"></td>
								<td><input name = "Tpassword" type = "text" value = "<%=teacher.getTpassword() %>" size= "10" class = "input_text"></td>
								<td><input name = "Tdept" type = "text" value = "<%=teacher.getTdept() %>" size= "10" class = "input_text"></td>
							</tr>
						</table> --%><input type = "submit" value = "修  改" class = "button">
						
				
				</form>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.JavaBean.*" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教师界面</title>
	<link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
    <style type="text/css">
    	.detail{
    		margin-top:7%;
    		float: left;
    		
    	}
    	.content{
    		
    		float:left;
    		margin-top: 5%;
    		margin-left:  5%;
    	}
    	select {
  	  	color:white;
		background: transparent;
		margin-top: 5%;
		font-size: 15px;
		width: 100px;
		height:35px;
		text-align: center;
		}
    </style>
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
	//发布是否成功提示
	function Alert(f) {
		var flag = f;
		if(flag ==1){
			alert("发 布 成 功!");
		}
		else if(flag != null){
			alert("发 布 失 败!");
		}
	}
	<%-- window.onload = function(){
		Alert(<%=request.getAttribute("flag")%>);
	} --%>
    </script>
  </head>
  <body class = "top" style="background: url(/CWMP/img/1.jpg);">
  		<!-- 接收session数据 -->
  		<%
  			//获取在登录界面设置的session值，在jsp页面间传递值用
	  		String account = session.getAttribute("account").toString();
  			String name = session.getAttribute("name").toString();
	  		String type = session.getAttribute("type").toString();
	  		System.out.print(request.getAttribute("flag"));
	  		
	  		//获取该教师对应的授课表集合
	  		 List<TCCL> listTCCL = (List)request.getAttribute("listTCCL");
	  		//获取授课表对象数组
	  		TCCL[] arrayTCCL = new TCCL[listTCCL.size()];
	  		for(int i=0; i<listTCCL.size(); i++){
	  			arrayTCCL[i] = listTCCL.get(i);
	  		} 
	  		
	  	//获取该教师对应授课班级的课程对象数组
	  		List<Course> listCourse = (List)request.getAttribute("listCourse");
	  		
	  	for(int i=0; i<listCourse.size();i++){
	  		System.out.println("jsp:"+listCourse.get(i).getCname());
	  	}
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
					<a href = "/CWMP/login.jsp" >注销</a>&nbsp;&nbsp;
				</div>
				
			</span>
			<hr>
			<span style="font-size:15px; margin-left: 80%">当前时间: <span id="div1"></span></span>
		</div>
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
			<div style="height: 28px;"><a href="/CWMP/teacherServlet" >首 页</a></div>当前位置: 教师&gt;作业管理&gt;发布作业

					<form action="/CWMP/tPublishWorkServlet" method="post">
						<div class = "detail">
							 <input name = "Wno" type = "text" size = "15" class = "input_text" placeholder="请输入作业号!"><br><br>
							<input name = "Wtitle" type = "text" size = "20" class = "input_text" placeholder = "请输入主题!"><br><br>
							
							<select name = "CLno">
								<option style="background-color: #AAAAAA">==  班  级  ==</option>
						<%
							for(int i=0; i<arrayTCCL.length; i++){
						%>
								<option style="background-color: #AAAAAA"><%=arrayTCCL[i].getCLno() %></option>
						<% 
							}
						%>
						</select>
							
							<select name = "Cname"><option style="background-color: #AAAAAA">==  课  程  ==</option>
						<%
							for(int i=0; i<listCourse.size(); i++){
						%>	
								<option style="background-color: #AAAAAA"><%=listCourse.get(i).getCname() %></option>
						<% 
							}
						%> 
						</select>
							
							<br><br>
							截止时间 :<br>
							<input name = "Wdeadline" type = "date" size = "30" class = "input_text" ><br><br><br>

						</div>
						<div class = "content">
							内 容:<br><textarea name = "Wcontent" rows="15" cols="70" style = "background-color:#EBEBEB;" ></textarea><br><br>
							<input name = "Tno" type = "hidden" value = "<%=account%>"><!-- 传递隐藏信息 用户账号 -->
							<input type = "submit" value = "发 布" class = "button">
							<input type = "reset" value = "重 置" class = "button" style = "margin-left: 50px;">
						</div>
					</form>
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
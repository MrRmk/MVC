<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head> 
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员界面</title>
    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
    <link type="text/css" href="/CWMP/css/tableStyle.css" rel="stylesheet"> 
    <style type="text/css">
    .box{
    	padding: 10px;
    }
    .box ul li{
    	margin:5px;
    	list-style: none;
    	display: block;
    	float: left;
    }
    	.add {
		    border: 2px dashed #999;
		    width:400px;
		    height: 202px;
		    cursor: pointer;
		     color: white; 
		    font-size: 30px;
		    text-align: center;
		    /* margin-top:40px; */
		    }
		    .a1{
		    display:block;
		    margin-top:65px; 
		    	text-align:center;
		    	font-size: 50px;
		    	color:white;
		    }
		    .a1:hover{
		    	color:#D1EEEE;
		    	font-size: 50px;
		    }
    </style>
    <script type="text/javascript">
		window.onload=function(){		
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
		<div class = "total" style="margin:0% 5% 0% 5%; height: 680px;" >
		
			<!-- 功能选择区 -->
			<div class = "function">
				<div class = "menu">
					<ul>
						<li><a href="/CWMP/mTeacherServlet">教师管理</a><br>
							<ul>
								<li><a href="/CWMP/page/manager/addTeacher.jsp">添	&nbsp;加</a></li>
								<li><a href="/CWMP/mTeacherServlet">删&nbsp;除</a></li>
								<li><a href="/CWMP/mTeacherServlet">修&nbsp;改</a></li>
							</ul>
						</li><br>
						<li><a href="/CWMP/mStudentServlet">班级管理</a>
							<ul>
								<li><a href="/CWMP/page/manager/addStudent.jsp">添	&nbsp;加</a></li>
								<li><a href="/CWMP/mStudentServlet">删&nbsp;除</a></li>
								<li><a href="/CWMP/mStudentServlet">修&nbsp;改</a></li>
							</ul>
						</li><br>
						<li><a href="/CWMP/mCourseServlet">课程管理</a>
							<ul>
								<li><a href="/CWMP/page/manager/addCourse.jsp">添	&nbsp;加</a></li>
								<li><a href="/CWMP/mCourseServlet">删&nbsp;除</a></li>
								<li><a href="/CWMP/mCourseServlet">修&nbsp;改</a></li>
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
				<!-- <a href="/CWMP/page/manager/manager.jsp">主页</a> -->
				当前位置 : 管理员主页
				<div class = "box">
				<ul>
				<li>
						<div class = "add" id = ""><a class = "a1" href="/CWMP/mTeacherServlet">教师管理</a></div>
				</li>
				<li>
						<div class = "add" id = ""><a class = "a1" href="/CWMP/mStudentServlet">班级管理</a></div>
				</li>
				<li>
						<div class = "add" id = ""><a class = "a1" href="/CWMP/mCourseServlet">课程管理</a></div>
				</li>
				<li>
						<div class = "add" id = ""><a class = "a1" href="/CWMP/mTCCLServlet">授课管理</a></div>
				</li>
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
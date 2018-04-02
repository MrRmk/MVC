<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改教师界面</title>
	    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
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
			function Alert(f) {
				var flag = f;
				if(flag ==1){
					alert("修改成功!");
				}
				else if(flag != null){
					alert("修改失败!");
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
	  		
	  		//解决中文乱码
			//request.setCharacterEncoding("UTF-8");
	  		
			String Cno = request.getParameter("Cno");
			String Cname = request.getParameter("Cname");
			String Ccredit = request.getParameter("Ccredit");
			
			//解决中文乱码
			Cno = new String(Cno .getBytes("iso8859-1"),"utf-8"); 
			Cname = new String(Cname .getBytes("iso8859-1"),"utf-8"); 
			Ccredit = new String(Ccredit .getBytes("iso8859-1"),"utf-8"); 
	  		
			System.out.println("***updateCurse页面1***"+Cno+Cname+Ccredit);
	  		
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
				<div style="height: 28px;"><a href="/CWMP/page/manager/manager.jsp">主页</a></div>
				当前位置 : 管理员&gt;课程管理&gt;修改
				<center>
					<form action="/CWMP/mUpdateCourseServlet" method="post" >
						课&nbsp;&nbsp;程&nbsp;&nbsp;号: <input type="text" name="Cno" class="input_text" value="<%=Cno%>"  readonly size="20" /><br><br>
						课&nbsp;&nbsp;程&nbsp;&nbsp;名: <input type="text" name="Cname" class="input_text" value="<%=Cname%>"  size = "20" maxlength="20" placeholder="如：软件工程" /><br><br>
						学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分&nbsp;&nbsp;: <input type="text" name="Ccredit" class="input_text" value="<%=Ccredit%>"  size="20" maxlength="6" placeholder="请输入0到10之间的数字！" /><br><br><br>
						<input type = "submit" value = "修 改" class= "button">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type = "reset" value = "重 置" class= "button">
					</form>
						<%
							System.out.println("updateCourse.jsp页面2***"+Cno+Cname +Ccredit);
						%>
					
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
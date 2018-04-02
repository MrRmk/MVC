<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学生界面</title>
	    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
	    <style type="text/css">
	    	.form1{
	    		border: 2px dashed #999;
			    width:800px;
			    height: 190px;
			    cursor: pointer;
			    color: white;
			    font-size: 20px;
			    text-align: center; 
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
							<!-- <li><a href = "/CWMP/page/student/sUploadPhoto.jsp">上传头像</a></li> -->
							<li><a href = "/CWMP/sQueryMessageServlet">查看信息</a></li>
						</ul>
					<li><br>
				</ul>
				</div>
			</div>
			
			<!-- 操作区 -->
			<div class = "operation">
				<div style="height: 28px;"><a href="/CWMP/studentServlet" >首 页</a></div>当前位置: 学生&gt;个人设置&gt;修改头像
				<br/><br/>
				<center>
					<form class="form1" >
						选择要替换的图片：<input type="file" id="fileUpload" accept="image/png,image/jpg,image/gif" name="fileUpload" /><br/><br/>
						<input type="submit" name="submit" class="button" id="submit" value="上传"/>
					</form>
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
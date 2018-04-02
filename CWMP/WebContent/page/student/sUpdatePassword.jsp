<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学生界面</title>
    	<link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
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
			function Alert(f){
				var flag = f;
				if( flag == 1 ){					
					alert("密码修改成功!");						
				}else if( flag == 2 ){
					alert("两次新密码不一致!****修改失败!");
				}else if( flag == 3 ){
					alert("新密码 与 原密码相同!****修改失败!");
				}else if( flag == 4 ){
					alert("输入的'原密码'不正确!****修改失败!");
				}
			}
			//重置
			function Reset(){
				//调用form表单的reset()函数
				document.getElementById("form").reset();alert("没错");
				//document.getElementById("form").reset();
				//获取获取(提示)错误显示信息对象
				//var error = document.getElementById("tishi");
				//重置错误信息对象内容
				error.innerHTML = "";
			}
		</script>
		<script language="JavaScript">
			function validate() {
				var pwd1 = document.getElementById("password1").value;
                var pwd2 = document.getElementById("password2").value;

				//对比两次输入的密码
                if(pwd1 == pwd2) {
                	document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
                  	document.getElementById("submit").disabled = false;
              	}
              	else {
                  	document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
                	document.getElementById("submit").disabled = true;
              	}
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
				<div style="height: 28px;"><a href="/CWMP/studentServlet" >首 页</a></div>当前位置: 学生&gt;个人设置&gt;修改密码<br><br>
					<div class="st">
							<center>
							<form id="form" action="/CWMP/sUpdatePasswordServlet" method="post">
									原 &nbsp;&nbsp;密码:&nbsp;<input type="text" name="password" class="input_text" id="password" size = "20" maxlength="6" placeholder="请输入原密码！" /><br/><br/>
									新 &nbsp;&nbsp;密码:&nbsp;<input type="text" name="password1" class="input_text" id="password1" size = "20" maxlength="6" placeholder="请输入6位字母,数字！" /><br/><br/>
									确认密码: <input  type="text" name="password2" class="input_text" id="password2" size = "20" maxlength="6" placeholder="与新密码保持一致！" onkeyup="validate()" /><br/><br/><br/>	
									<span class="tishi" id="tishi"></span><br/>
									<input type="submit" class="button" name="submit" id="submit" />&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" class="button" name="reset" id="reset" value="重置"  />		
							</form>
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
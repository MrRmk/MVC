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
    <script src="/CWMP/js/select.js" type="text/javascript"></script> 
    <style type="text/css">
    	select {
  	  	color:white;
		background: transparent;
		margin-top: 5%;
		font-size: 15px;
		width: 100px;
		height:35px;
		text-align: center;
		}
	.showWork{
    		background-color: #EBEBEB;
    		color:black;
    		width: 40%;
    		height:500px;
    		margin-top:5%;
    		float: left;
    	}
    	
    </style>
    <script type="text/javascript">
    window.onload=function(){		
    	Alert(<%=request.getAttribute("flag")%>);
    	Alert1(<%=request.getAttribute("flag1")%>);
    	Alert2(<%=request.getAttribute("flag2")%>);
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
	
	//用JQuery实现二级联动菜单----作业和班级
	$(document).ready(function(){ 
$("#work").change(function(){ 
$("#work option").each(function(i,o){ 
if($(this).attr("selected")) 
{ 
$(".class").hide(); 
$(".class").eq(i).show(); 
} 
}); 
}); 
$("#work").change(); 
});
	//用JQuery实现二级联动菜单----班级和学生
/* 	$(document).ready(function(){ 
$(".class").change(function(){ 
$(".class option").each(function(i,o){ 
if($(this).attr("selected")) 
{ 
$(".student").hide(); 
$(".student").eq(i).show(); 
} 
}); 
}); 
$(".class").change(); 
}); */

//要批改的学生作业未提交提示
function Alert(f) {
	var flag = f;
	if(flag ==1){
		alert("这位同学的作业没找到, 嗯. . . .\n   可能他/她还在奋笔疾书中!");
	}
	/* else if(flag != null){
		alert("发 布 失 败!");
	} */
}

//要批改的学生作业内容是否成功写入数据库提示
function Alert1(f) {
	var flag1 = f;
	if(flag1 == 1){
		alert("这个完了,继续下一个吧!");
	}
	 else if(flag1 != null){
		alert("改不了,我也不知道怎么了!");
	} 
}
function Alert2(f) {
	var flag2 = f;
	if(flag2 == 5){
		alert("您要下载的资源已被删除！！");
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
	  		
	  		//获取该教师对应的作业表集合
	  		List<Work> listWork = (List)request.getAttribute("listWork");
	  		
	  		//获取班级对应的学生的集合 集合
	  		List<List<Student>> listListStudent = (List)request.getAttribute("listListStudent");
	  		
	  		//获取从文件读取的作业内容
	  		String work = "";
	  		if(request.getAttribute("work") != null){
	  			work = request.getAttribute("work").toString();
	  		}
	  		String Wno = null;
	  		String CLno = null;
	  		String Sno = "";
	  		String Sname = "";
	  		if(request.getAttribute("Wno")!=null){
	  			Wno = (String)request.getAttribute("Wno");
	  			CLno = (String)request.getAttribute("CLno");
	  			Sno = (String)request.getAttribute("Sno");
	  			Sname = (String)request.getAttribute("Sname");
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
			<div style="height: 28px;"><a href="/CWMP/teacherServlet" >首 页</a></div>当前位置: 教师&gt;批改作业<br><hr>
				<div class = "showWork">
					<div style = "text-align:center;"><%=Sno %> <%=Sname %></div><br><%=work %><br>
					<!-- <input type = "button" value = "上一页" class = "button" style="margin-top: 99%;margin-left: 30%">&nbsp;&nbsp;
					<input type = "button" value = "下一页" class = "button" style="margin-top: 99%;margin-left: 10%">&nbsp;&nbsp; -->
				</div><br>
				<div style = "margin-top:20px;margin-left: 20px;float:left;">
				
				<form action="tCheckWorkServlet" method = "post">
				
				<!-- 下拉联级显示 -->
				
					<!-- 作业下拉列表 -->
				<select id="work" name = "Wno">
					<option style="background-color: #AAAAAA">== 作 业 ==</option>
					<%
						for(int i=0; i<listWork.size(); i++){
							%>
								<option style="background-color: #AAAAAA"><%=listWork.get(i).getWno()%></option>
							<%
						}
					%> 
				</select>
					
					<!-- 班级下拉列表 -->
					<select class = "class"><option style="background-color: #AAAAAA">== 班 级 ==</option></select>
					
					<%
						for(int i=0; i<listWork.size(); i++){
						%>
							<select class = "class" name = "CLno">
								<option style="background-color: #AAAAAA"><%=listWork.get(i).getCLno()%></option>
							</select>
						<%
						}
					%>
					
					
					
					<!-- 学生下拉列表 -->
					<!-- <select class = "student"><option style="background-color: #AAAAAA">== 学 生 ==</option></select> -->
					<select class = "student" name = "Sname">
					<%
						for(int i=0; i<listListStudent.size(); i++){
							%>
								<optgroup label="<%=listListStudent.get(i).get(0).getCLno() %>" style="background-color: #AAAAAA">
							<%
							for(int j=0; j<listListStudent.get(i).size(); j++){
							%>
								<option style="background-color: #AAAAAA"><%=listListStudent.get(i).get(j).getSname()%></option>
							<%
							}
							%>
								</optgroup>
							<%
						}
					%>
					</select>
				&nbsp;
				
				<%//提交隐藏数据,用于查询 %>
				<input type = "hidden" name = "Wnoa" value = "<%=Wno%>"/>
				<input type = "hidden" name = "CLnoa" value = "<%=CLno%>"/>
				<input type = "hidden" name = "Snoa" value = "<%=Sno%>"/>
				<input type = "submit" value = "开始批阅" class = "button" style = "width:70px;"/><br><br>
				<a class = "button" href = "tCheckWorkServlet1?Wno=<%=Wno%>&CLno=<%=CLno%>&Sno=<%=Sno%>" style = "font-size: 15px;padding:4px;clear:both;">下载作业</a><br><br>
				</form>
				<form action="tCheckWorkServlet1" method = "post">
						<%//提交隐藏数据,用于查询 %>
					<input type = "hidden" name = "Wnoa" value = "<%=Wno%>"/>
					<input type = "hidden" name = "CLnoa" value = "<%=CLno%>"/>
					<input type = "hidden" name = "Snoa" value = "<%=Sno%>"/>
				
					得 分: <input name = "Chscore"type = "text" size = "5" class = "input_text"><br><br>
					评语:<br><textarea name = "Chremark"  rows="8" cols="55" style="background-color: #EBEBEB;"></textarea><br><br><br>
					
					<input type = "submit" value = "完 成" class = "button" style="margin-top: 5%;margin-left:30%">&nbsp;&nbsp;
					<input type = "button" value = "重 填" class = "button" style="margin-top: 5%;margin-left: 10%">
					
				</form>
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

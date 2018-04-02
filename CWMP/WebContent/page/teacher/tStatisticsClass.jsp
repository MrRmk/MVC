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
    <link type="text/css" href="/CWMP/css/tableStyle.css" rel = "stylesheet">
    <script type="text/javascript" src = "/CWMP/js/jquery-3.1.1.js"></script> 
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
	
	//用JQuery实现二级联动菜单
	$(document).ready(function(){ 
$("#class").change(function(){ 
$("#class option").each(function(i,o){ 
if($(this).attr("selected")) 
{ 
$(".course").hide(); 
$(".course").eq(i).show(); 
} 
}); 
}); 
$("#class").change(); 
});
	//控制下拉框的值不变...................不行
	if(<%=request.getAttribute("CLno")%> !=null){
	$(document).ready(function(){
		$("#class").val("<%=request.getAttribute("CLno")%>");
	});
	$(document).ready(function(){
		$("#course").val("<%=request.getAttribute("Cname")%>");
	});
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
	  	//获取该教师对应的授课表集合
	  		 List<TCCL> listTCCL = (List)request.getAttribute("listTCCL");
	  		//获取授课表对象数组
	  		TCCL[] arrayTCCL = new TCCL[listTCCL.size()];
	  		for(int i=0; i<listTCCL.size(); i++){
	  			arrayTCCL[i] = listTCCL.get(i);
	  		} 
	  		
	  	//获取该教师对应授课班级的课程对象数组
	  		Course[] arrayCourse = new Course[listTCCL.size()];
	  		for(int i=0; i<listTCCL.size(); i++){
  				arrayCourse[i] = (Course)request.getAttribute("Course"+i);//将 学生对象集合存入数组中
  			}
	  		/* for(int i=0; i<arrayCourse.length;i++){
		  		arrayCourse[i] = ((Course[])request.getAttribute("Course"))[i];
		  	} */
	  		
	  	for(int i=0; i<arrayCourse.length;i++){
	  		System.out.println("jsp:"+arrayCourse[i].getCname());
	  	}
		  	List<Student> listStudent = null;
		  	List<Work> listWork = null;
		  	List<List<Ccheck>> listListCcheck = null;
		  	String CLno = null;
		  	String Cname = null;
		  	if(request.getParameter("CLno") != null){
		  		CLno = request.getAttribute("CLno").toString();
		  		Cname = request.getAttribute("Cname").toString();
		  	//获得要显示的学生信息
		  		listStudent = (List)request.getAttribute("listStudent");
		  		for(int i=0; i<listStudent.size(); i++){
		  			System.out.println("jsp-listStudent:##########"+listStudent.get(i).getSno()+listStudent.get(i).getSname());
		  		}
		  	//获得要显示的作业信息
		  		listWork = (List)request.getAttribute("listResultWork");
		  		for(int i=0; i<listWork.size(); i++){
		  			System.out.println("jsp-listWork:##########"+listWork.get(i).getWno()+listWork.get(i).getWcontent()+listWork.get(i).getCno());
		  		}
		  	//获得学生对应的批改表信息
		  		listListCcheck = (List)request.getAttribute("listListResultCcheck");
		  		for(int i=0; i<listListCcheck.size(); i++){
		  			for(int j=0; j<listListCcheck.get(i).size(); j++){
		  			System.out.println("jsp-listListCcheck:##########"+listListCcheck.get(i).get(j).getSno()+"  "
		  			+listListCcheck.get(i).get(j).getWno()+"  "+listListCcheck.get(i).get(j).getChscore());
		  			}
		  		}
		  			
		  	}System.out.print("开始显示!!!");
		  	
		  	//分页变量
		  	int pageSize = 5;
		  	int rowSize;
		  	int pageNow;
		  	
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
			<div style="height: 28px;"><a href="/CWMP/teacherServlet" >首 页</a></div>当前位置: 教师&gt;提交统计&gt;班级
				<center>
				<form action="/CWMP/tStatisticsClassServlet" method = "post">
				<select id = "class" name="CLno"> 
					<option style="background-color: #AAAAAA">==  班  级  ==</option>
					<%
							for(int i=0; i<arrayTCCL.length; i++){
					%>
								<option style="background-color: #AAAAAA" "><%=arrayTCCL[i].getCLno() %></option>
					<% 
							}
					%>
				</select>&nbsp;&nbsp;
				<select class = "course" ><option>==  课  程  ==</option></select>
				
					<%
							for(int i=0; i<arrayCourse.length; i++){
					%>	
								<select class = "course" id = "course" name = "Cname">
								<option style="background-color: #AAAAAA" value = "<%=arrayCourse[i].getCname() %>"><%=arrayCourse[i].getCname() %></option>
								</select>
					<% 
							}
					%> 
				
					<input type = "submit" value = "统 计" class = "button">
				<br><br>
				<%if(request.getParameter("CLno") != null && listWork.size()!=0){ %>
				<table class = "tableSeting">
							<thead><%=CLno %> <%=Cname %> 提 交 统 计</thead>
							<tr>
								<td>学 号</td>
								<td>姓 名</td>
								<td>性 别</td>
								<td>班 级</td>
								<td>系 别</td>
								<%//根据获得的作业集合显示作业选项
									for(int i=0; i<listWork.size(); i++){%>
										<td><%=listWork.get(i).getWtitle() %></td>
								<%
									}
								%>
							</tr>
							<%
								for(int i=0; i<listStudent.size(); i++){%>
								<tr>
									<td><%=listStudent.get(i).getSno() %></td>
									<td><%=listStudent.get(i).getSname() %></td>
									<td><%=listStudent.get(i).getSsex() %></td>
									<td><%=listStudent.get(i).getCLno() %></td>
									<td><%=listStudent.get(i).getSdept() %></td>
									<%//判断作业是否提交
									List<Ccheck> listCcheck = new ArrayList();
									for(int p=0; p<listListCcheck.size(); p++){
										for(int q=0; q<listListCcheck.get(p).size(); q++){
											if(listListCcheck.get(p).get(q).getSno().equals(listStudent.get(i).getSno())){
												listCcheck.add(listListCcheck.get(p).get(q));
											}
										}
									}
										for(int j=0; j<listWork.size(); j++){%>
											<td> 
											<%
											if(listCcheck.size() ==0){
											%>
												<span style = "color: red">否</span>
											<%
											}else{
												for(int m=0; m<listCcheck.size(); m++){
													if(listCcheck.get(m).getWno().equals(listWork.get(j).getWno())){
														%>是<%
														//listCcheck.remove(j);//将该记录从listCcheck集合中删除
														break;
													}else if(listCcheck.size()-1 == m){
														%>
														<span style = "color: red">否</span>
													<%}
												}
											}
											%>
										</td>
									<%
										}
									%>
								</tr>
							<% 	
								}
							%>
						</table>
				<%}%>
							<input name = "CLno_" type = "hidden" value = "<%=CLno %>"><input name = "Cname_" type = "hidden" value = "<%=Cname %>">
							<a href = "tStatisticClassServlet?">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href = "tStatisticClassServlet?">下一页</a>
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

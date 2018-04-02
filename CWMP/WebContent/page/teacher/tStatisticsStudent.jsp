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
    <!-- <script type="text/javascript" src = "/CWMP/js/jquery-3.1.1.js"></script> -->
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
$(".student").hide(); 
$(".student").eq(i).show(); 
} 
}); 
}); 
$("#class").change(); 
});
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
	  		
	  		//获取该教师对应授课班级的学生对象集合
	  		List<Student>[] arrayListStudent = new List[listTCCL.size()];
  			for(int i=0; i<listTCCL.size(); i++){
  				arrayListStudent[i] = (List)request.getAttribute("listStudent"+i);//将 学生对象集合存入数组中
  			}
  			
  			/* String Sname = null;
  			String Sno = null; 
  			List<Work> listResultWork = null;
  			List<List<Ccheck>> listListCcheck = new ArrayList<>();
  			if(request.getAttribute("Sno")!=null){
  			//获得要显示的作业集合
  			listResultWork = (List)request.getAttribute("listResultWork");
  			for(int i=0; i<listResultWork.size(); i++){
  				System.out.println("jsp--listResultWork--:"+listResultWork.get(i).getWcontent());
  			} 
  			//获得所有作业号在批改表中的记录集合数组
  			//List<Ccheck>[] arrayListCcheck = new List[listResultWork.size()];
  			for(int i=0; i<listResultWork.size();i++){
  				 listListCcheck.add(((List[])request.getAttribute("arrayListCcheck"))[i]);
  				//for(int j=0; j<arrayListCcheck[i].size(); j++)
  					//System.out.println("jsp--arrayListCcheck--:"+arrayListCcheck[i].get(j).getSno()+" "+arrayListCcheck[i].get(j).getWno()+" "+arrayListCcheck[i].get(j).getChtime());
  			}
  			//获得姓名对应的学号
  			Sno = request.getAttribute("Sno").toString();
  			//System.out.println("jsp--Sno--:"+Sno);
  			//获取名字
  			Sname = request.getAttribute("Sname").toString();
  			}  */
  			
  			
  			String Sname = null;
  			String Sno = null;
  			List<Work> listResultWork = null;
  			List<Ccheck> listCcheck = new ArrayList();
  			if(request.getAttribute("Sno")!=null){
  			//获得要显示的作业集合
  			listResultWork = (List)request.getAttribute("listResultWork");
  			for(int i=0; i<listResultWork.size(); i++){
  				System.out.println("jsp--listResultWork--:"+listResultWork.get(i).getWcontent());
  			}
  			//获得所有学号在批改表中的记录集合数组
  				listCcheck = (List)request.getAttribute("listCcheck");System.out.println("jsp--arrayListCcheck--size:"+listCcheck.size());
  				for(int j=0; j<listCcheck.size(); j++)
  					System.out.println("jsp--arrayListCcheck--:"+listCcheck.get(j).getSno()+" "+listCcheck.get(j).getWno()+" "+listCcheck.get(j).getUptime());
  			//获得姓名对应的学号
  			Sno = request.getAttribute("Sno").toString();
  			System.out.println("jsp--Sno--:"+Sno);
  			//获取名字
  			Sname = request.getAttribute("Sname").toString();
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
			<div style="height: 28px;"><a href="/CWMP/teacherServlet" >首 页</a></div>当前位置: 教师&gt;提交统计&gt;学生个人
				<center>
					<form action = "/CWMP/tStatisticsStudentServlet" method = "post">
					<select id = "class" name = "CLno">
						<option style="background-color: #AAAAAA">== 班 级 ==</option>
						<%
							for(int i=0; i<arrayTCCL.length;i++){
						%>
								<option style="background-color: #AAAAAA"><%=arrayTCCL[i].getCLno() %></option>
						<% 
							}
						%>
						
						<!-- <option style="background-color: #AAAAAA">142012</option> -->
					</select>
					<select class = "student" ><option>== 学 生 ==</option></select>
					
						<%
							for(int i=0; i<arrayListStudent.length;i++){
						%>	
								<select class="student" name = "<%=arrayListStudent[i].get(0).getCLno()%>">
						<%
								for(int j=0; j<arrayListStudent[i].size(); j++){
						%>
								<option style="background-color: #AAAAAA"><%=arrayListStudent[i].get(j).getSname() %></option>
						<% 
							}
						%>
								</select>
						<%}
						%>
					
					<input type = "submit" value = "统 计" class = "button">
					<br><br>
					<% 
					if(request.getAttribute("Sno")!=null){
						if(listResultWork.size() != 0){//如果作业为空则提示该班未布置作业
					%>
					<table class = "tableSeting">
							<thead><%=Sname %> 提 交 统 计</thead>
							<tr>
								<td>序 号</td>
								<td>作 业 号</td>
								<td>主 题</td>
								<td>截止时间</td>
								<td>提交时间</td>
								<td>是否提交</td>
							</tr>
							<%
								for(int i=0; i<listResultWork.size(); i++){
							%>
							<tr>
								<td><%=i+1 %></td>
								<td><%=listResultWork.get(i).getWno() %></td>
								<td><%=listResultWork.get(i).getWtitle() %></td>
								<td><%=listResultWork.get(i).getWdeadline() %></td>
								<%-- <%//查询提交时间
									if(listCcheck.get(i).getUptime() != null){
										%>
												<td><%=listCcheck.get(i).getUptime() %>  </td>
										<%
									}else{
										%>
												<td>  </td>
										<%
									}
								%> --%>
								
								<%-- <%//通过学号查找批改表中的提交时间是否为空判断是否提交
										for(int j=0; j<listResultWork.size(); j++){
											for(int k=0; k<arrayListCcheck[j].size(); k++){
												if(Sno.equals(arrayListCcheck[j].get(k).getSno())){
													if(arrayListCcheck[j].get(k).getChtime() != null){
														System.out.println("if:-Uptime:"+arrayListCcheck[j].get(k).getChtime());
													%>
													<td>是</td>
													<%
													}else{System.out.println("else:-Uptime:"+arrayListCcheck[j].get(k).getChtime());
														%>
														<td><span style = "color: red">否</span></td>
														<%
													}
												}
											}
										}	
								%> --%>
								<%//首先判断listCcheck是否为空,再查询作业号在不在批改表中,再通过作业号知道上传时间,根据是否为空判断是否提交
									if(listCcheck.size() != 0){
										for(int j=0; j<listCcheck.size(); j++){
											if(listResultWork.get(i).getWno().equals(listCcheck.get(j).getWno())){
												
													%>
													<td><%=listCcheck.get(j).getUptime() %></td>
													<td>是</td>
													<%
													listCcheck.remove(j);//将该记录从listCcheck集合中删除
													break;
											}else if(listCcheck.size()-1 == j){
											%>
											<td>  </td>
											<td><span style = "color: red">否</span></td>
										<%}
											
											}
									}else{
											%>
											<td>  </td>
											<td><span style = "color: red">否</span></td>
								<%}%>
							</tr>
							<%} 
						}else{
						%>
								<p>这 个 班 还 没 有 作 业 !</p>
					<%}}%>
					</table>
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

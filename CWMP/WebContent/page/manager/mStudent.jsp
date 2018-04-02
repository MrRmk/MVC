<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.JavaBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>管理员界面</title>
	    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
	    <link type="text/css" href="/CWMP/css/tableStyle.css" rel="stylesheet">
	    <style type="text/css">
	    	select{
		  	  	color:white;
				background: transparent;
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
					alert("删除成功!");
				}
				else if(flag != null){
					alert("删除失败!");
				}
			}
			/* //选中下拉框事件
			function ClassChange(){
				var x = document.getElementById("selectClass")
			    var grade = x.options[x.selectedIndex].value;//下拉列表中的值
				
			    alert(grade);
			} */
			
		</script>
		<!-- 去除下拉框中重复的值 -->
		<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.4.4.min.js"></script>
		<script>
		$(function(){
		    $("select").each(function(i,n){
		        var options = "";
		        $(n).find("option").each(function(j,m){
		            if(options.indexOf($(m)[0].outerHTML) == -1)
		            {
		                options += $(m)[0].outerHTML;
		            }
		        });
		        $(n).html(options);
		    });
		});
		//删除
		function del(dep_id){
		    var frm = document.form1;
		    frm.way.value = "del";
		    frm.action = "/servlet/DepServlet?dep_id="+dep_id+" ";
		    if(confirm("你确实要删除该记录吗？"))
		    frm.submit();
		} 
		//点击查询
		function query(){
			//获取下拉框中的值
		    var CLno = document.getElementById("selectClass");
		    var C = CLno.value;
		    window.location.href = "/CWMP/mStudentServlet?CLno="+C+" ";
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
	  	%>
	  	<%
			System.out.println("***mStudent********************");
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
						<li><a href="mTeacherServlet">教师管理</a><br>
							<ul>
								<li><a href="/CWMP/page/manager/addTeacher.jsp">添	&nbsp;加</a></li>
								<li><a href="mTeacherServlet">删&nbsp;除</a></li>
								<li><a href="mTeacherServlet">修&nbsp;改</a></li>
							</ul>
						</li><br>
						<li><a href="mStudentServlet">班级管理</a>
							<ul>
								<li><a href="/CWMP/page/manager/addStudent.jsp">添	&nbsp;加</a></li>
								<li><a href="mStudentServlet">删&nbsp;除</a></li>
								<li><a href="mStudentServlet">修&nbsp;改</a></li>
							</ul>
						</li><br>
						<li><a href="mCourseServlet">课程管理</a>
							<ul>
								<li><a href="/CWMP/page/manager/addCourse.jsp">添	&nbsp;加</a></li>
								<li><a href="mCourseServlet">删&nbsp;除</a></li>
								<li><a href="mCourseServlet">修&nbsp;改</a></li>
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
				当前位置 : 管理员&gt;班级管理
				<div  style="margin-top:0px;">
				<%
					List<Student> li = (List<Student>)request.getAttribute("list");
					String CLno1 = null; 
					 if( li != null ){ 
				%>
					<select id="selectClass" name="CLno1" style="margin-top:0px;" >
						<option style="background-color: #AAAAAA">==班级==</option>	
					<%
						for(int i=0; i<li.size(); i++){
							CLno1 = li.get(i).getCLno();
					%>		<option style="background-color: #AAAAAA"><%=CLno1 %></option>
					<%	}	%>
					</select>
					
					&nbsp;<input type="button" name="submit" value="查询" onclick="query()" class = "button"/>
				<!-- </form>	 -->
				<%	  }  	%>
				
				<%
					List<Student> listCLno = (List<Student>)request.getAttribute("listCLno");
					if( listCLno != null ){
						String ClassCLno = request.getAttribute("ClassCLno").toString();
						int CLnumber = Integer.parseInt(request.getAttribute("CLnumber").toString());
						System.out.println("CLnumber:"+CLnumber);
						
				%>
					<br><br>
					<div style="margin-right:30%; width:100%; height:100%; border:0px solid red;">
						人数 : <input type="text" id="number" value="<%=CLnumber %>" size="6" readonly  class = "input_text" style = "color:white; border:none"/>
					</div>
					<center>
					当前班级：<%=ClassCLno%>班<br>
					<table class = "tableSeting" style="margin-top:0px; border:1px solid red;">
						<tr>
							<td>序号</td>
							<td>学号</td>
							<td>姓名</td>
							<td>性别</td>
							<td>密码</td>
							<td>系别</td>
							<td>班级</td>
							<td>操作1</td>
							<td>操作2</td>
						</tr>
					<%
						
						for(int j=0; j<listCLno.size(); j++){
							System.out.println("***学生表添加了"+(j+1)+"条数据********************");
					  		String Sno = listCLno.get(j).getSno().toString();
					  		String Sname = listCLno.get(j).getSname().toString();
					  		String sex = listCLno.get(j).getSsex().toString();
					  		String Spassword = listCLno.get(j).getSpassword().toString();
					  		String Sdept = listCLno.get(j).getSdept().toString();
					  		String CLno = listCLno.get(j).getCLno().toString();
					  		
					  		//*********超链接 传递值 之前用编码一次(en编码)*********
					  		System.out.println("mStudent.jsp页面"+Sno+Sname +sex +Spassword +Sdept +CLno);
					  		Sno = java.net.URLEncoder.encode(Sno,"UTF-8");//解码
					  		Sname = java.net.URLEncoder.encode(Sname,"UTF-8");//解码
					  		sex = java.net.URLEncoder.encode(sex,"UTF-8");//解码
					  		Spassword = java.net.URLEncoder.encode(Spassword,"UTF-8");//解码
					  		Sdept = java.net.URLEncoder.encode(Sdept,"UTF-8");//解码
					  		CLno = java.net.URLEncoder.encode(CLno,"UTF-8");//解码 
					  		System.out.println("mStudent.jsp页面"+Sno+Sname +sex +Spassword +Sdept+CLno);
					%>
						<tr>
							<td><%=j+1 %></td>
							<td><%=listCLno.get(j).getSno() %></td>
							<td><%=listCLno.get(j).getSname() %></td>
							<td><%=listCLno.get(j).getSsex() %></td>
							<td><%=listCLno.get(j).getSpassword()%></td>
							<td><%=listCLno.get(j).getSdept() %></td>
							<td><%=listCLno.get(j).getCLno() %></td>
							<td><a href="/CWMP/page/manager/updateStudent.jsp?Sno=<%=Sno%>&Sname=<%=Sname%>&sex=<%=sex%>&Spassword=<%=Spassword%>&Sdept=<%=Sdept%>&CLno=<%=CLno%>">修改</a></td>
							<td><a href="mDeleteStudentServlet?Sno=<%=Sno%>" >删除</a></td>
						</tr>		
					<%		
						}
					}
					%>	
					</table>
					
					</center>
				
				
				<%-- 
					<center>
					<table class = "tableSeting">
							<thead>学生管理</thead>
							<tr>
								<td>序号</td>
								<td>学号</td>
								<td>姓名</td>
								<td>性别</td>
								<td>密码</td>
								<td>系别</td>
								<td>班级</td>
								<td>操作1</td>
								<td>操作2</td>
							</tr>
							<%
								List<Student> list = (List<Student>)request.getAttribute("list");
								for(int i=0; i<list.size(); i++){
									if( (li.get(1).getCLno().toString()).equals(list.get(i).getCLno().toString()) ){
										
									
									System.out.println("***学生表添加了"+(i+1)+"条数据********************");
							  		String Sno = list.get(i).getSno().toString();
							  		String Sname = list.get(i).getSname().toString();
							  		String sex = list.get(i).getSsex().toString();
							  		String Spassword = list.get(i).getSpassword().toString();
							  		String Sdept = list.get(i).getSdept().toString();
							  		String CLno = list.get(i).getCLno().toString();
							  		
							  		//*********超链接 传递值 之前用编码一次(en编码)*********
							  		System.out.println("mStudent.jsp页面"+Sno+Sname +sex +Spassword +Sdept +CLno);
							  		Sno = java.net.URLEncoder.encode(Sno,"UTF-8");//解码
							  		Sname = java.net.URLEncoder.encode(Sname,"UTF-8");//解码
							  		sex = java.net.URLEncoder.encode(sex,"UTF-8");//解码
							  		Spassword = java.net.URLEncoder.encode(Spassword,"UTF-8");//解码
							  		Sdept = java.net.URLEncoder.encode(Sdept,"UTF-8");//解码
							  		CLno = java.net.URLEncoder.encode(CLno,"UTF-8");//解码 
							  		System.out.println("mStudent.jsp页面"+Sno+Sname +sex +Spassword +Sdept+CLno);
							%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=list.get(i).getSno() %></td>
									<td><%=list.get(i).getSname() %></td>
									<td><%=list.get(i).getSsex() %></td>
									<td><%=list.get(i).getSpassword()%></td>
									<td><%=list.get(i).getSdept() %></td>
									<td><%=list.get(i).getCLno() %></td>
									<td><a href="/CWMP/page/manager/updateStudent.jsp?Sno=<%=Sno%>&Sname=<%=Sname%>&sex=<%=sex%>&Spassword=<%=Spassword%>&Sdept=<%=Sdept%>&CLno=<%=CLno%>">修改</a></td>
									<td><a href="mDeleteStudentServlet?Sno=<%=Sno%>" >删除</a></td>
								</tr>		
							<%		
									}
								}
							%>
						</table>						
					</center> --%>
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
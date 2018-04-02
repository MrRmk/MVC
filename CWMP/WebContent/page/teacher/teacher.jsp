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
    <script type="text/javascript" src = "/CWMP/js/jquery-3.1.1.js"></script>
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
		    
		    }
		   .add1{
		   		margin-top: 15%;
		   }
		   .showWorks{
		   		display:block;
		   		margin-top: 18%;
		   }
		   .showWorks:hover{
		   		font-size: 38px;color:#D1EEEE;
		   }
		   .showWorks:ACTIVE{
		   		font-size: 36px;color:#8470FF;
		   }
		   .time{
		   		margin-top:20% 0 0 50%;
		   		font-size: 15px;
		   }
		   .add1:hover{
		  		 margin-top: 5%;
		   		width:150px;
		   		height:150px;
		   }
		   .showDetails{
		       position: absolute;
		   		width:400px;
		  	 	height:330px;
		  	 	display:none;
		  	 	background-color: white;
		  	 	padding:0;
		   }
		     .add:active .showDetails{
		    	/*display: block;*/
		    }
		  .table_left{
		  	text-align: right;
		  	padding: 5px;
		  }
		  .table_right{
		  	text-align: left;
		  	padding: 5px;
		  }
    </style>
    <script type="text/javascript">
		window.onload=function(){		
			
			//定时器每秒调用一次fnDate()		
			setInterval(function(){		
			fnDate();		
			},1000);		
			Alert(<%=request.getAttribute("flag")%>);myrefresh();
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
				alert("删 除 成 功!");
				document.location.reload();
			}
		}
		setTimeout('Alert()',1000);
		
		//***************************************************
 		//淡入淡出
 		$("document").ready(function(){		//三个框
 			$("#1").click(function(){
 				$("#11").fadeToggle(1000);
 			})
 		});
 		$("document").ready(function(){
 			$("#2").click(function(){
 				$("#22").fadeToggle(1000);
 			})
 		});
 		$("document").ready(function(){
 			$("#3").click(function(){
 				$("#33").fadeToggle(1000);
 			})
 		});
 		$("document").ready(function(){	//向下箭头
 			$("#arrow").click(function(){
 				$("#all").fadeToggle(1000);
 			})
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
	  		
	  		//获取teacherServlet传递的Work表对象
	  		ArrayList   wList = (ArrayList)request.getAttribute("wList");
	  		Work[] w = new Work[wList.size()];
	  		if(wList !=null){
	  			for(int i=0; i<wList.size(); i++){
	  				w[i] = (Work)wList.get(i);//获取wList的内容,需强制转换
	  				System.out.println("内容:"+w[i].getWcontent());
	  			}
	  		}
  		%>
  		
		<!-- 头部 -->
		<div class = "top">
		
			<!-- 头部标题 -->
			<span style="font-size: 45px; font-family:sans-serif;margin-left: 35%">
				课程作业管理平台
			</span>
			<span style="margin-left:80%;">
				欢迎您: [<%=type%>]&nbsp;<%=name%>&nbsp;&nbsp;
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
			<div style="height: 28px;"><a href="/CWMP/teacherServlet" >首 页</a></div>当前位置: 教师&gt;作业管理
				<div class = "box">
				<div style = "border: 2px dashed #999;float:left;width:820px;text-align: center;"><img  id = "arrow" alt = "arrow" src = "/CWMP/img/arrow.png" width = "80px" height = "30px"/></div>
				<br>
				<div id = "all" style = "position: absolute;width:824px;text-align: center;height:100%; background-color: #999;color:white;float:left;display: none;margin-left: 190px;margin-top: 17px;">
					<table>
						<tr>
							<td width="60px">作业号</td>
							<td width="90px">主题</td>
							<td width="180px">截止时间</td>
							<td width="60px">发布者</td>
							<td width="60px">班级</td>
							<td width="60px">课程</td>
							<td width="220px">内容</td>
							<td>操作</td>
						</tr>
						<%
							for(int i=0; i<w.length; i++){
							%>
								<tr>
							<td><%=w[i].getWno() %></td>
							<td><%=w[i].getWtitle() %></td>
							<td><%=w[i].getWdeadline() %></td>
							<td><%=w[i].getTno() %></td>
							<td><%=w[i].getCLno() %></td>
							<td><%=w[i].getCno()%></td>
							<td><%=w[i].getWcontent()%></td>
							<td height = "28px"><a href = "/CWMP/teacherServlet?Wno=<%=w[i].getWno() %>">删 除</a></td>
							</tr>
							<%
							}
						%>
					
					</table>
				</div>
				<ul>
				<%
					for(int i=0,j=w.length-1; j>w.length-1-3; i++,j--){
						if(j>=0){
				%>
				<li>
						<div class = "add" id = "<%=i+1%>">
							<span class = "showWorks"><%=w[j].getWtitle() %></span>
							<span class = "time"><%=w[j].getWdeadline() %></span>
							<div class = "showDetails"  id = "<%=i+1 %><%=i+1 %>" style = "color:black; font-size: 15px;font-family: 微软雅黑;">
								<img alt="bom" src="/CWMP/img/bom.gif " width="400px">
								<table >
									<tr>
										<td class = "table_left">作业号:</td>
										<td class = "table_right"><%=w[j].getWno() %></td>
									<tr>
									<tr>
										<td class = "table_left">主 题:</td>
										<td class = "table_right"><%=w[j].getWtitle() %></td>
									<tr>
									<tr>
										<td class = "table_left">截止时间:</td>
										<td class = "table_right"><%=w[j].getWdeadline() %></td>
									<tr>
									<tr>
										<td class = "table_left">发布者:</td>
										<td class = "table_right"><%=w[j].getTno() %></td>
									<tr>
									<tr>
										<td class = "table_left">班 级:</td>
										<td class = "table_right"><%=w[j].getCLno()%></td>
									<tr>
									<tr>
										<td class = "table_left">课 程:</td>
										<td class = "table_right"><%=w[j].getCno()%></td>
									<tr>
									<tr>
										<td class = "table_left">内容:</td>
										<td class = "table_right"><%=w[j].getWcontent() %></td>
									<tr>
								</table>
								
							</div>
						</div>
						
					</li>
				<%	 }
						else{
				%>
						<li>
						<div class = "add" >
							<a href="/CWMP/tPublishWorkServlet"><img alt="add" src="/CWMP/img/add.png" class = "add1"></a>
						</div>
					</li>
				<%	}
					}
				%>
					
<!--  					<li>
						<div class = "add"  id = "2">
							<span class = "showWorks">web课程设计-详细设计</span>
							<span class = "time">2017-2-26</span>
							<div class = "showDetails" id = "22">
								<img alt="bom" src="/CWMP/img/bom.gif " width="400px">
							</div>
						</div>
					</li>
					<li>
						<div class = "add" id = "3">
							<span class = "showWorks" >web课程设计-规格说明</span>
							<span class = "time">2017-2-6</span>
							<div class = "showDetails" id = "33">
								<img alt="bom" src="/CWMP/img/bom.gif " width="400px">
							</div>
						</div>
					</li>		-->
					<li>
						<div class = "add" >
							<a href="/CWMP/tPublishWorkServlet"><img alt="add" src="/CWMP/img/add.png" class = "add1"></a>
						</div>
					</li>
					
					
				</ul>
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

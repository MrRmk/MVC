<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "com.JavaBean.*" %>
<%@ page import = "com.BeanDao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>学生界面</title>
	    <link type="text/css" href="/CWMP/css/frame.css" rel="stylesheet">
	    <script type="text/javascript" src = "/CWMP/js/jquery-3.1.1.js"></script>
	    <style type="text/css">
		    .box{
		    	padding: 5px;
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
			    height: 190px;
			    cursor: pointer;
			     color: white; 
			    font-size: 30px;
			    text-align: center;
			    
			    }
			.Notice {/*公告栏*/
			    border: 2px dashed #999;
			    width:400px;
			    height: 202px;
			    cursor: pointer;
			    color: white; 
			    text-align: center;
			    
			}
			table tr td {
	    		border: 1px solid #A6A6A6;
	    		height: 20px;
	    		padding: 3px;
	    		
	    	}
			.NoticeTable{/*公告表*/
				margin-left:150px;
				margin-top:10px;
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
		  	 	height:300px;
		  	 	display:none;
		  	 	background-color: white;
		  	 	padding:0;
		   }
	       .add:active .showDetails{
	    	/*display: block;*/
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
			//******************************************
	 		//淡入淡出
	 		$("document").ready(function(){		//三个框
	 			$("#1").click(function(){
	 				$("#11").fadeToggle(2000);
	 			})
	 		});
	 		$("document").ready(function(){
	 			$("#2").click(function(){
	 				$("#22").fadeToggle(2000);
	 			})
	 		});
	 		$("document").ready(function(){
	 			$("#3").click(function(){
	 				$("#33").fadeToggle(1000);
	 			})
	 		});
	 		$("document").ready(function(){	//菜单栏
	 			$(".menu.li").click(function(){
	 				$("ul").fadeToggle(1000);
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
	  		
	  		//获取studentServlet传递的Work表对象
	  		ArrayList wList = (ArrayList)request.getAttribute("wList");
	  		Work[] w = new Work[wList.size()];
	  		if( wList !=null ){
	  			for(int i=0; i<wList.size(); i++){
	  				w[i] = (Work)wList.get(i);//获取wList的内容,需强制转换
	  				System.out.println("student.jsp页面*****内容:"+w[i].getWcontent());
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
						<li><a href = "studentServlet">查看作业</a></li>
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
			<div style="height: 28px;"><a href="studentServlet" >首 页</a></div>当前位置: 学生&gt;查看作业
				<div class = "box" >
				<ul>
					
					<li style = "width:800px;">
						<div class = "Notice"  style = "width:810px;  ">
							<span style= "margin-top: 0; font-size:30px;">公 告 栏 </span>
							<img alt="gg" src="/CWMP/img/gg.png" style = "with:30px; height:30px;margin-left:5px;margin-top: 10px;float: left;"><br>
								
								<marquee><table class = "NoticeTable" >
									<tr>
										<td>序号</td>
										<td>主题</td>
										<td>发布者</td>
										<td>内容</td>
										<td>发布时间</td>
										<td>班级号</td>
									</tr>
									<%
										//获取studentServlet传递的公告表的对象
										List<Notice> NtList = (ArrayList)request.getAttribute("NtList");
										for( int j=0; j<NtList.size(); j++ ){
											String NtNo = NtList.get(j).getNtNo().toString();
											String NtTitle = NtList.get(j).getNtTitle().toString();
											String NtContent = NtList.get(j).getNtContent().toString();
											String NtTime = NtList.get(j).getNtTime().toString();
											String NtCLno = NtList.get(j).getNtCLno().toString();
											String NtTno = NtList.get(j).getNtTno().toString();
											//获取教师名
											TeacherDao tDao = new TeacherDao();
									  		String Tname = tDao.QueryByTno(NtTno).getTname().toString();
									  		System.out.println("student页面"+NtNo+NtTitle+NtContent+NtTime+NtCLno+NtTno);
									%>
									<tr>
										<td><%=j+1 %></td>
										<td><%=NtTitle %></td>
										<td><%=Tname %></td>
										<td><%=NtContent %></td>
										<td><%=NtTime %></td>
										<td><%=NtCLno %></td>
									</tr>
								<%		
									}
								%>
								</table></marquee>
						</div>
					</li>
					<%
						for(int i=0; i<3; i++){
							if(i<=w.length-1){
								//获取教师名
								TeacherDao tDao = new TeacherDao();
						  		String Tname = tDao.QueryByTno(w[i].getTno()).getTname().toString();
						  		//获取课程名
								CourseDao cDao = new CourseDao();
						  		String Cname = cDao.QueryByCno(w[i].getCno()).getCname().toString();
					%>
					 <li>
						<div class = "add" id = "<%=i+1%>">
							<span class = "showWorks"><%=w[i].getWtitle() %></span>
							<span class = "time"><%=w[i].getWdeadline() %></span>
							<div class = "showDetails"  id = "<%=i+1 %><%=i+1 %>" style = "color:black; font-size: 15px;font-family: 微软雅黑;">
								<img alt="bom" src="/CWMP/img/bom.gif " width="400px">
								<table >
									<tr>
										<td class = "table_left">作业号:</td>
										<td class = "table_right"><%=w[i].getWno() %></td>
									<tr>
									<tr>
										<td class = "table_left">主 题:</td>
										<td class = "table_right"><%=w[i].getWtitle() %></td>
									<tr>
									<tr>
										<td class = "table_left">截止时间:</td>
										<td class = "table_right"><%=w[i].getWdeadline() %></td>
									<tr>
									<tr>
										<td class = "table_left">教师号:</td>
										<td class = "table_right"><%=w[i].getTno() %></td>
									<tr>
									<tr>
										<td class = "table_left">发布者:</td>
										<td class = "table_right"><%=Tname %></td>
									<tr>
									<tr>
										<td class = "table_left">班 级:</td>
										<td class = "table_right"><%=w[i].getCLno()%></td>
									<tr>
									<tr>
										<td class = "table_left">课 程:</td>
										<td class = "table_right"><%=Cname%></td>
									<tr>
									<tr>
										<td class = "table_left">内容:</td>
										<td class = "table_right"><%=w[i].getWcontent() %></td>
									<tr>
								</table>
							</div>
						</div>
						
					</li>
					<%
							}
						}
					%>
					
					<%--<li>
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
					</li>
					<li>
						
					</li> --%>
					
					
				</ul>
				</div>
			</div>
		</div>
		
		<!-- foot -->
		<div class = "foot" style="margin-top:30%">
			<center>
				<hr>
				<p >
					版权：南昌航空大学 软件学院 142011班&nbsp;邮编：330036 &nbsp;联系电话：13672205014
				</p><br>
			</center>
		</div>
		
	</body>
</html>
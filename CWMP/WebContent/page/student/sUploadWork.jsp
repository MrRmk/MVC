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
	    <style type="text/css">
	    	.div1{
	    		
	    	}
	    	.div2{
	    		
	    	}
	    </style>
	    <script type="text/javascript">
			window.onload=function(){	
				Alert(<%=request.getAttribute("flag")%>,<%=request.getAttribute("row")%>);
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
			//文件是否上传成功提示
			function Alert(f,row) {
				var flag = f;
				var row = row;
				if( row==0 ){
					alert("已经提交过这次作业!");
				}
				if( (flag == 1)&&(row == 1) ){
					alert("文件上传成功！");
				}
				else if( flag == 2 ){
					alert("单个文件超出最大值！！！");
				}
				else if( flag == 3 ){
					alert("上传文件的总的大小超出限制的最大值！！！");
				}
				else if( flag == 4 ){
					alert("文件上传失败！");
				}
			}
			//判断选择的作业是否为空
			$("#select").change(  //为Select添加事件，当选择其中一项时触发
				function Work(){
					var select = document.getElementById("select").value;
					if( select=="0" )
					{
						alert("你没有选中一个作业!");
					}
				}
			); 
		</script>
		 
		<!-- 去除下拉框中重复的值 -->
		<!-- <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.4.4.min.js"></script>
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
		 
		//用JQuery实现二级联动菜单
		$(document).ready(function(){ 
			$("#Course").change(function(){ 
				$("#Course option").each(function(i,o){ 
					if($(this).attr("selected")) 
					{ 
						$(".Wno").hide(); 
						$(".Wno").eq(i).show(); 
					} 
				}); 
			}); 
			$("#class").change(); 
		});
		</script> -->
	</head>
	<body class = "top" style="background: url(/CWMP/img/1.jpg);">
  		
  		<!-- 接收session数据 -->
  		<%
  			//获取在登录界面设置的session值，在jsp页面间传递值用
	  		String account = session.getAttribute("account").toString();
  			String name = session.getAttribute("name").toString();
	  		String type = session.getAttribute("type").toString();
	  		
	  		//获取该学生的班级号
			StudentDao stu = new StudentDao();
			Student s = stu.QueryBySno(account);
			String CLno = s.getCLno().toString();
			System.out.println("sUploadWork1Servlet页面CLno:"+CLno);
			//按班级号在作业表中查询到所有的作业记录，放到数组wList中
			WorkDao  wDao = new WorkDao();
			List<Work> WList = (List<Work>) wDao.QueryByCLno(CLno);
		
			/* String text = request.getParameter("textarea");
			System.out.println("textarea:"+text); */
	  		
	  		
	  		/* List<Work> list = (List<Work>)request.getAttribute("list");
	  		List<Work> [] AL = new List[list.size()];
	  		//获取该学生对应的(作业表中的课程号对应的作业表)的集合
	  		for( int j=0; j<list.size(); j++ ){
				AL[j] = (List)request.getAttribute("listWno"+j);
			} */
	  		
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
				<div style="height: 28px;"><a href="/CWMP/studentServlet" >首 页</a></div>当前位置: 学生&gt;上传作业
				<br/>
				<div>
					
						<!-- 课程下拉框 -->
						<%-- <select id="Course" name="select1" style="background:none; color:white;">
							<option style="background-color: #AAAAAA; ">==课程==</option>
						<%	
							
							/* String Cno = null; 
							List<Work>[] AL = null;
							List<Work> ArrayList = null; */
							for(int i=0; i<list.size(); i++){					  		
						  		String Cno = list.get(i).getCno().toString();
						  		
						  		/* //把课程号Cno对应的作业表中的记录放到AL中
						  		AL = new List[list.size()];
						  		ArrayList = new ArrayList<>();
						  		for(int j=0; j<list.size(); j++){
									if( Cno.equals(list.get(j).getCno().toString()) ){
										Work w = list.get(j);//存放
										ArrayList.add(w);
									}
								}
						  		AL[i] = ArrayList; */
						  		
						  		//获取课程名
						  		CourseDao cDao = new CourseDao();
						  		String Cname = cDao.QueryByCno(Cno).getCname().toString();
						  		System.out.println("sUploadWork.jsp页面"+"课程号："+Cno+",课程名："+Cname);
						%>
							<option style="background-color: #AAAAAA"><%=Cname %></option>
						<%	}	%>
						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 --%>
						
						
						
						<%-- <%	
							for(int k=0; k<AL.length; k++){
						%>
								<select  class="Wno" name="select3" style="background:none; color:white;" >
						<%	
								for( int t=0; t<AL[k].size(); t++ ){
										/* String Wno = list1.get(j).getWno().toString(); *//* 
										System.out.println("sUploadWork.jsp页面****作业号："+Wno); */
						%>
									<option style="background-color: #AAAAAA;">&nbsp;&nbsp;<%=AL[k].get(t).getWno() %>&nbsp;&nbsp;</option>
						<%		}	
						%>		
								</select>
						<%	}	
						%> --%>
						
					<form action="/CWMP/sUploadWork2Servlet" enctype="multipart/form-data" method="post">
						<div class="div1" style="padding-left:600px;">
							<%
								//List<Work> list = (List<Work>)request.getAttribute("list");
								String Wno = "";//存放隐藏域变量
								if( WList !=null ){
							%>
							<!-- 作业下拉框 -->
							<select  class="Wno" id="select" name="select" style="background:none; color:white;">
								<option value="0" selected="selected" style="background-color:#696969; ">==选择要提交的作业==</option>
								<%
									for(int j=0; j<WList.size(); j++){
								%>		
										<option style="background-color: #AAAAAA"><%=WList.get(j).getWtitle().toString() %></option>
										
								<%		Wno = WList.get(j).getWno().toString();
									}
								}
								%>
							</select>
										<input type="hidden" name="Wno" value="<%=Wno%>" /><!--存放隐藏域变量 -->
						</div>
						<br/><br/><br/>
						<div class="div2" style="padding-left:450px;">
							<label >备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</label><textarea name="textarea" rows="15" cols="60"></textarea><br/>
						</div>
						<br/>
						<div class="div3" style="padding-left:450px;">
							<label >选择作业：</label><input type="file" name="fileUpload" id="fileUpload" accept="application/msword,text/plain" /><br/><br/><!-- application/zip, -->
						</div>
						<div class="div3" style="padding-left:600px;">
						
							<input type="submit" name="submit" class="button" id="submit" value="上传"/>
						</div>
						
					</form>
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

    </div>
	
	</body>
</html>
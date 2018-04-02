<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员页面</title>
<!-- <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> -->
</head>
<body>
<center>
	
</center>
<%
	String account = "1";
	String password = "2";
	String type = "3";
	if(request.getAttribute("account")!=null){
		account = request.getAttribute("account").toString();
	}
	if(request.getAttribute("password")!=null){
		password = request.getAttribute("password").toString();
	}
	if(request.getAttribute("type").toString()!=null){
		type = request.getAttribute("type").toString();
	}
%>
	<h1>欢迎您：<%=account %></h1>
	账号：<%=account %><br/>
	密码：<%=password %><br/>
	类型：<%=type %><br/>
</body>
</html>
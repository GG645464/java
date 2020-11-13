<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gg.model.Subject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
<title>考试结果</title>
<style type="text/css">
	td{
		font-size: 25px;
	}
</style>
</head>
<body>
	<h2 align="center">考试结果</h2>
	<hr>
    	 <table align="center" border="0">
	          <tr style="height: 50px;">
	            <td>编号:</td>
	            <td>${admin.id }</td>
	            <td style="width: 50px;"></td>
	            <td>姓名:</td>
	            <td>${admin.name }</td>
	          </tr>
          </table>
	
	<div align="center" style="margin-top:10px;font-size:40px;">
		考生分数:
		<span style="color:red">${score }</span>分
	</div>
	<div align="center">
		<a href="admin/subjectServlet?param=list&answer=t&testId=${test.id }" style="margin-top:40px;font-size:40px;">查看答案</a>
	</div>
</body>
</html>
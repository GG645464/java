<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gg.util.PageModel" %>
<%@ page import="com.gg.model.Teacher" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>老师信息修改页面</title>
</head>
<body>
	<h2 align="center">修改老师信息</h2>
   		<hr/>
   		<form action="admin/TeacherServlet?param=update&PageNo=${param.PageNo}" method="post">
    		<table width="400" boder="0" align="center">
    			<tr>
    				<td>编号:</td>			
    				<td><input type="text" name="id" value="${requestScope.teacher.id }" readonly="readonly" /></td>
    			</tr>
    			<tr>
    				<td>姓名:</td>
    				<td><input type="text" name="name" value="${requestScope.teacher.name }" /></td>
    			</tr>
    			<tr>
    				<td>年龄:</td>
    				<td><input type="text" name="age" value="${requestScope.teacher.age }" /></td>
    			</tr>
    			<tr>
    				<td>密码:</td>
    				<td><input type="text" name="password" value="${requestScope.teacher.password }" /></td>
    				
    			</tr>
    			<tr>
    				<td></td>
    				<td>
    					<input type="submit" value="提交" style="margin-right:40px;margin-top:20px;" />
    					<input type="reset" value="重置" />
    				</td>
    			</tr>
    		</table>
    	</form>
</body>
</html>
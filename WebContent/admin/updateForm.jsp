<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.gg.model.Student" %>
<%@ page import="com.gg.util.PageModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改学生信息页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>	
   		<h2 align="center">修改学生信息</h2>
   		<hr/>
   		<form action="admin/stuServlet?param=update&PageNo=${param.PageNo}" method="post">
    		<table width="400" border="0" align="center">
    			<tr>
    				<td>学号:</td>			<!-- requestScope.stu()拿到学生对象    .id(调用getId()方法访问id) -->
    				<td><input type="text" name="id" value="${requestScope.stu.id }" readonly="readonly" /></td>
    			</tr>
    			<tr>
    				<td>姓名:</td>
    				<td><input type="text" name="name" value="${requestScope.stu.name }" /></td>
    			</tr>
    			<tr>
    				<td>年龄:</td>
    				<td><input type="text" name="age" value="${requestScope.stu.age }" /></td>
    			</tr>
    			<tr>
    				<td>密码:</td>
    				<td><input type="text" name="password" value="${requestScope.stu.password }" /></td>
    				
    			</tr>
    			<tr>
    				<td>班级:</td>
    				<td>
	    				<select name="cid">
		    				<c:forEach items="${requestScope.cList }" var="cLs">
			    				<c:choose>
			    					<c:when test="${cLs.id eq requestScope.stu.classes.id  }">
			    						<option value="${cLs.id }" selected="selected">${cLs.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${cLs.id }">${cLs.name }</option>
			    					</c:otherwise>
			    				</c:choose>
		    				</c:forEach>
		    			</select>
    				</td>
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

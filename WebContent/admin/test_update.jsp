<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gg.model.Test" %>
<%@ page import="com.gg.util.PageModel" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>试卷信息修改</title>


</head>

<body>
	<h2 align="center">修改试卷信息</h2>
   		<hr/>
   		<form action="admin/testServlet?param=update&PageNo=${param.PageNo}" method="post">
    		<table width="400" border="0" align="center">
    			<tr>
    				<td>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>		
    				<td><input type="text" name="id" value="${requestScope.test.id }" readonly="readonly" /></td>
    			</tr>
    			<tr>
    				<td>试卷名称：</td>
    				<td>
	    				<input type="text" name="name" value="${requestScope.test.testName }" />
    				</td>
    			</tr>
    			<tr>
    				<td>时间：</td>
    				<td>
	    				<input type="text" name="time" value="${requestScope.test.time }" />
    				</td>
    			</tr>
    			<tr>
    				<td colspan="2" align="center">
    					<input type="submit" value="提交" style="margin-right:40px;margin-top:20px;" />
    					<input type="reset" value="重置" />
    				</td>
    			</tr>
    		</table>
    	</form>
</body>
</html>
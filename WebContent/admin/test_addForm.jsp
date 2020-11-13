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
<title>试卷信息添加</title>

<script type="text/javascript">

function toIndex(){
	window.location.href = "admin/testServlet?param=list&PageNo=1"
}
</script>
</head>
<body>
	<h2 align="center">添加试卷信息</h2>
    	<hr/>
    	<input align="right" type="button" value="返回" onclick="toIndex();">
    	<center style="color:red">${requestScope.result }</center>								
    	<form action="admin/testServlet?param=add&PageNo=${param.PageNo }" method="post">
    		<table width="400" boder="0" align="center">
    			<tr>
    				<td>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</td>
    				<td><input type="text" name="id" id="id"/></td>
    			</tr>
    			<tr>
    				<td>试卷名称:</td>
    				<td><input type="text" name="name" id="name" /></td>
    			</tr>
    			<tr>
    				<td>时间:</td>
    				<td><input type="text" name="time" id="time" /></td>
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
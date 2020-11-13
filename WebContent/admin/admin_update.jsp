<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gg.model.Admin" %>
<%@ page import="com.gg.model.Type" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户信息修改</title>
</head>
<body>
	<h2 align="center">修改信息</h2>
   		<hr/>
   		<form action="PersonServlet?param=update" method="post">
    		<table width="400" border="0" align="center">
    			<tr>
    				<td>编号:</td>			
    				<td><input type="text" name="id" value="${admin.id }" readonly="readonly" /></td>
    			</tr>
    			<tr>
    				<td>姓名:</td>
    				<td><input type="text" name="name" value="${admin.name }" /></td>
    			</tr>
    			<tr>
    				<td>密码:</td>
    				<td><input type="text" name="password" value="${admin.password }" /></td>
    				
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
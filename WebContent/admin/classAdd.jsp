<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">添加班级信息</h2>
    	<hr/>								
    	<form action="admin/ClassesServlet?param=add&PageNo=${param.PageNo }" method="post">
    		<table width="400" boder="0" align="center">
    			<tr>
    				<td>编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</td>
    				<td><input type="text" name="id" id="id"/></td>
    			</tr>
    			<tr>
    				<td>班级名称:</td>
    				<td><input type="text" name="name" id="name" /></td>
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
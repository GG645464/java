<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.gg.model.Student" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户信息修改</title>
<script type="text/javascript">
	function toIndex(){
		window.location.href = "admin/PersonServlet?param=find"
	}
</script>
</head>
<body>
	<h2 align="center">修改信息</h2>
   		<hr/>
   		<input align="right" type="button" value="返回" onclick="toIndex();">
   		<form action="admin/PersonServlet?param=update" method="post">
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
    				<td>年龄:</td>			
    				<td><input type="text" name="age" value="${admin.age }" /></td>
    			</tr>
    			
    			<tr>
    				<td>密码:</td>
    				<td><input type="text" name="password" value="${admin.password }" /></td>
    			</tr>
    			<tr>
    				<td>班级:</td>
    				<td>
	    				<select name="cid">
		    				<c:forEach items="${requestScope.cList }" var="cLs">
			    				<c:choose>
			    					<c:when test="${cLs.id eq admin.classes.id  }">
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
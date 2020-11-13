<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gg.model.Test" %>
<%@ page import="com.gg.util.PageModel" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>试卷信息管理</title>

<script type="text/javascript">


//首页
function topPage(){
	window.location.href = "admin/testServlet?param=list&PageNo=${pageModel.topPage}";
}
//上一页
function previousPage(){
	
	window.location.href = "admin/testServlet?param=list&PageNo=${pageModel.previousPage}";
}
//下一页
function nextPage(){
	window.location.href = "admin/testServlet?param=list&PageNo=${pageModel.nextPage}";
}
//尾页
function lastPage(){
	window.location.href = "admin/testServlet?param=list&PageNo=${pageModel.lastPage}";
}

function toIndex(){
	window.location.href = "student/student_index.html"
}

</script>
<style type="text/css">
		.div td{
			font-size: 30px;
			
			width: 120px;
		}
		.div a{
			text-decoration: none;
			color: black;
		}
	</style>
</head>
<body>
	<div class="div" style="width: 100%;height: 60px;background-color: gold;">
	<table align="center" style="height: 60px;" border="0">
		<tr>
			<td><a href="student/student_index.html">首页</a></td>
			<td></td>
			<td><a href="admin/testServlet?param=list&PageNo=1">试卷</a></td>
			<td></td>
			<td><a href="admin/PersonServlet?param=find">个人信息</a></td>
		</tr>
	</table>
</div>
		<h1 align="center">试卷信息列表</h1>
    	<hr/>
    	<input align="right" type="button" value="返回" onclick="toIndex();">
    	<center>
    		<form action="admin/testServlet?param=find&PageNo=${pageModel.pageNo }" method="post">
	    		<input style="margin-right:20px;" type="text"  id="testName" value="${name }" name="testName" placeholder="按试卷名称搜索" >
	    		<input style="width: 70px; height: 25px;" type="submit" value="查询" id="submit" />
    		</form>
    	</center>
    	<table width="600" border="1" align="center">
    		<tr>
    			<th>编号</th>
    			<th>试卷名称</th>
    			<th>时间/分</th>
    		</tr>
    				<c:forEach items="${requestScope.testList }" var="test">
				<tr>
    		  		<td align="center" width="15%">${test.id }</td>
    		  		<td align="center" width="37%"><a href='admin/subjectServlet?param=list&testId=${test.id }'>${test.testName }</a></td>
    		  		<td align="center" width="15%">${test.time }</td>
    		  	</tr>
    		  		</c:forEach>
    	</table>
    	<table width="400" border="0" align="center" style="margin-top:30px;">
    			<tr>
    		  		<td align="center">
    		  			<input type="button" value="首页" onclick="topPage();" />&nbsp;&nbsp;&nbsp;&nbsp;
    		  			<input type="button" value="上一页" onclick="previousPage();" />&nbsp;&nbsp;&nbsp;&nbsp;
    		  			<input type="button" value="下一页" onclick="nextPage();" />&nbsp;&nbsp;&nbsp;&nbsp;
    		  			<input type="button" value="尾页" onclick="lastPage();" />
    		  			&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">${pageModel.pageNo }</font>/${pageModel.totalPages }页
    		  		</td>
    		  	</tr>
    	</table>
</body>
</html>
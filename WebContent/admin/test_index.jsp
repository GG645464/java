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

function changeCheck(){
	var check =  document.getElementById("check0");
	var checks =  document.getElementsByName("testName");
	for(var i=0;i<checks.length;i++){
		if(check.checked){
			checks[i].checked = check.checked;
		}else{
			checks[i].checked = false;
		}
		
	}
}

function addForm(){
	window.location.href= "admin/test_addForm.jsp?PageNo=${pageModel.pageNo }";
}

function deleteForm(){
	var result =  window.confirm("确认删除？");
	var checks =  document.getElementsByName("testName");
	if(result){
		for(var i=0;i<checks.length;i++){
			if(checks[i].checked){
				 return true;
				}
			}
			alert("请选择要删除的对象！")
			return false;
		}else{
		return false;
	}
	
}

function confirmDel(id){
	var result =  window.confirm("确认删除？");
	if(result){
		window.location.href= "admin/testServlet?param=delete&id="+id+"&PageNo=${pageModel.pageNo }";
	}
	
}

function update(id){
	window.location.href = 'admin/testServlet?param=modify&PageNo=${pageModel.pageNo }&id='+id;
}

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


</script>
</head>
<body>
		<h1 align="center">试卷信息列表</h1>
    	<hr/>
    	<center>
    		<form action="admin/testServlet?param=find&PageNo=${pageModel.pageNo }" method="post">
	    		<input style="margin-right:20px;" type="text"  id="testName" value="${name }" name="testName" placeholder="按试卷名称搜索" >
	    		<input style="width: 70px; height: 25px;" type="submit" value="查询" id="submit" />
    		</form>
    	</center>
    	
    	
    	<form action="admin/testServlet?param=deleteCounts" method="post" onsubmit="return deleteForm();">
    	<table width="800" border="0" align="center">
    		<tr align="right">
    			<td>
    				<input style="width: 70px; height: 25px;" type="button" value="添加" onclick="addForm()" />	
    				<input type="submit" value="批量删除" style="width: 70px; height: 25px;" />
    			</td>
    		</tr>
    	</table>
    	<table width="600" border="1" align="center">
    		<tr>
    			<th><input style="width: 30px; height: 18px;" type="checkbox" id="check0" onclick="changeCheck()"></th>
    			<th>编号</th>
    			<th>试卷名称</th>
    			<th>时间/分</th>
    			<th>操作</th>
    		</tr>
    				<c:forEach items="${requestScope.testList }" var="test">
				<tr>
					<td align="center" width="3%"><input style="width: 30px; height: 18px;" value="${test.id }" type="checkbox" name="testName"></td>
    		  		<td align="center" width="15%">${test.id }</td>
    		  		<td align="center" width="37%"><a href='admin/subjectServlet?param=list&testId=${test.id }'>${test.testName }</a></td>
    		  		<td align="center" width="15%">${test.time }</td>
    		  		<td width="32%" align="center">
    		  			<input type="button" style="margin-left:20px;" value="修改" onclick="update(${test.id })" />
	    		  		<input type="button" style='margin-left:20px;' value="删除" onclick="confirmDel(${test.id })" />
    		  		</td>
    		  	</tr>
    		  		</c:forEach>
    	</table>
    	</form>
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
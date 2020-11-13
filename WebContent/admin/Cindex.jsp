<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <base href="<%=basePath%>">
<head>
<meta charset="UTF-8">
<title>班级信息首页</title>
<script type="text/javascript">
function addForm(){
	//window.location.href= "admin/addForm.jsp?PageNo=${pageModel.pageNo }";
	window.location.href= "admin/classAdd.jsp?PageNo=${pageModel.pageNo }";
}

function confirmDel(id){
	var result =  window.confirm("确认删除？");
	if(result){
		window.location.href= "admin/ClassesServlet?param=delete&id="+id+"&PageNo=${pageModel.pageNo }";
	}
	
}

function update(id){
	window.location.href = 'admin/ClassesServlet?param=modify&PageNo=${pageModel.pageNo }&id='+id;
}

//首页
function topPage(){
	window.location.href = "admin/ClassesServlet?param=list&PageNo=${pageModel.topPage}";
}
//上一页
function previousPage(){
	
	window.location.href = "admin/ClassesServlet?param=list&PageNo=${pageModel.previousPage}";
}
//下一页
function nextPage(){
	window.location.href = "admin/ClassesServlet?param=list&PageNo=${pageModel.nextPage}";
}
//尾页
function lastPage(){
	window.location.href = "admin/ClassesServlet?param=list&PageNo=${pageModel.lastPage}";
}


</script>
</head>
<body>
	<h1 align="center">班级信息列表</h1>
    	<hr/>
    	
    	<table width="400" border="0" align="center">
    		<tr>
    			<td align="right"><input type="button" value="添加" onclick="addForm()" /></td>
    		</tr>
    	</table>
    	<table width="400" border="1" align="center">
    		<tr>
    			<th>编号</th>
    			<th>班级名称</th>
    			<th>操作</th>
    		</tr>
    				<c:forEach items="${requestScope.clsList }" var="cls">
				<tr>
    		  		<td align="center" width="30%">${cls.id }</td>
    		  		<td align="center" width="30%">${cls.name }</td>
    		  		<td width="40%" align="center">
    		  			<input type="button" style="margin-left:20px;" value="修改" onclick="update(${cls.id })" />
	    		  		<input type="button" style="margin-left:20px;" value="删除" onclick="confirmDel(${cls.id })" />
    		  		</td>
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
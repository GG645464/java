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
<title>添加老师信息页面</title>
	<script type="text/javascript">
		
		function checkForm(){
			var idNode = document.getElementById("id");
			var nameNode = document.getElementById("name");
			var ageNode = document.getElementById("age");
			var passwordNode = document.getElementById("password");
			
			//Id的限定规则
			var regId = /^\d{4}$/;	
			var regAge = /^\d{1,2}$/;
			
			if(idNode.value.length==0){
				alert("学号不能为空！");
				return false;
			}
			if(!regId.test(idNode.value)){
				alert("学号应为4位数字！");
				return false;
			}	
			
			var regName = /\s+/;
			if(nameNode.value.replace(regName,"").length==0){
				alert("姓名不能为空！");
				return false;
			}
			
				
			
			if(ageNode.value.length==0){
				alert("年龄不能为空！");
				return false;
			}
			
			
			if(!regAge.test(ageNode.value)){
				alert("年龄应为1-2位数字！");
				return false;
			}
			
			
			
			if(passwordNode.value.length==0){
				alert("密码不能为空！");
				return false;
			}
			
			return true;
		}
		
		function toIndex(){
			window.location.href = "admin/TeacherServlet?param=list&PageNo=1"
		}
		
		
	</script>
</head>
<body>
	<h2 align="center">添加老师</h2>
    	<hr/>												<!-- 限定验证onsubmit="return checkForm();" -->
    	<input align="right" type="button" value="返回" onclick="toIndex();">
    	<center style="color:red">${requestScope.result }</center>
    	<form action="admin/TeacherServlet?param=add&PageNo=${param.PageNo}" method="post">
    		<table width="400" boder="0" align="center">
    			<tr>
    				<td>编号:</td>
    				<td><input type="text" name="id" id="id"/></td>
    			</tr>
    			<tr>
    				<td>姓名:</td>
    				<td><input type="text" name="name" id="name" /></td>
    			</tr>
    			<tr>
    				<td>年龄:</td>
    				<td><input type="text" name="age" id="age" /></td>
    			</tr>
    			<tr>
    				<td>密码:</td>
    				<td><input type="text" name="password" id="password" /></td>
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
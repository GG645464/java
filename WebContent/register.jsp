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
<title>注册</title>
<script type="text/javascript">

	function toIndex(){
		window.location.href = "login.jsp";
	}
</script>
</head>
<body>
	<input align="right" type="button" value="返回" onclick="toIndex();">
	<h2 align="center">选择注册用户</h2>
	<hr />
	<table align="center" width="900px" border="0">
		<tr>
			<td>
				<div align="center"><a href="registerForm?param=teacher" style="text-decoration: none;font-size: 30px;background-color: gold;color: black;">老师注册</a></div>
			</td>
			<td>	
				<div align="center"><a href="registerForm?param=student" style="text-decoration: none;font-size: 30px;background-color: gold;color: black;">学生注册</a></div>
			</td>
		</tr>
	</table>
</body>
</html>
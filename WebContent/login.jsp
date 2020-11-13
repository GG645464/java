<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	
		function checkForm(){
			var userName = document.getElementById("username");
			var pwd = document.getElementById("pwd");
			
			if(userName.value.length==0){
				alert("用户名不能为空！");
				return false;
			}
			
			if(pwd.value.length==0){
				alert("密码不能为空！");
				return false;
			}
		}
		
		function register(){
			window.location.href = "register.jsp";
		}
		
	</script>
  </head>
  
  <body>
   		<h2 align="center">用户登录</h2>
   		<hr/>
   		<form action="login" method="post" align="center" onsubmit="return checkForm();">
   			用户名：<input style="margin-top:10px;" value="${account }" type="text" id="username" name="account" align="center" /><br />
   			密码：<input style="margin-top:10px;" value="${password }" type="password" id="pwd" name="password" align="center" /><br />
   			<select style="margin-top:10px;" name="type">
   				<option value="管理员">管理员</option>		
   				<option value="老师">老师</option>
   				<option value="学生">学生</option>
   			</select>
   			<br />
   			<input style="margin-top:10px;" type="button" value="注册" onclick="register();" />
   			<input style="margin-top:10px;margin-left:30px;" type="submit" value="登录" />
   		 	
   		 	<h3><font color="red">${error }</font></h3>
   		</form>
   			
  </body>
</html>

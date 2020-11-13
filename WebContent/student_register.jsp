<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
<meta charset="UTF-8">
<title>学生注册页面</title>
<script type="text/javascript">
		function checkForm(){
			var userName = document.getElementById("name");
			var age = document.getElementById("age");
			var pwd = document.getElementById("pwd");
			var pwd1 = document.getElementById("pwd1");
			
			var regSpace = /\s+/;
			var regAge = /^\d{1,2}$/;	

			if(userName.value.replace(regSpace,"").length==0){
				alert("用户名不能为空！");
				return false;
			}
			
			if(age.value.length==0){
				alert("年龄不能为空！");
				return false;
			}
			
			if(!regAge.test(age.value)){
				alert("年龄应为1-2位数字！");
				return false;
			}
			
			if(pwd.value.length==0){
				alert("请输入密码！");
				return false;
			}

			if(pwd.value.replace(regSpace,"").length==0){
				alert("密码不能为空！");
				return false;
			}

			if(pwd.value!=pwd1.value){
				alert("密码输入不一致！");
				return false;
			}


			return true;

		}
		
		function toIndex(){
			window.location.href = "register.jsp";
		}
		
		function toLogin(){
			window.location.href = "login.jsp";
		}

	</script>
</head>
<body>
<input align="right" type="button" value="返回" onclick="toIndex();">
<input align="right" type="button" value="登录" onclick="toLogin();">
	<h2 align="center">学生注册页面</h2>
	<hr />
	<center><h3><font color="red">${result }</font></h3></center>
	  <form action="registerServlet?param=student" method="post" name="form" onsubmit="return checkForm();">
    <table width="600" align="center" border="0">
      <tr style="height: 50px;">
        <td align="right">用户名:</td>
        <td align="left" colspan="2"><input type="text" name="name" id="name">*</td>
      </tr>
      <tr style="height: 50px;">
        <td align="right">年龄:</td>
        <td align="left" colspan="2"><input type="text" name="age" id="age">*</td>
      </tr>
      <tr style="height: 50px;">
        <td align="right">密&nbsp;&nbsp;&nbsp;码:</td>
        <td align="left" colspan="2"><input type="password" id="pwd" name="pwd">*</td>
      </tr>
      <tr style="height: 50px;">
        <td align="right">确认密码:</td>
        <td align="left" colspan="2"><input type="password" name="pwd1" id="pwd1">*</td>
      </tr>
      <tr style="height: 50px;">
    	<td align="right">班级:</td>
    	<td align="left" colspan="2">
	    	<select name="cid">
	    		<c:forEach items="${cList }" var="classes">
		    		<option value="${classes.id }">${classes.name }</option>
		    	</c:forEach>
		    </select>
    	</td>
    	</tr>
    </table>
    <center style='margin-top: 20px;margin-bottom: 50px;'>
      <input type="submit" value="点击注册" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="reset">
    </center>
</body>
</html>
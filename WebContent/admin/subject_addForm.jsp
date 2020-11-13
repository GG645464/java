<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gg.model.Test" %>
<%@ page import="com.gg.model.Subject" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>添加试题</title>
<script type="text/javascript">

function toIndex(){
	var sUrl = document.referrer;
	window.location.href = sUrl;
}
</script>
</head>
<body>
	<h2 align="center">添加试题</h2>
    	<hr/>												<!-- 限定验证onsubmit="return checkForm();" -->
    	<input align="right" type="button" value="返回" onclick="toIndex();">
    	<form action="admin/subjectServlet?param=add&testId=${test.id }" method="post">
    		<table width="600" border="0" align="center">
    			<!-- <tr>
    				<td style="height:35px;">编号:</td>
    				<td><input type="text" name="id" id="id" style="width:343px;"/></td>
    			</tr>
    			 -->
    			<tr>
    				<td style="height:35px;">题目:</td>
    				<td><input type="text" name="title" id="title" style="width:343px;" /></td>
    			</tr>
    			<tr>
    				<td style="height:35px;">选项A:</td>
    				<td><input type="text" name="optionsA" id="optionsA" style="width:343px;" /></td>
    			</tr>
    			<tr>
    				<td style="height:35px;">选项B:</td>
    				<td><input type="text" name="optionsB" id="optionsB" style="width:343px;" /></td>
    			</tr>
    			<tr>
    				<td style="height:35px;">选项C:</td>
    				<td><input type="text" name="optionsC" id="optionsC" style="width:343px;" /></td>
    			</tr>
    			<tr>
    				<td style="height:35px;">选项D:</td>
    				<td><input type="text" name="optionsD" id="optionsD" style="width:343px;" /></td>
    			</tr>
    			
    			<tr>
    				<td style="height:35px;">分值:</td>
    				<td><input type="text" name="score" id="score" style="width:343px;" /></td>
    			</tr>
    			<tr>
    				<td>答案:</td>
    				<td>
    					<select name="answer" style="width:343px">
		    					<option value="A">A</option>
		    					<option value="B">B</option>
		    					<option value="C">C</option>
		    					<option value="D">D</option>
		    			</select>
    				</td>
    			</tr>
    			<tr>
    				<td>解析:</td>
    				<td>
    					<textarea name="desc" id="desc" cols="45" rows="5"></textarea>
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
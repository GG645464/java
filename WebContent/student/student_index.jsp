<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.gg.model.Admin" %>
<%@ page import="com.gg.model.Type" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Insert title here</title>
<script type="text/javascript">
	function update(id){
		window.location.href = 'admin/PersonServlet?param=modify&id='+id;
	}
	
	function logout(){
		window.location.href = 'admin/PersonServlet?param=logout';
	}
</script>
<style type="text/css">
 		td {
 			font-size: 25px;
 		}
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
	<h2 align="center">个人信息</h2>
    	<hr/>
    	 <table align="center" border="0">
          <tr style="height: 100px;">
          	<td>编号:</td>
            <td>${admin.id }</td>
            <td style="width: 100px;"></td>
          	<td>姓名:</td>
            <td>${admin.name }</td>
          </tr>
          <tr>
            
          </tr>
          <tr style="height: 100px;">
          	<td>年龄:</td>
            <td>${admin.age }</td>
            <td style="width: 100px;"></td>
            <td>密码:</td>
            <td>${admin.password }</td>
           </tr>
           <tr style="height: 100px;">
            <td>班级:</td>
            <td>${admin.classes.name }</td>
            <td style="width: 100px;"></td>
            <td>用户类型:</td>
            <td>${admin.type.typeName }</td>
          </tr>
          <tr style="height: 100px;" align="center">
              <td colspan="4">
                  <input type="button" style="margin-left:20px;height:40px;width: 80px; position: relative;right: 60px;" value="修改" onclick="update(${admin.id })" />
                  <input type="button" style="margin-left:20px;height:40px;width: 80px; position: relative;left: 40px; " value="退出登录" onclick="logout();" />
              </td>
          </tr>
        </table>
</body>
</html>
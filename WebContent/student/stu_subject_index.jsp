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
<title>试卷内容</title>
<script type="text/javascript">

		var maxtime = ${test.time }* 60;
		//var maxtime = 5;
		function CountDown() {
			if (maxtime >= 0) {
				minutes = Math.floor(maxtime / 60);
				seconds = Math.floor(maxtime % 60);
				msg = "剩余考试时间：" + minutes + "分" + seconds + "秒";
				document.all["timer"].innerHTML = msg;
				if (maxtime == 15 * 60) {
					alert('考生请注意！离考试时间结束还有15分钟!');
				}
				--maxtime;
			}
			else {
				clearInterval(timer);
				//时间到了就提交试卷
				//alert(document.getElementById("button2"));
				document.getElementById("button2").click();
				
			}
		}
			
			timer = setInterval("CountDown()", 1000);
						
		
		function toIndex(){
			window.location.href = "admin/testServlet?param=list&PageNo=1";
		}
		
	</script>
	<style type="text/css">
		td{
			font-size: 30px;
			
			width: 120px;
		}
		a{
			text-decoration: none;
			color: black;
		}
	</style>
	
</head>
<body>
		<div style="width: 100%;height: 60px;background-color: gold;">
	<table align="center" style="height: 60px;" border="0">
		<tr>
			<td><a href="student/student_index.html">首页</a></td>
			<td></td>
			<td><a href="admin/testServlet?param=list&PageNo=1">试卷</a></td>
			<td></td>
			<td><a href="#">个人信息</a></td>
		</tr>
	</table>
</div>

	<h3 id='timer' style="position: fixed;right: 10px;color: crimson;">
	</h3>


	<h2 align="center">${test.testName }</h2>
	<div>
		<input align="right" type="button" value="返回" onclick="toIndex();">
		<span style="position: relative;left:300px;font-size: 20px;">本试卷一共${count }题，共${totalscore }分，考试结束后将自动交卷</span>
	</div>
	<hr>
	<form action="admin/subjectServlet?param=submit&testId=${test.id }" method="post" onsubmit="return checkSubject();">

		<div align="center">
				<input style="height: 30px;width: 100px;font-size: 22px;" type="submit" id="button2" value="交卷">
			</div>
		
		<c:forEach items="${requestScope.subjectList }" varStatus="number" var="subject">
			<h3>
				<span>${number.index+1}.${subject.title }</span>
				<span>(单选题/${subject.score }分)</span>
			</h3>

			<p style="position: relative;font-size: 18px;left: 50px;">
				<input type="radio" name="subject_option_${subject.id }" value="A">A.${subject.optionsA }<br>
				<input type="radio" name="subject_option_${subject.id }" value="B">B.${subject.optionsB }<br>
				<input type="radio" name="subject_option_${subject.id }" value="C">C.${subject.optionsC }<br>
				<input type="radio" name="subject_option_${subject.id }" value="D">D.${subject.optionsD }
			</p>
			<!-- visibility:visible -->
			<div style="visibility:hidden;background-color: gold;bottom: 40px;" id="div_answer"><div>我的答案${result[number.index] }&nbsp;&nbsp;&nbsp;答案:${subject.answer }</div><h3 style="color: red;">解析:${subject.desc }</h3></div>
	
		</c:forEach>
			

	</form>
</body>
</html>
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

		var maxtime = ${test.time}*60;
		//var maxtime = 5;
		
		var x = 1;
		function CountDown() {
			if (maxtime >= 0) {
				minutes = Math.floor(maxtime / 60);
				seconds = Math.floor(maxtime % 60);
				msg = "剩余考试时间：" + minutes + "分" + seconds + "秒";
				document.all["timer"].innerHTML = msg;
				if (maxtime == 15 * 60) {
					//alert('考生请注意！离考试时间结束还有15分钟!');
				}
				--maxtime;
				x += 1;
				if(x>1){
					clearInterval(timer);
				}
			}
			else {
				clearInterval(timer);
				//时间到了就提交试卷

				
			}
		}
		
		
		
		window.onload = function(){
			timer = setInterval("CountDown()", 1000);
			
		}
		
		
		function addForm(){
			window.location.href="admin/subjectServlet?param=addForm&testId=${test.id}";
		}
		
		function confirmDel(id){
			var result =  window.confirm("确认删除？");
			if(result){
				window.location.href= "admin/subjectServlet?param=delete&testId=${test.id }&id="+id;
			}
			
		}
		
		function update(id){
			window.location.href = 'admin/subjectServlet?param=modify&testId=${test.id }&id='+id;
		}
		
		function toIndex(){
			window.location.href = "admin/testServlet?param=list&PageNo=1";
		}
		
	</script>
</head>
<body>

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
				<input style="height: 30px;width: 100px;font-size: 22px;" type="button" name="add" value="添加试题" onclick="addForm();">
				<input style="height: 30px;width: 100px;font-size: 22px;" type="submit" id="submit" name="submit" value="交卷">
			</div>
		
		<c:forEach items="${requestScope.subjectList }" varStatus="number" var="subject">
			<div>
			<h3>
				<span>${number.index+1}.${subject.title }</span>
				<span>(单选题/${subject.score }分)</span>
			</h3>
			
			<div align="right">
				<input type="button" name="motify" onclick="update(${subject.id })" value="修改试题"  style="position: relative;bottom:42px;right: 200px;font-size: 20px;right: 200px;height: 30px;width: 100px;margin-right: 20px;">
				<input type="button" onclick="confirmDel(${subject.id })" name="delete" value="删除试题" style="position: relative;bottom:42px;font-size: 20px;right: 200px;height: 30px;width: 100px;">
			</div>
			
			</div>

			<p style="position: relative;bottom:42px;font-size: 18px;left: 50px;">
				<input type="radio" name="subject_option_${subject.id }" value="A">A.${subject.optionsA }<br>
				<input type="radio" name="subject_option_${subject.id }" value="B">B.${subject.optionsB }<br>
				<input type="radio" name="subject_option_${subject.id }" value="C">C.${subject.optionsC }<br>
				<input type="radio" name="subject_option_${subject.id }" value="D">D.${subject.optionsD }
			</p>
			<div style="background-color: gold;bottom: 40px;"><div>我的答案${result[number.index] }&nbsp;&nbsp;&nbsp;答案:${subject.answer }</div><h3 style="color: red;">解析:${subject.desc }</h3></div>
	
		</c:forEach>
			

	</form>
</body>
</html>
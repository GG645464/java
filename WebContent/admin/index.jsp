<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gg.model.Student" %>
<%@ page import="com.gg.util.PageModel" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		window.onload = function(){
			var submit = document.getElementById("submit");
			var reset = document.getElementById("reset1");
			console.log(submit.offsetTop+"|"+submit.offsetLeft+"|"+submit.offsetWidth);
			console.log(reset.offsetTop)
			reset.style.position = "absolute";
			reset.style.top = submit.offsetTop;
			reset.style.left = submit.offsetLeft+submit.offsetWidth+20;
		}
	
		function changeCheck(){
			var check =  document.getElementById("check0");
			var checks =  document.getElementsByName("sname");
			for(var i=0;i<checks.length;i++){
				if(check.checked){
					checks[i].checked = check.checked;
				}else{
					checks[i].checked = false;
				}
				
			}
		}
	
		function addForm(){
			//window.location.href= "admin/addForm.jsp?PageNo=${pageModel.pageNo }";
			window.location.href= "admin/stuServlet?param=stuForm&PageNo=${pageModel.pageNo }";
		}
		
		function deleteForm(){
			var result =  window.confirm("确认删除？");
			var checks =  document.getElementsByName("sname");
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
				window.location.href= "admin/stuServlet?param=delete&id="+id+"&PageNo=${pageModel.pageNo }";
			}
			
		}
		
		function update(id){
			window.location.href = 'admin/stuServlet?param=modify&PageNo=${pageModel.pageNo }&id='+id;
		}
		
		//重置查询条件
		function reset(){
			var stuName = document.getElementById("stuName");
			var age = document.getElementById("age");
			stuName.value = "";
			age.value = "";
			//stuName[placeholder]="按姓名搜索";
			//age[placeholder] = "按年龄搜索";
			document.getElementById("submit").click();
			
		}
		
		
		//首页
		function topPage(){
			window.location.href = "admin/stuServlet?param=list&PageNo=${pageModel.topPage}";
		}
		//上一页
		function previousPage(){
			
			window.location.href = "admin/stuServlet?param=list&PageNo=${pageModel.previousPage}";
		}
		//下一页
		function nextPage(){
			window.location.href = "admin/stuServlet?param=list&PageNo=${pageModel.nextPage}";
		}
		//尾页
		function lastPage(){
			window.location.href = "admin/stuServlet?param=list&PageNo=${pageModel.lastPage}";
		}
		
	</script>

  </head>
  <body>
    	<h1 align="center">学生信息列表</h1>
    	<hr/>
    	<center>
    		<form action="admin/stuServlet?param=find&PageNo=${pageModel.pageNo }" method="post">
	    		<input style="margin-right:20px;" type="text"  id="stuName" value="${name }" name="stuName" placeholder="按姓名搜索" >
	    		<input style="margin-right:20px;" type="text"  id="age" name="age" value="${age }" placeholder="按年龄搜索" >
	    		<input style="width: 70px; height: 25px;" type="submit" value="查询" id="submit" />
    		</form>
    		<div><input style="width: 70px; height: 25px;" type="button" id="reset1" value="重置查询" onclick="reset();" /></div>
    	</center>
    	<form action="admin/stuServlet?param=deleteCounts" method="post" onsubmit="return deleteForm();">
    	<table width="800" border="0" align="center">
    		<tr align="right">
    			<td>
    				<input style="width: 70px; height: 25px;" type="button" value="添加" onclick="addForm()" />	
    				<input type="submit" value="批量删除" style="width: 70px; height: 25px;" />
    			</td>
    		</tr>
    	</table>
    	<table width="800" border="1" align="center" >
	    		<tr>
	    			<th><input style="width: 30px; height: 18px;" type="checkbox" id="check0" onclick="changeCheck()"></th>
	    			<th>学号</th>
	    			<th>姓名</th>
	    			<th>年龄</th>
	    			<th>密码</th>
	    			<th>班级</th>
	    			<th>操作</th>
	    		</tr>		 
				<c:forEach items="${requestScope.stuList }" var="stu">
				<tr>
					<td align="center" width="5%"><input style="width: 30px; height: 18px;" value="${stu.id }" type="checkbox" name="sname"></td>
    		  		<td align="center" width="10%">${stu.id }</td>
    		  		<td align="center" width="15%">${stu.name }</td>
    		  		<td align="center" width="17%">${stu.age }</td>
    		  		<td align="center" width="18%">${stu.password }</td>
    		  		<td align="center" width="16%">${stu.classes.name }</td>
    		  		<td width="20%">
    		  			<input type="button" style="margin-left:20px;" value="修改" onclick="update(${stu.id })" />
	    		  		<!--<a href='admin/stuServlet?param=modify&PageNo=${pageModel.pageNo }&id=${stu.id }'>修改</a>-->
	    		  		<!-- <a href onclick="confirmDel(${stu.id })">删除</a> -->
	    		  		<input type="button" style='margin-left:20px;' value="删除" onclick="confirmDel(${stu.id })" />
    		  		</td>
    		  	</tr>	
    		  	</c:forEach>  
    	</table>
    	</form>
    	<table width="800" border="0" align="center" style="margin-top:30px;">
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

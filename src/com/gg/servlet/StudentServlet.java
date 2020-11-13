package com.gg.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.dao.ClassesDao;
import com.gg.dao.StudentDao;
import com.gg.model.Classes;
import com.gg.model.Student;
import com.gg.util.PageModel;
import com.gg.util.StringUtil;

@WebServlet(urlPatterns={"/admin/stuServlet"},name="stuServlet")
public class StudentServlet extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	private StudentDao studentDao = new StudentDao();
	private ClassesDao classesDao = new ClassesDao();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		if("find".equals(param)) {
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
//			int pageNo = 1;
			String name = request.getParameter("stuName");
			String age = (String)request.getParameter("age");
			request.setAttribute("name", name);
			request.setAttribute("age", age);
			
			if(StringUtil.isNoEmpty(name)||StringUtil.isNoEmpty(age)) {
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("stuName",name);
				map.put("age",age);
				
				List<Student> stuList = studentDao.finStudentsByMap(map);
				request.setAttribute("stuList", stuList);
				int totalRecords = studentDao.queryTotalRecordsMap(map);
				PageModel<Student> pageModel = new PageModel<Student>();
				pageModel.setList(stuList);
				pageModel.setTotalRecords(totalRecords);
				pageModel.setPageNo(1);
				request.setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("index.jsp?PageNo="+1).forward(request, response);
			}else {
				int totalRecords = studentDao.queryTotalRecords();
				
				//查询第一页列表
				List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
				request.setAttribute("stuList", stuList);
				
				//传递一个PageModel对象
				PageModel<Student> pageModel = new PageModel<Student>();
				pageModel.setList(stuList);
				pageModel.setTotalRecords(totalRecords);
				pageModel.setPageNo(pageNo);
				request.setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("index.jsp?Page=No"+pageNo).forward(request, response);
			}
		}
		else if("stuForm".equals(param)){
			//查询所有班级
			List<Classes> cList = classesDao.queryAllClasses();
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("addForm.jsp").forward(request, response);
		}
		else if("modify".equals(param)){
			
			int id = Integer.parseInt(request.getParameter("id"));
			Student stu = studentDao.findStudentById(id);
			request.setAttribute("stu", stu);
			
			//查询所有班级
			List<Classes> cList = classesDao.queryAllClasses();
			request.setAttribute("cList", cList);
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			int totalRecords = studentDao.queryTotalRecords();
			
			//传递一个PageModel对象
			PageModel<Student> pageModel = new PageModel<Student>();
			pageModel.setList(stuList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("updateForm.jsp?PageNo="+pageNo).forward(request, response);
		}
		else if("list".equals(param)){
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			
			//查询总记录数
			int totalRecords = studentDao.queryTotalRecords();
			
			//查询第一页列表
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			request.setAttribute("stuList", stuList);
			
			//传递一个PageModel对象
			PageModel<Student> pageModel = new PageModel<Student>();
			pageModel.setList(stuList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			//转发index.jsp
			request.getRequestDispatcher("index.jsp?Page=No"+pageNo).forward(request, response);
		}
		else if("add".equals(param)){
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String password = request.getParameter("password");
			int cid =  Integer.parseInt(request.getParameter("cid"));
			Classes classes = new Classes(cid);
			Student stu = new Student(id,name,age,password,classes);
			boolean flag =  studentDao.saveStudent1(stu);
			if(flag) {
				request.setAttribute("result", "添加成功！");
			}else {
				request.setAttribute("result", "抱歉，您的添加有误，添加失败！");
			}
			
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			int totalRecords = studentDao.queryTotalRecords();
			
			//传递一个PageModel对象
			PageModel<Student> pageModel = new PageModel<Student>();
			pageModel.setList(stuList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			List<Classes> cList = classesDao.queryAllClasses();
			request.setAttribute("cList", cList);
			
			request.getRequestDispatcher("addForm.jsp").forward(request, response);
//			response.sendRedirect("addmin/addForm.jsp");
			//重定向回到index.jsp页面
		}else if("update".equals(param)){	//这样写会报空指针异常!!!【 param.rquals("update")】
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			int totalRecords = studentDao.queryTotalRecords();
			
			//传递一个PageModel对象
			PageModel<Student> pageModel = new PageModel<Student>();
			pageModel.setList(stuList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String password = request.getParameter("password");
			int cid = Integer.parseInt(request.getParameter("cid"));
			Classes classes = new Classes(cid);
			Student stu = new Student(id,name,age,password,classes);
			studentDao.updateStudent(stu);
			
			//重定向回到index.jsp页面
			response.sendRedirect("stuServlet?param=list&PageNo="+pageNo);
			
		}else if("deleteCounts".equals(param)) {
			int id;
			String[] stuId = request.getParameterValues("sname");
			for(String sid:stuId) {
				id = Integer.parseInt(sid);
				studentDao.deleteStudent(id);
			}
			response.sendRedirect("stuServlet?param=list&PageNo="+1);
			
		
		}else if("delete".equals(param)){
			
			//执行删除学生
			int id = Integer.parseInt(request.getParameter("id"));
			studentDao.deleteStudent(id);
			
			//获取页数
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			
			int totalRecords = studentDao.queryTotalRecords();
			int totalPages = totalRecords%5==0?totalRecords/5:(totalRecords/5+1);
			if(pageNo>totalPages) {
				if(pageNo==1) {
					
				}else {
					pageNo--;
				}
			}
			
			response.sendRedirect("stuServlet?param=list&PageNo="+pageNo);
			
		}else{
			
			response.sendRedirect("other.jsp");
		}
		
		
	}
	
	
}

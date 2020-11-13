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

import com.gg.dao.TeacherDao;
import com.gg.model.Teacher;
import com.gg.util.PageModel;
import com.gg.util.StringUtil;


@WebServlet(urlPatterns={"/admin/TeacherServlet"},name="TeacherServlet")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TeacherDao teacherDao = new TeacherDao();
    public TeacherServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取url地址参数(设置参数把多个servlet整合到一个servlet里)
		//http://localhost:8080/Online_exam/TeacherServlet?param=list&PageNo=1
		String param = request.getParameter("param");
		
		
		if("find".equals(param)) {
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
//			int pageNo = 1;
			String name = request.getParameter("teacherName");
			String age = (String)request.getParameter("age");
			request.setAttribute("teacherName", name);
			request.setAttribute("age", age);
			
			if(StringUtil.isNoEmpty(name)||StringUtil.isNoEmpty(age)) {
				Map<String, String> map = new HashMap<String, String>();
				
				map.put("teacherName",name);
				map.put("age",age);
				
				List<Teacher> teacherList = teacherDao.finTeachersByMap(map);
				request.setAttribute("teacherList", teacherList);
				int totalRecords = teacherDao.queryTotalRecordsMap(map);
				
				PageModel<Teacher> pageModel = new PageModel<Teacher>();
				pageModel.setList(teacherList);
				pageModel.setTotalRecords(totalRecords);
				pageModel.setPageNo(1);
				request.setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("Tindex.jsp?PageNo="+1).forward(request, response);
			}else {
				int totalRecords = teacherDao.queryTotalRecords();
				
				//查询第一页列表
				List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
				request.setAttribute("teacherList", teacherList);
				
				//传递一个PageModel对象
				PageModel<Teacher> pageModel = new PageModel<Teacher>();
				pageModel.setList(teacherList);
				pageModel.setTotalRecords(totalRecords);
				pageModel.setPageNo(pageNo);
				request.setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("Tindex.jsp?Page=No"+pageNo).forward(request, response);
			}
		}else if("modify".equals(param)){
			
			int id = Integer.parseInt(request.getParameter("id"));
			Teacher teacher = teacherDao.findTeacherById(id);
			request.setAttribute("teacher", teacher);
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
			int totalRecords = teacherDao.queryTotalRecords();
			
			//传递一个PageModel对象
			PageModel<Teacher> pageModel = new PageModel<Teacher>();
			pageModel.setList(teacherList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("Tupdate.jsp?PageNo="+pageNo).forward(request, response);
		}
		
		//
		else if("list".equals(param)){
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			
			//查询总记录数
			int totalRecords = teacherDao.queryTotalRecords();
			
			//查询第一页列表
			List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
			request.setAttribute("teacherList", teacherList);
			
			//传递一个PageModel对象
			PageModel<Teacher> pageModel = new PageModel<Teacher>();
			pageModel.setList(teacherList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			//转发index.jsp
			request.getRequestDispatcher("Tindex.jsp?Page=No"+pageNo).forward(request, response);
		}
		else if("add".equals(param)){
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String password = request.getParameter("password");
			
			Teacher teacher = new Teacher(id,name,age,password);
			boolean flag =  teacherDao.saveTeacher1(teacher);
			if(flag) {
				request.setAttribute("result", "添加成功！");
			}else {
				request.setAttribute("result", "抱歉，您的添加有误，添加失败！");
			}
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
			int totalRecords = teacherDao.queryTotalRecords();
			
			//传递一个PageModel对象
			PageModel<Teacher> pageModel = new PageModel<Teacher>();
			pageModel.setList(teacherList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("TaddForm.jsp").forward(request, response);
//			response.sendRedirect("addmin/addForm.jsp");
			//重定向回到index.jsp页面
		}else if("update".equals(param)){	//这样写会报空指针异常!!!【 param.rquals("update")】
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
			int totalRecords = teacherDao.queryTotalRecords();
			
			//传递一个PageModel对象
			PageModel<Teacher> pageModel = new PageModel<Teacher>();
			pageModel.setList(teacherList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String password = request.getParameter("password");
			
			Teacher teacher = new Teacher(id,name,age,password);
			teacherDao.updateTeacher(teacher);
			
			//重定向回到index.jsp页面
			response.sendRedirect("TeacherServlet?param=list&PageNo="+pageNo);
			
		}else if("deleteCounts".equals(param)) {
			int id;
			String[] teacherId = request.getParameterValues("tname");
			for(String tid:teacherId) {
				id = Integer.parseInt(tid);
				teacherDao.deleteTeacher(id);
			}
			response.sendRedirect("TeacherServlet?param=list&PageNo="+1);
			
		
		}else if("delete".equals(param)){
			
			//执行删除
			int id = Integer.parseInt(request.getParameter("id"));
			teacherDao.deleteTeacher(id);
			
			//获取页数
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			
			int totalRecords = teacherDao.queryTotalRecords();
			int totalPages = totalRecords%5==0?totalRecords/5:(totalRecords/5+1);
			if(pageNo>totalPages) {
				if(pageNo==1) {
					
				}else {
					pageNo--;
				}
			}
			
			response.sendRedirect("TeacherServlet?param=list&PageNo="+pageNo);
			
		}else{
			
			response.sendRedirect("other.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

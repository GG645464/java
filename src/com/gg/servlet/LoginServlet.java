package com.gg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.dao.AdminDao;
import com.gg.model.Admin;
import com.gg.model.Student;
import com.gg.model.Teacher;
import com.gg.model.Type;

@WebServlet(urlPatterns={"/login"},name="LoginServlet")
public class LoginServlet extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDao();
//	private StudentDao studentDao = new StudentDao();
//	private TeacherDao teacherDao = new TeacherDao();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取登录页面的参数[用户名，密码，用户类型]
		String account= request.getParameter("account");
		String password= request.getParameter("password");
		String typeString = request.getParameter("type");
		Type type =null;
		
		//根据登录用户不同，创建不同类型用户
		if("管理员".equals(typeString)) {
			type = new Type(1, typeString);
		}else if("老师".equals(typeString)) {
			type = new Type(2, typeString);
		}else if("学生".equals(typeString)) {
			type = new Type(3, typeString);
		}
		
		//把用户名和密码保存到request对象中[当输入错误的用户名和密码时刷新login.jsp页面提示]
		request.setAttribute("account", account);
		request.setAttribute("password", password);
		
		
		//根据不同用户登录，连接对应的数据库{表}查询用户是否存在(已注册)
		boolean legal = false;
		if("管理员".equals(typeString)) {
			Admin admin = new Admin(account,password);
			legal = adminDao.isExistsAdmin(admin);
			if(legal){
				
				//查询到用户信息，，
				admin = adminDao.findAdmin(admin);
				//设置对应权限
				admin.setType(type);
				
				//保存登录用户到session对象中(用作权限校验和展示个人信息)
				request.getSession().setAttribute("admin", admin);
				response.sendRedirect("admin/admin_index.html");
				
			}else {
				request.setAttribute("error", "用户名或密码错误！");
				//服务器端跳转[不用服务器跳转错误提示不出来]
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
	
		}else if("学生".equals(typeString)) {
			Student student = new Student(account,password);
			legal = adminDao.isExistsStudent(student);
			if(legal){
				
				student = adminDao.findStudent(student);
				student.setType(type);
				request.getSession().setAttribute("admin", student);
				//跳转学生用户界面
				response.sendRedirect("student/student_index.html");
			}else {
				request.setAttribute("error", "用户名或密码错误！");
				//服务器端跳转[不用服务器跳转错误提示不出来]
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if("老师".equals(typeString)) {
			Teacher teacher = new Teacher(account,password);
			legal = adminDao.isExists(teacher);
			if(legal) {
				
				teacher = adminDao.findTeacher(teacher);
				teacher.setType(type);
				request.getSession().setAttribute("admin", teacher);
				//跳转老师界面
				response.sendRedirect("teacher/teacher_index.html");
			}else {
				request.setAttribute("error", "用户名或密码错误！");
				//服务器端跳转[不用服务器跳转错误提示不出来]
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
	
		}else {
			//跳转其他界面
			response.sendRedirect("other.jsp");
		}
		
	}
		
		
	
}

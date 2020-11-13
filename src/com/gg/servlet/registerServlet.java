package com.gg.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.dao.AdminDao;
import com.gg.model.Classes;
import com.gg.model.Student;
import com.gg.model.Teacher;
import com.gg.model.Type;


@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao adminDao = new AdminDao();
    public registerServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param =  request.getParameter("param");
		Type type = null;
		
		if("student".equals(param)) {
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String pwd = request.getParameter("pwd");
			int cid =  Integer.parseInt(request.getParameter("cid"));
			Classes classes = new Classes(cid);
			type = new Type(3, "学生");
			Student student = new Student(name, age, pwd, classes, type);
			
			boolean flag = adminDao.saveStudent(student);
			if(flag) {
				request.setAttribute("result", "注册成功！");
				request.getRequestDispatcher("registerForm?/param=student").forward(request, response);
			}else {
				request.setAttribute("result", "注册失败！");
				request.getRequestDispatcher("student_register.jsp").forward(request, response);
			}
			
			
		}else if("teacher".equals(param)) {
			
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			String pwd = request.getParameter("pwd");
			type = new Type(2, "老师");
			Teacher teacher = new Teacher(name, age, pwd, type);
			
			boolean flag1 = adminDao.findTeacherByName(name);
			
			if(flag1) {
				request.setAttribute("result", "用户名已注册！");
				request.getRequestDispatcher("teacher_register.jsp").forward(request, response);
			}else {
				boolean flag2 = adminDao.saveTeacher(teacher);
				if(flag2) {
					request.setAttribute("result", "注册成功！");
					request.getRequestDispatcher("teacher_register.jsp").forward(request, response);
				}else {
					request.setAttribute("result", "注册失败！");
					request.getRequestDispatcher("teacher_register.jsp").forward(request, response);
				}
				
			}
				
			
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

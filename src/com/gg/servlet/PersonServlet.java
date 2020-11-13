package com.gg.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.dao.AdminDao;
import com.gg.dao.ClassesDao;
import com.gg.dao.StudentDao;
import com.gg.dao.TeacherDao;
import com.gg.model.Admin;
import com.gg.model.Classes;
import com.gg.model.Student;
import com.gg.model.Teacher;
import com.gg.model.Type;


@WebServlet("/admin/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminDao adminDao = new AdminDao();
    private TeacherDao teacherDao = new TeacherDao();
    private StudentDao studentDao = new StudentDao();
    private ClassesDao classesDao = new ClassesDao();
    public PersonServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object object = request.getSession().getAttribute("admin");
		String typeName=null;
		int id = 0;
		if("class com.gg.model.Admin".equals(object.getClass().toString())) {
			Admin admin = (Admin)request.getSession().getAttribute("admin");
			typeName = admin.getType().getTypeName();
			id = admin.getId();
		}else if("class com.gg.model.Teacher".equals(object.getClass().toString())) {
			Teacher Teacher = (Teacher)request.getSession().getAttribute("admin");
			typeName = Teacher.getType().getTypeName();
			id = Teacher.getId();
		}else if("class com.gg.model.Student".equals(object.getClass().toString())) {
			Student student = (Student)request.getSession().getAttribute("admin");
			typeName = student.getType().getTypeName();
			id = student.getId();
		}
		
		String param = request.getParameter("param");
		if("logout".equals(param)) {
			if("学生".equals(typeName)) {
				response.sendRedirect("../login.jsp");	
			}else if("老师".equals(typeName)) {
				response.sendRedirect("../login.jsp");
			}
			else {
				request.getSession().removeAttribute("admin");
				response.sendRedirect("admin_index.html");	
			}
		}else if("find".equals(param)) {
			
			if("管理员".equals(typeName)) {
				
				Admin admin =  adminDao.findAdminById(id);
//				request.getSession().removeAttribute("admin");
				request.getSession().setAttribute("admin", admin);
				
				response.sendRedirect("person.jsp");
			}else if("老师".equals(typeName)) {
				Teacher teacher = teacherDao.findTeacherById(id);
				Type type = new Type(2,"老师");
				teacher.setType(type);
				request.getSession().setAttribute("admin", teacher);
				response.sendRedirect("../teacher/teacher_index.jsp");
			}else if("学生".equals(typeName)){
				Student student = studentDao.findStudentById(id);
				Classes classes=classesDao.findClassById(student.getClasses().getId());
				Type type = new Type(3,"学生");
				student.setClasses(classes);
				student.setType(type);
				request.getSession().setAttribute("admin", student);
				response.sendRedirect("../student/student_index.jsp");
			}else {
				response.sendRedirect("../other.jsp");
			}
			
			
			
		}else if("modify".equals(param)) {
			
			if("管理员".equals(typeName)) {
				
				response.sendRedirect("admin_update.jsp");
			}else if("老师".equals(typeName)) {
				response.sendRedirect("../teacher/teacher_update.jsp");
			}else if("学生".equals(typeName)){
				List<Classes> cList = classesDao.queryAllClasses();
				request.setAttribute("cList", cList);
				
				request.getRequestDispatcher("../student/student_update.jsp").forward(request, response);
			}else {
				response.sendRedirect("../other.jsp");
			}
		}else if("update".equals(param)) {
			if("管理员".equals(typeName)) {
				int aid = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				Admin admin = new Admin(aid,name,password);
				adminDao.update(admin);
				response.sendRedirect("PersonServlet?param=find");
				
			}else if("老师".equals(typeName)) {
				int tid = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				int age = Integer.parseInt(request.getParameter("age"));
				String password = request.getParameter("password");
				Teacher teacher = new Teacher(tid, name, age, password);
				teacherDao.updateTeacher(teacher);
				response.sendRedirect("PersonServlet?param=find");
			}else if("学生".equals(typeName)){
				int tid = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				int age = Integer.parseInt(request.getParameter("age"));
				String password = request.getParameter("password");
				int cid = Integer.parseInt(request.getParameter("cid"));
				Classes classes = new Classes(cid);
				Student stu = new Student(id,name,age,password,classes);
				studentDao.updateStudent(stu);
				response.sendRedirect("PersonServlet?param=find");
			}
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

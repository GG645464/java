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
		
		//��ȡ��¼ҳ��Ĳ���[�û��������룬�û�����]
		String account= request.getParameter("account");
		String password= request.getParameter("password");
		String typeString = request.getParameter("type");
		Type type =null;
		
		//���ݵ�¼�û���ͬ��������ͬ�����û�
		if("����Ա".equals(typeString)) {
			type = new Type(1, typeString);
		}else if("��ʦ".equals(typeString)) {
			type = new Type(2, typeString);
		}else if("ѧ��".equals(typeString)) {
			type = new Type(3, typeString);
		}
		
		//���û��������뱣�浽request������[�����������û���������ʱˢ��login.jspҳ����ʾ]
		request.setAttribute("account", account);
		request.setAttribute("password", password);
		
		
		//���ݲ�ͬ�û���¼�����Ӷ�Ӧ�����ݿ�{��}��ѯ�û��Ƿ����(��ע��)
		boolean legal = false;
		if("����Ա".equals(typeString)) {
			Admin admin = new Admin(account,password);
			legal = adminDao.isExistsAdmin(admin);
			if(legal){
				
				//��ѯ���û���Ϣ����
				admin = adminDao.findAdmin(admin);
				//���ö�ӦȨ��
				admin.setType(type);
				
				//�����¼�û���session������(����Ȩ��У���չʾ������Ϣ)
				request.getSession().setAttribute("admin", admin);
				response.sendRedirect("admin/admin_index.html");
				
			}else {
				request.setAttribute("error", "�û������������");
				//����������ת[���÷�������ת������ʾ������]
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
	
		}else if("ѧ��".equals(typeString)) {
			Student student = new Student(account,password);
			legal = adminDao.isExistsStudent(student);
			if(legal){
				
				student = adminDao.findStudent(student);
				student.setType(type);
				request.getSession().setAttribute("admin", student);
				//��תѧ���û�����
				response.sendRedirect("student/student_index.html");
			}else {
				request.setAttribute("error", "�û������������");
				//����������ת[���÷�������ת������ʾ������]
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if("��ʦ".equals(typeString)) {
			Teacher teacher = new Teacher(account,password);
			legal = adminDao.isExists(teacher);
			if(legal) {
				
				teacher = adminDao.findTeacher(teacher);
				teacher.setType(type);
				request.getSession().setAttribute("admin", teacher);
				//��ת��ʦ����
				response.sendRedirect("teacher/teacher_index.html");
			}else {
				request.setAttribute("error", "�û������������");
				//����������ת[���÷�������ת������ʾ������]
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
	
		}else {
			//��ת��������
			response.sendRedirect("other.jsp");
		}
		
	}
		
		
	
}

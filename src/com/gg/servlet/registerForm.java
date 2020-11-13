package com.gg.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.dao.ClassesDao;
import com.gg.model.Classes;


@WebServlet("/registerForm")
public class registerForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassesDao classesDao = new ClassesDao();
   
    public registerForm() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String param =  request.getParameter("param");
		
		if("student".equals(param)) {
			//查询所有班级
			List<Classes> cList = classesDao.queryAllClasses();
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("student_register.jsp").forward(request, response);
		}else if("teacher".equals(param)) {
			request.getRequestDispatcher("teacher_register.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

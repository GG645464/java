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
				
				//��ѯ��һҳ�б�
				List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
				request.setAttribute("stuList", stuList);
				
				//����һ��PageModel����
				PageModel<Student> pageModel = new PageModel<Student>();
				pageModel.setList(stuList);
				pageModel.setTotalRecords(totalRecords);
				pageModel.setPageNo(pageNo);
				request.setAttribute("pageModel", pageModel);
				request.getRequestDispatcher("index.jsp?Page=No"+pageNo).forward(request, response);
			}
		}
		else if("stuForm".equals(param)){
			//��ѯ���а༶
			List<Classes> cList = classesDao.queryAllClasses();
			request.setAttribute("cList", cList);
			request.getRequestDispatcher("addForm.jsp").forward(request, response);
		}
		else if("modify".equals(param)){
			
			int id = Integer.parseInt(request.getParameter("id"));
			Student stu = studentDao.findStudentById(id);
			request.setAttribute("stu", stu);
			
			//��ѯ���а༶
			List<Classes> cList = classesDao.queryAllClasses();
			request.setAttribute("cList", cList);
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			int totalRecords = studentDao.queryTotalRecords();
			
			//����һ��PageModel����
			PageModel<Student> pageModel = new PageModel<Student>();
			pageModel.setList(stuList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("updateForm.jsp?PageNo="+pageNo).forward(request, response);
		}
		else if("list".equals(param)){
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			
			//��ѯ�ܼ�¼��
			int totalRecords = studentDao.queryTotalRecords();
			
			//��ѯ��һҳ�б�
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			request.setAttribute("stuList", stuList);
			
			//����һ��PageModel����
			PageModel<Student> pageModel = new PageModel<Student>();
			pageModel.setList(stuList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			//ת��index.jsp
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
				request.setAttribute("result", "��ӳɹ���");
			}else {
				request.setAttribute("result", "��Ǹ����������������ʧ�ܣ�");
			}
			
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			int totalRecords = studentDao.queryTotalRecords();
			
			//����һ��PageModel����
			PageModel<Student> pageModel = new PageModel<Student>();
			pageModel.setList(stuList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			List<Classes> cList = classesDao.queryAllClasses();
			request.setAttribute("cList", cList);
			
			request.getRequestDispatcher("addForm.jsp").forward(request, response);
//			response.sendRedirect("addmin/addForm.jsp");
			//�ض���ص�index.jspҳ��
		}else if("update".equals(param)){	//����д�ᱨ��ָ���쳣!!!�� param.rquals("update")��
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Student> stuList = studentDao.finStudentsByPageNo(pageNo);
			int totalRecords = studentDao.queryTotalRecords();
			
			//����һ��PageModel����
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
			
			//�ض���ص�index.jspҳ��
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
			
			//ִ��ɾ��ѧ��
			int id = Integer.parseInt(request.getParameter("id"));
			studentDao.deleteStudent(id);
			
			//��ȡҳ��
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

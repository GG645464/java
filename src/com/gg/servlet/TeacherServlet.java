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
		
		//��ȡurl��ַ����(���ò����Ѷ��servlet���ϵ�һ��servlet��)
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
				
				//��ѯ��һҳ�б�
				List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
				request.setAttribute("teacherList", teacherList);
				
				//����һ��PageModel����
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
			
			//����һ��PageModel����
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
			
			//��ѯ�ܼ�¼��
			int totalRecords = teacherDao.queryTotalRecords();
			
			//��ѯ��һҳ�б�
			List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
			request.setAttribute("teacherList", teacherList);
			
			//����һ��PageModel����
			PageModel<Teacher> pageModel = new PageModel<Teacher>();
			pageModel.setList(teacherList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			//ת��index.jsp
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
				request.setAttribute("result", "��ӳɹ���");
			}else {
				request.setAttribute("result", "��Ǹ����������������ʧ�ܣ�");
			}
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
			int totalRecords = teacherDao.queryTotalRecords();
			
			//����һ��PageModel����
			PageModel<Teacher> pageModel = new PageModel<Teacher>();
			pageModel.setList(teacherList);
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("TaddForm.jsp").forward(request, response);
//			response.sendRedirect("addmin/addForm.jsp");
			//�ض���ص�index.jspҳ��
		}else if("update".equals(param)){	//����д�ᱨ��ָ���쳣!!!�� param.rquals("update")��
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Teacher> teacherList = teacherDao.finTeachersByPageNo(pageNo);
			int totalRecords = teacherDao.queryTotalRecords();
			
			//����һ��PageModel����
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
			
			//�ض���ص�index.jspҳ��
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
			
			//ִ��ɾ��
			int id = Integer.parseInt(request.getParameter("id"));
			teacherDao.deleteTeacher(id);
			
			//��ȡҳ��
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

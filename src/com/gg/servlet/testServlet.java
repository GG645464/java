package com.gg.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.dao.TestDao;
import com.gg.model.Student;
import com.gg.model.Test;
import com.gg.util.PageModel;
import com.gg.util.StringUtil;


@WebServlet(urlPatterns={"/admin/testServlet"},name="testServlet")
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TestDao testDao = new TestDao();
    
    public testServlet() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		if("find".equals(param)) {
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			
			String testName = request.getParameter("testName");
//			request.setAttribute("testName", testName);
			
			List<Test> testList = testDao.finTestByName(testName);
			request.setAttribute("testList", testList);
			PageModel<Test> pageModel = new PageModel<Test>();
			pageModel.setList(testList);
			pageModel.setPageNo(1);
			request.setAttribute("pageModel", pageModel);
			
			//������Ϊ��ʱ�Ĳ�ѯ
			if(StringUtil.isNoEmpty(testName)) {
				
				//ѧ���û���ѯ
				try {
					Student student = (Student)request.getSession().getAttribute("admin");
					String typeName = student.getType().getTypeName();
					
					if("ѧ��".equals(typeName)) {
						request.getRequestDispatcher("../student/stu_test_index.jsp?").forward(request, response);
					}
				//�����û���ѯ
					else {
						request.getRequestDispatcher("test_index.jsp?PageNo="+1).forward(request, response);
					}
				}catch(Exception e) {
						
					}
				
				//��������ת���
				try {
					request.getRequestDispatcher("test_index.jsp?PageNo="+1).forward(request, response);
				}catch(Exception e) {
					
				}
			//����Ϊ��ʱ�Ĳ�ѯ
			}else {	
				request.getRequestDispatcher("testServlet?param=list&PageNo="+1).forward(request, response);
			}
			
				
			}else if("modify".equals(param)){
			
			int id = Integer.parseInt(request.getParameter("id"));
			Test test = testDao.findTestById(id);
			request.setAttribute("test", test);
			
			List<Test> TestList = testDao.queryAllTests();
			request.setAttribute("TestList", TestList);
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			int totalRecords = testDao.queryTotalTestRecords();
			
			//����һ��PageModel����
			PageModel<Test> pageModel = new PageModel<Test>(TestList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("test_update.jsp?PageNo="+pageNo).forward(request, response);
		}
		else if("list".equals(param)){
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			//��ѯ�ܼ�¼��
			int totalRecords = testDao.queryTotalTestRecords();
			//��ѯ��һҳ�б�
			List<Test> testList =  testDao.finTestsByPageNo(pageNo);
			request.setAttribute("testList", testList);
			//����һ��PageModel����
			PageModel<Test> pageModel = new PageModel<Test>(testList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			try {
				Student student = (Student)request.getSession().getAttribute("admin");
				String typeName = student.getType().getTypeName();
				if("ѧ��".equals(typeName)) {
					request.getRequestDispatcher("../student/stu_test_index.jsp?PageNo="+pageNo).forward(request, response);
				}else {
					//ת��index.jsp
					request.getRequestDispatcher("test_index.jsp?PageNo="+pageNo).forward(request, response);
				}
			}catch (Exception e) {
				
			}
			try {
				request.getRequestDispatcher("test_index.jsp?PageNo="+pageNo).forward(request, response);
			}catch (Exception e) {
				
			}
		
		}
		else if("add".equals(param)){
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int time = Integer.parseInt(request.getParameter("time"));
			Test test = new Test(id,name,time);
			boolean flag = testDao.saveTest(test);
			
			if(flag) {
				request.setAttribute("result", "��ӳɹ���");
			}else {
				request.setAttribute("result", "��Ǹ����������������ʧ�ܣ�");
			}
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Test> testList = testDao.finTestsByPageNo(pageNo);
			int totalRecords = testDao.queryTotalTestRecords();
			
			//����һ��PageModel����
			PageModel<Test> pageModel = new PageModel<Test>();
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			pageModel.setList(testList);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("test_addForm.jsp").forward(request, response);
		}else if("update".equals(param)){	//����ָ���쳣�� param.rquals("update")��
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Test> testList =  testDao.finTestsByPageNo(pageNo);
			int totalRecords = testDao.queryTotalTestRecords();
			
			//����һ��PageModel����
			PageModel<Test> pageModel = new PageModel<Test>(testList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int time = Integer.parseInt(request.getParameter("time"));
			Test test = new Test(id, name,time);
			testDao.updateTest(test);
			
			//�ض���ص�index.jspҳ��
			response.sendRedirect("testServlet?param=list&PageNo="+pageNo);
			
		}else if("deleteCounts".equals(param)) {
			int id;
			String[] testId = request.getParameterValues("testName");
			for(String tid:testId) {
				id = Integer.parseInt(tid);
				testDao.deleteTest(id);
			}
			response.sendRedirect("testServlet?param=list&PageNo="+1);
		
		}else if("delete".equals(param)){
			
			//ִ��ɾ��
			int id = Integer.parseInt(request.getParameter("id"));
			testDao.deleteTest(id);
			
			//��ȡҳ��
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			int totalRecords = testDao.queryTotalTestRecords();
			
			int totalPages = totalRecords%5==0?totalRecords/5:(totalRecords/5+1);
			if(pageNo>totalPages){
				pageNo--;
			}
			
			response.sendRedirect("testServlet?param=list&PageNo="+pageNo);
			
		}else{
			
			response.sendRedirect("other.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

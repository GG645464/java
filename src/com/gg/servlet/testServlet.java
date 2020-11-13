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
			
			//参数不为空时的查询
			if(StringUtil.isNoEmpty(testName)) {
				
				//学生用户查询
				try {
					Student student = (Student)request.getSession().getAttribute("admin");
					String typeName = student.getType().getTypeName();
					
					if("学生".equals(typeName)) {
						request.getRequestDispatcher("../student/stu_test_index.jsp?").forward(request, response);
					}
				//其他用户查询
					else {
						request.getRequestDispatcher("test_index.jsp?PageNo="+1).forward(request, response);
					}
				}catch(Exception e) {
						
					}
				
				//其他不跳转情况
				try {
					request.getRequestDispatcher("test_index.jsp?PageNo="+1).forward(request, response);
				}catch(Exception e) {
					
				}
			//参数为空时的查询
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
			
			//传递一个PageModel对象
			PageModel<Test> pageModel = new PageModel<Test>(TestList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("test_update.jsp?PageNo="+pageNo).forward(request, response);
		}
		else if("list".equals(param)){
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			//查询总记录数
			int totalRecords = testDao.queryTotalTestRecords();
			//查询第一页列表
			List<Test> testList =  testDao.finTestsByPageNo(pageNo);
			request.setAttribute("testList", testList);
			//传递一个PageModel对象
			PageModel<Test> pageModel = new PageModel<Test>(testList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			try {
				Student student = (Student)request.getSession().getAttribute("admin");
				String typeName = student.getType().getTypeName();
				if("学生".equals(typeName)) {
					request.getRequestDispatcher("../student/stu_test_index.jsp?PageNo="+pageNo).forward(request, response);
				}else {
					//转发index.jsp
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
				request.setAttribute("result", "添加成功！");
			}else {
				request.setAttribute("result", "抱歉，您的添加有误，添加失败！");
			}
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Test> testList = testDao.finTestsByPageNo(pageNo);
			int totalRecords = testDao.queryTotalTestRecords();
			
			//传递一个PageModel对象
			PageModel<Test> pageModel = new PageModel<Test>();
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			pageModel.setList(testList);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("test_addForm.jsp").forward(request, response);
		}else if("update".equals(param)){	//报空指针异常【 param.rquals("update")】
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Test> testList =  testDao.finTestsByPageNo(pageNo);
			int totalRecords = testDao.queryTotalTestRecords();
			
			//传递一个PageModel对象
			PageModel<Test> pageModel = new PageModel<Test>(testList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			int time = Integer.parseInt(request.getParameter("time"));
			Test test = new Test(id, name,time);
			testDao.updateTest(test);
			
			//重定向回到index.jsp页面
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
			
			//执行删除
			int id = Integer.parseInt(request.getParameter("id"));
			testDao.deleteTest(id);
			
			//获取页数
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

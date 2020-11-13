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
import com.gg.util.PageModel;

@WebServlet("/admin/ClassesServlet")
public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassesDao classDao = new ClassesDao();
    public ClassesServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("param");
		if("modify".equals(param)){
			
			int id = Integer.parseInt(request.getParameter("id"));
			Classes classes = classDao.findClassById(id);
			request.setAttribute("classes", classes);
			
			List<Classes> cList = classDao.queryAllClasses();
			request.setAttribute("cList", cList);
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			int totalRecords = classDao.queryTotalClassRecords();
			
			//传递一个PageModel对象
			PageModel<Classes> pageModel = new PageModel<Classes>(cList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			request.getRequestDispatcher("classUpdate.jsp?PageNo="+pageNo).forward(request, response);
		}
		else if("list".equals(param)){
				
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			//查询总记录数
			int totalRecords = classDao.queryTotalClassRecords();;
			//查询第一页列表
			List<Classes> classList =  classDao.finClassesByPageNo(pageNo);
			request.setAttribute("clsList", classList);
			//传递一个PageModel对象
			PageModel<Classes> pageModel = new PageModel<Classes>(classList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			//转发index.jsp
			request.getRequestDispatcher("Cindex.jsp?PageNo="+pageNo).forward(request, response);
		}
		else if("add".equals(param)){
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			Classes classes = new Classes(id,name);
			classDao.saveClasses(classes);
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			int totalRecords = classDao.queryTotalClassRecords();
			
			//传递一个PageModel对象
			PageModel<Classes> pageModel = new PageModel<Classes>();
			pageModel.setTotalRecords(totalRecords);
			pageModel.setPageNo(pageNo);
			request.setAttribute("pageModel", pageModel);
			
			response.sendRedirect("ClassesServlet?param=list&PageNo="+pageNo);
			//重定向回到index.jsp页面
		}else if("update".equals(param)){	//这样写会报空指针异常!!!【 param.rquals("update")】
			
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			List<Classes> classList =  classDao.finClassesByPageNo(pageNo);
			int totalRecords = classDao.queryTotalClassRecords();
			
			//传递一个PageModel对象
			PageModel<Classes> pageModel = new PageModel<Classes>(classList,totalRecords,pageNo);
			request.setAttribute("pageModel", pageModel);
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			Classes cls = new Classes(id, name);
			classDao.updateClasses(cls);
			
			//重定向回到index.jsp页面
			response.sendRedirect("ClassesServlet?param=list&PageNo="+pageNo);
			
		}else if("delete".equals(param)){
			
			//执行删除学生
			int id = Integer.parseInt(request.getParameter("id"));
			classDao.deleteClasses(id);
			
			//获取页数
			int pageNo = Integer.parseInt(request.getParameter("PageNo"));
			int totalRecords = classDao.queryTotalClassRecords();
			
			int totalPages = totalRecords%5==0?totalRecords/5:(totalRecords/5+1);
			if(pageNo>totalPages){
				pageNo--;
			}
			
			response.sendRedirect("ClassesServlet?param=list&PageNo="+pageNo);
			
		}else{
			
			response.sendRedirect("other.jsp");
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

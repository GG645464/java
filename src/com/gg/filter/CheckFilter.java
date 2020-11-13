package com.gg.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gg.model.Admin;
import com.gg.model.Student;
import com.gg.model.Teacher;

/**
 * Ȩ��У��Ĺ�����
 * @author Administrator
 *
 */
//Filter���������ȼ�����Servlet
//@WebFilter(urlPatterns={"/admin/*"},filterName="CheckFilter")
@WebFilter(urlPatterns={"/*"},filterName="CheckFilter")
public class CheckFilter implements Filter{
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//HttpServletRequest�̳���ServletRequest
		//����httpRequest��httpResponse��Ϊ��ת�Ͷ���
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		//�����Ƿ��¼�ˣ��治����admin�������
		Object object = httpRequest.getSession().getAttribute("admin");
//		System.out.println("servletPath:"+httpRequest.getServletPath());
//		System.out.println("ContextPath:"+httpRequest.getContextPath());
		String path = httpRequest.getServletPath();
		
		if(path.equals("/registerForm")||path.equals("/login")||path.equals("/registerServlet")||path.equals("/login.jsp")||path.equals("/register.jsp")||path.equals("/student_register.jsp")||path.equals("/teacher_register.jsp")) {
			chain.doFilter(request, response);	
		}else {

			if(object != null){	
				//���ݸ���һ��������
				chain.doFilter(request, response);	
			}else{	
				httpResponse.sendRedirect("../login.jsp");
			}
		}
		
//		chain.doFilter(request, response);	
	}
	@Override
	public void destroy() {
	}
	
}

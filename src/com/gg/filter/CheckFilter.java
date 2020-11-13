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
 * 权限校验的过滤器
 * @author Administrator
 *
 */
//Filter过滤器优先级高于Servlet
//@WebFilter(urlPatterns={"/admin/*"},filterName="CheckFilter")
@WebFilter(urlPatterns={"/*"},filterName="CheckFilter")
public class CheckFilter implements Filter{
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//HttpServletRequest继承自ServletRequest
		//所以httpRequest和httpResponse都为上转型对象
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		//查找是否登录了，存不存在admin这个属性
		Object object = httpRequest.getSession().getAttribute("admin");
//		System.out.println("servletPath:"+httpRequest.getServletPath());
//		System.out.println("ContextPath:"+httpRequest.getContextPath());
		String path = httpRequest.getServletPath();
		
		if(path.equals("/registerForm")||path.equals("/login")||path.equals("/registerServlet")||path.equals("/login.jsp")||path.equals("/register.jsp")||path.equals("/student_register.jsp")||path.equals("/teacher_register.jsp")) {
			chain.doFilter(request, response);	
		}else {

			if(object != null){	
				//传递给下一个过滤器
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

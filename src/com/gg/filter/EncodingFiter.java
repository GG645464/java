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
/**
 * ÉèÖÃ±àÂëµÄ¹ýÂËÆ÷
 * @author Administrator
 *
 */

@WebFilter(urlPatterns={"/*"},filterName="EncodingFiter")
public class EncodingFiter implements Filter {

	
	private String encoding = null;
    public EncodingFiter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig Config) throws ServletException {
		
		encoding = Config.getInitParameter("encoding");
	}

}

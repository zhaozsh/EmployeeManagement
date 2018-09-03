package com.startdima.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器实现登陆拦截，未登陆用户的所有操作都会跳转至登陆页
 * @author zhaozsh
 * @date 2018年8月27日
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = {"/*"})
public class LoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req= (HttpServletRequest) request;
		HttpServletResponse resp=  (HttpServletResponse) response;
		String requestPath = req.getServletPath();
        // 判断用户是否登录
        HttpSession session = req.getSession();
        // 没有登录，且访问页面不是登录也页面
        if(session.getAttribute("loginName") == null && !requestPath.endsWith("index.html") 
        		&& !requestPath.endsWith("LoginServlet") && !requestPath.endsWith("css")){
            // 转到登录页面
        	resp.sendRedirect("index.html");
        }else{
            // 放行
            chain.doFilter(request,response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

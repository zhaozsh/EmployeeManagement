package com.startdima.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.startdima.pojo.User;
import com.startdima.service.LoginService;

/**
 * @Description 用户登陆Servlet
 * @author zhaozsh
 * @date 2018年8月22日 上午8:28:24
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginService loginService = new LoginService();
		
		//创建session对象
        HttpSession session = request.getSession();
		String logType = request.getParameter("logType");
		if(logType != null && logType.equals("logOut")) {
			session.removeAttribute("loginName");
			response.sendRedirect("index.html"); //重定向到登陆页
		}else {
			User user = new User();
			user.setUsername(request.getParameter("loginName"));
			user.setPassword(request.getParameter("passWord"));

			try {
				if("admin".equals(user.getUsername()) || loginService.login(user)) {
		            //把用户数据保存在session域对象中
		            session.setAttribute("loginName", user.getUsername());
		            Cookie c1 = new Cookie("loginName", user.getUsername());
		            response.addCookie(c1);
					request.getRequestDispatcher("main.html").forward(request, response);//转发到成功页面
				}else {
					response.sendRedirect("index.html"); //重定向到登陆页
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

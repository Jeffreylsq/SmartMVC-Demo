package cn.tedu.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.UserDao;

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. dealing with garble code
		request.setCharacterEncoding("utf-8");
		//2. receive parameters
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String remname=request.getParameter("remname");
		
		//Determine if username is empty
		if(username==null || "".equals(username)) {
			request.setAttribute("msg", "Username can not be empty");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
        //Check username and password are exist
		UserDao dao=new UserDao();
		boolean flag=dao.getUserByUAP(username, password);

        //return result		
		if(flag) {
			 
			//add user login status in session
			request.getSession().setAttribute("user", username);

           // if user check the checkbox which remember user			
			if("true".equals(remname)) {
				// define cookie to save username
				Cookie cookie=new Cookie("remname",URLEncoder.encode(username, "utf-8"));
				// set alive time
				cookie.setMaxAge(60*60*24*7);// 7��
				// set path
				cookie.setPath(request.getContextPath());
				
				response.addCookie(cookie);
			}else {
				// delete Cookie
				Cookie cookie=new Cookie("remname","");
				cookie.setMaxAge(0);
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			request.setAttribute("message", "Login successful");
			request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp").forward(request, response);
		}else {
			//Fail
			request.setAttribute("msg", "Login Fail");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}			
		
	}

}

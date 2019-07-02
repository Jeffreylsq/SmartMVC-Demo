package cn.tedu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 * @author Tianyu Wei
 */
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
		HttpServletResponse response) throws ServletException, IOException {
		//response.setStatus(302); 
		//response.setHeader("Location", 
		//		"http://doc.tedu.cn");
		
		response.sendRedirect("http://doc.tedu.cn"); 
	}

}






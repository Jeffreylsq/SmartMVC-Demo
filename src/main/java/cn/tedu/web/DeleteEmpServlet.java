package cn.tedu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.EmpDao;

/**
 * ´¦ÀíÉ¾³ýÔ±¹¤ÇëÇó
 */
public class DeleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("empno");
		Integer empno = Integer.parseInt(str);
		EmpDao dao = new EmpDao();
		int n = dao.deleteEmp(empno);
		if(n==1) {
			System.out.println("É¾³ý³É¹¦£¡");
			response.sendRedirect("listEmp"); 
			return;
		}else {
			System.out.println("É¾³ýÊ§°Ü£¡");
			request.setAttribute("message", "É¾³ýÊ§°Ü£¡");
			request.getRequestDispatcher(
					"/WEB-INF/jsp/msg.jsp");
		}
	}

}





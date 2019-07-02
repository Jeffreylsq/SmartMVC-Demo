package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.EmpDao;
import cn.tedu.entity.Emp;

/**
 * 利用Servlet与JSP协作显示员工列表
 */
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		EmpDao dao = new EmpDao();
		List<Emp> list = dao.findAll();
		
		//利用request对象向JSP传送计算结果
		request.setAttribute("list", list); 
		
		//转发到JSP页面显示结果 list-emp.jsp
		RequestDispatcher rd=request.getRequestDispatcher(
				"/WEB-INF/jsp/list.jsp");
		rd.forward(request, response); 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}






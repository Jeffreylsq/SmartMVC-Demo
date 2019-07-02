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
 * @author Tianyu Wei
 */
public class ListEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		EmpDao dao = new EmpDao();
		List<Emp> list = dao.findAll();
		
		//����request������JSP���ͼ�����
		request.setAttribute("list", list); 
		
		//ת����JSPҳ����ʾ��� list-emp.jsp
		RequestDispatcher rd=request.getRequestDispatcher(
				"/WEB-INF/jsp/list.jsp");
		rd.forward(request, response); 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}






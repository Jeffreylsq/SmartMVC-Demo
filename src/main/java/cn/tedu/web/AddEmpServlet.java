package cn.tedu.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.EmpDao;
import cn.tedu.entity.Emp;

/**
 * Dynamic add emp form
 * @author Tianyu Wei
 */
public class AddEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EmpDao dao = new EmpDao();
		//invoke findAll method find all employee INFO and put into list<Emp>
		List<Emp> managers = dao.findAll();
		//save list in "managers"
		request.setAttribute("managers", managers);
		
		//invoke findJobs(),and put all jobs into list<String>
		List<String> jobs=dao.findJobs();
		request.setAttribute("jobs", jobs);
		request.getRequestDispatcher(
				"/WEB-INF/jsp/add-emp.jsp")
				.forward(request, response);
	}

}






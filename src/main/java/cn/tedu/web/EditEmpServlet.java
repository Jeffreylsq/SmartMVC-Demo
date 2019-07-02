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
 * 显示编辑Emp信息的页面
 */
public class EditEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//获取员工编号
		String str = request.getParameter("empno");
		Integer empno = Integer.parseInt(str);
		//根据员工编号，查询员工信息
		EmpDao dao = new EmpDao();
		Emp emp = dao.findEmpByEmpno(empno);
		List<String> jobs = dao.findJobs();
		List<Emp> managers = dao.findAll();
		
		//利用request对象将emp传送到JSP页面中
		request.setAttribute("emp", emp); 
		request.setAttribute("jobs", jobs); 
		request.setAttribute("managers", managers); 
			
		//转发到JSP显示当前被编辑员工信息
		request.getRequestDispatcher(
				"/WEB-INF/jsp/edit-emp.jsp")
				.forward(request, response); 
	}
}









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
 * ��ʾ�༭Emp��Ϣ��ҳ��
 */
public class EditEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//��ȡԱ�����
		String str = request.getParameter("empno");
		Integer empno = Integer.parseInt(str);
		//����Ա����ţ���ѯԱ����Ϣ
		EmpDao dao = new EmpDao();
		Emp emp = dao.findEmpByEmpno(empno);
		List<String> jobs = dao.findJobs();
		List<Emp> managers = dao.findAll();
		
		//����request����emp���͵�JSPҳ����
		request.setAttribute("emp", emp); 
		request.setAttribute("jobs", jobs); 
		request.setAttribute("managers", managers); 
			
		//ת����JSP��ʾ��ǰ���༭Ա����Ϣ
		request.getRequestDispatcher(
				"/WEB-INF/jsp/edit-emp.jsp")
				.forward(request, response); 
	}
}









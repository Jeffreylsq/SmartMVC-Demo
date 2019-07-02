package cn.tedu.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.EmpDao;
import cn.tedu.entity.Emp;

/**
 *  @author Tianyu Wei
 */
public class UpdateEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			String ename = request.getParameter("ename");
			String job = request.getParameter("job");
			String strMgr = request.getParameter("mgr");
			int mgr = Integer.parseInt(strMgr);
			String strHiredate = 
					request.getParameter("hiredate");
			SimpleDateFormat fmt=new SimpleDateFormat(
					"yyyy-MM-dd");
			Date date = fmt.parse(strHiredate);
			java.sql.Date hiredate=new java.sql.Date(
					date.getTime());
			String strSal = request.getParameter("sal");
			double sal = Double.parseDouble(strSal);
			String strComm = request.getParameter("comm");
			double comm=Double.parseDouble(strComm);
			String strDeptno=request.getParameter("deptno");
			int deptno=Integer.parseInt(strDeptno);
			
			//���������¹��������µĹ�����ӵ�Ա����Ϣ
			String addJob=request.getParameter("addJob");
			if(addJob!=null && !addJob.isEmpty()) {
				job = addJob;
			}
		
			String sempno = request.getParameter("empno");
			Integer empno = Integer.parseInt(sempno);
					
			Emp emp = new Emp(empno, ename, job, 
					mgr, hiredate, sal, comm, deptno);
			
			EmpDao dao = new EmpDao();
			int n = dao.updateEmp(emp);
			if(n==1) {
				response.sendRedirect("listEmp");
				return;
			} else {
				request.setAttribute("msg", "����ʧ��"); 
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", 
					"����ʧ��"+e.getMessage()); 
		}
		request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp")
			.forward(request, response); 
	}
}








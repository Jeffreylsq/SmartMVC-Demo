package mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.tedu.dao.EmpDao;
import cn.tedu.entity.Emp;

public class EmpController {
	
	private EmpDao dao=new EmpDao();
	
	@RequestMapping(value = "/delEmp.do")
	public String delEmp(HttpServletRequest request) {
		
		String empnoStr=request.getParameter("empno");
		if(empnoStr!=null) {
			Integer empno=Integer.parseInt(empnoStr);
			int n=dao.deleteEmp(empno);
			if(n==1) {// delete successful
				return "redirect:listEmp.do";
			}else {
				request.setAttribute("message", "ɾ��ʧ�ܣ�");
				return "msg";
			}
		}
		return null;
	}
	
	/**
	 * list all method and return path of forward
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listEmp.do")
	public String listEmp(HttpServletRequest request) {
		List<Emp> list=dao.findAll();
		request.setAttribute("list",list);
		
		return "list";
	}
	
	@RequestMapping(value="/addEmp.do")
	public String addEmp(HttpServletRequest request) {
		//Find all emp INFO as table 
		List<Emp> managers = dao.findAll();
		request.setAttribute("managers", managers);
		
		//Find all jobs as job table
		List<String> jobs=dao.findJobs();
		request.setAttribute("jobs", jobs);
		
		
		return "add-emp";
	}
	

}

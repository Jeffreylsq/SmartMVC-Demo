package test;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.dao.EmpDao;
import cn.tedu.dao.UserDao;
import cn.tedu.entity.Emp;
import cn.tedu.entity.User;
import mvc.Handler;
import mvc.HandlerMapping;

public class TestCase {
	UserDao dao=new UserDao();
	
	@Test
	public void testParseController() {
		HandlerMapping hm=new HandlerMapping();
		try {
			Class cz=Class.forName("mvc.EmpController");
			hm.parseController(cz);
			Map<String, Handler> map=hm.getMap();
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetUserByUAP() {
		boolean flag=dao.getUserByUAP("Tom", "1234");
		System.out.println("flag="+flag);
	}
	
	@Test
	public void testSaveUser() {
		User user=new User(-1, "admin", "123");
		boolean flag=dao.saveUser(user);
		System.out.println("flag="+flag);
	}
	
	
	
	@Test
	public void testSaveEmp() {
		long now = System.currentTimeMillis();
		Emp emp=new Emp(8000, "Tom", "¶Ë²èµÄ", 7839, 
				new Date(now), 2000.0, 100.0, 10); 
		EmpDao dao = new EmpDao();
		dao.saveEmp(emp); 
	}
	
	@Test
	public void testFindJobs() {
		EmpDao dao = new EmpDao();
		List<String> jobs=dao.findJobs();
		System.out.println(jobs); 
	}
	
	@Test
	public void testFindMaxEmpno() {
		EmpDao dao = new EmpDao();
		Integer no = dao.findMaxEmpno();
		System.out.println(no); 
	}
	
	@Test
	public void testDeleteEmp() {
		int empno = 9001;
		EmpDao dao = new EmpDao();
		int n = dao.deleteEmp(empno);
		System.out.println(n); 
	}
	
	@Test
	public void testFindEmpByEmpno() {
		EmpDao dao = new EmpDao();
		Emp emp = dao.findEmpByEmpno(9000);
		System.out.println(emp); 
	}
	
	
	@Test
	public void testUpdateEmp() {
		EmpDao dao = new EmpDao();
		Emp emp = dao.findEmpByEmpno(9000);
		emp.setEname("ÐÜ´ó"); 
		dao.updateEmp(emp);
	}
}














package mvc;

import javax.servlet.http.HttpServletRequest;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

public class UserController {
	
	private UserDao dao=new UserDao();
	
	@RequestMapping(value="/addUser.do")
	public String addUser(HttpServletRequest request) {
		return "register";
	}
	
	@RequestMapping(value="/saveUser.do")
	public String saveUser(HttpServletRequest request) {
		// receive form parameters
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//determine if Username is empty 
		if(username==null || "".equals(username)) {
			request.setAttribute("msg", "Can not be empty");
			return "register";
		}
         // Determine if user is exist
		if(dao.getUserByUsername(username)) {
			request.setAttribute("msg", "User exist");
			return "register";
		}
		//id is auto_increment, can put any value
		User user=new User(-1, username, password);
		
		//save user success: true
		boolean flag=dao.saveUser(user);
		
		String msg=flag?"Register Successful":"Register Fail";
		request.setAttribute("message", msg);
		
		return "msg";
	}

}

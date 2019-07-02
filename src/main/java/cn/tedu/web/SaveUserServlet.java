package cn.tedu.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

/**
 * 
 * @author Tianyu Wei
 *
 */
public class SaveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Dealing with garbled problems
		response.setCharacterEncoding("utf-8");
		// 2. receive parameters
		String username=request.getParameter("username");
		String password=request.getParameter("password");

		// Determine username is null
		if(username==null || "".equals(username)) {
			// �û���Ϊ��
			request.setAttribute("msg", "Username cannot be empty");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
				.forward(request, response);
			return;
		}
		
		UserDao dao=new UserDao();

		//Determine user is exist or not
		boolean hasUsername=dao.getUserByUsername(username);
		if(hasUsername) {
			request.setAttribute("msg", "Username exist");
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
				.forward(request, response);
			return;
		}
		
		
		
		
		// 4. ִ���߼�
		User user=new User(-1, username, password);
		boolean flag=dao.saveUser(user);
		
		// 5. ������Ӧ����
		String message=flag?"Register successful":"Register fail";
		System.out.println("text " + message);
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("/WEB-INF/jsp/msg.jsp")
			.forward(request, response);

	}

}

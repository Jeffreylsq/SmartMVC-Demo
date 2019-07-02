package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		//Determine session of user whether login or not
		HttpSession session=req.getSession(false);
		if(session==null || session.getAttribute("user")==null) {
			// if not redirect to login.jsp
			((HttpServletResponse)response).sendRedirect(req.getContextPath()+"/login.jsp");
		}else {
			// if login , let it go
			chain.doFilter(request, response);
		}
	}
  
    public LoginFilter() {
    }

	public void destroy() {
	}


	public void init(FilterConfig fConfig) throws ServletException {
	}

}

package mvc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author Tianyu Wei
 *
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping hm;
	
	@Override
	public void init() throws ServletException {
		// Initialization HandlerMapping
		hm=new HandlerMapping();

		//Obtain ServletConfig object
		ServletConfig config=getServletConfig();
		String fileName=config.getInitParameter("config");
		try {
			// Read mvc.xml controller package name and class name
			SAXReader reader=new SAXReader();
            //Profile inputStream by using getClassLoader()
			ClassLoader cl=this.getClass().getClassLoader();
			InputStream is=cl.getResourceAsStream(fileName);
			// Dom4j parse document
			Document document=reader.read(is);
			Element rootEle=document.getRootElement();
			// obtain all elements which tage name is bean
			List<Element> list=rootEle.elements("bean");
			for(Element ele:list) {
				// get bean class attribute and instantiate it
				String value=ele.attribute("class").getValue();
				Class cz=Class.forName(value);
				hm.parseController(cz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡServletContext����
		ServletContext sc=getServletContext();
		// ��ȡweb.xml�����õĳ�ʼ������
		String encode=sc.getInitParameter("encode");
		
		// 1. ��������
		// ����Post��������
		request.setCharacterEncoding(encode);
		// ������Ӧ����
		response.setContentType("text/html;charset="+encode);
		
		// �����ӿ�������Ӧ�ķ���
		// ��ȡ�û������url
		// http://localhost:8080/Servlet11/admin/listEmp.do
		// /Servlet11/admin/listEmp.do
		String url=request.getRequestURI();
		// ��ȡ��Դ·��
		url=url.substring(request.getContextPath().length());
		
		try {
			// ��HandlerMapping�в���url��Ӧ��Handler
			Handler handler=hm.getHandler(url);
			
			// ���findM��Ϊnull��˵���ӿ������������û�����url���Ӧ�ķ���
			if(handler!=null) {
				// ������ø÷���
				String path=(String)handler.execute(request);
				
				// �ж���ת�������ض���
				if(path.startsWith("redirect:")) {// �ض���
					// "redirect:listEmp.do"
					path=path.substring("redirect:".length());
					// "listEmp.do   http://www.tmooc.cn"
					if(path.startsWith("http")) {
						response.sendRedirect(path);
					}else {
						path=request.getContextPath()+"/"+path;
						response.sendRedirect(path);
					}
				}else {//ת��
					// ���������ص�·��ƴ�ӳ���������Դ·��
					// list ->/WEB-INF/jsp/list.jsp
					path="/WEB-INF/jsp/"+path+".jsp";
					// ����ת��
					request.getRequestDispatcher(path)
					.forward(request, response);
				}
			}else {
				// ���û�ҵ��÷���������404��Ӧ״̬��
				response.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;


/**
 * Handler
 * 
 * 
 *   Keep reflect relation between sub-controller and method object
 * 
 *  offer function execute() that invoke method by using reflect 
 * 
 */
public class Handler {
	
	private Method method;
	private Object controller;
	
	public Object execute(HttpServletRequest request) throws Exception {
		Object result=method.invoke(controller, request);
		return result;
	}
	
	
	public Handler() {}

	public Handler(Method method, Object controller) {
		this.method = method;
		this.controller = controller;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object getController() {
		return controller;
	}

	public void setController(Object controller) {
		this.controller = controller;
	}

	@Override
	public String toString() {
		return "Handler [method=" + method + ", controller=" + controller + "]";
	}

}

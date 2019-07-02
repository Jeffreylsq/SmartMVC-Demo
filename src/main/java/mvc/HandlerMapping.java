package mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *   Manage Map<String url, Handler handler>
 * 
 * offer reflect iterate sub-controller
 * save url and controller instantiation into map
 * 
 * offer searching method by using url
 * 
 */
public class HandlerMapping {
	
	private Map<String, Handler> map=new HashMap<String, Handler>();
	
	public void parseController(Class cz) throws Exception {
		Method[] ms=cz.getDeclaredMethods();
		Object controller=cz.newInstance();
		for(Method m:ms) {
			RequestMapping rm=
					m.getAnnotation(RequestMapping.class);
			if(rm!=null) {
				String url=rm.value();
				Handler handler=new Handler();
				handler.setMethod(m);
				handler.setController(controller);
				map.put(url, handler);
			}
		}
	}
	
	public Map<String, Handler> getMap(){
		return this.map;
	}
	
	/**
	 * Search corresponding method to user requst url
	 * @param url
	 * @return
	 */
	public Handler getHandler(String url) {
		return map.get(url);
	}
	

}





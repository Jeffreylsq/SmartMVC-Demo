package mvc;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @author Tianyu Wei
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {
	
    // return annotation attribute value
	String value();

}





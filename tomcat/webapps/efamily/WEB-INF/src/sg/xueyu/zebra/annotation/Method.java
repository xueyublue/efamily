package sg.xueyu.zebra.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used set request method to Method
 * 
 * @Date 	17-NOV-2017
 * @author 	DARREN
 * @version 1.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Method {

	public enum RequestMethod {
		GET, POST
	};

	RequestMethod value() default RequestMethod.GET;

}

package sg.xueyu.zebra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Method {

	public enum RequestMethod {
		GET, PUT, POST, DELETE
	};

	RequestMethod value() default RequestMethod.GET;

}

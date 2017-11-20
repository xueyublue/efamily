package sg.xueyu.zebra.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to initialize configurations
 * The classes that annotated by this annotation will be initialized when RootController is started
 * 
 * @Date 	17-NOV-2017
 * @author 	DARREN
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {

	String value() default "";

}

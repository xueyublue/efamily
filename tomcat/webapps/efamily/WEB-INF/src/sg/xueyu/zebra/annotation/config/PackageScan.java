package sg.xueyu.zebra.annotation.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to set scanning packages
 * 
 * @Date 	17-NOV-2017
 * @author 	DARREN
 * @version 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PackageScan {
	
	String[] value();
	
}

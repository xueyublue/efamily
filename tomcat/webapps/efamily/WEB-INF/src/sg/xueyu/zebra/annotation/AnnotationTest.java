package sg.xueyu.zebra.annotation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import sg.xueyu.zebra.util.PackageScanner;

public class AnnotationTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		PackageScanner packageScanner = new PackageScanner("*");
		for (String string : packageScanner.getFullyQualifiedClassNameList()) {
			System.out.println(string);
		}
		
		try {
			Class<?> c = Class.forName("sg.xueyu.zebra.annotation.MyClass");

			if (c.isAnnotationPresent(Path.class)) {
				Path path = c.getAnnotation(Path.class);
				System.out.println(path.value());
			}

			Method[] methods = c.getMethods();

			for (Method method : methods) {
				if (method.isAnnotationPresent(Path.class)) {
					Path path = method.getAnnotation(Path.class);
					System.out.println(path.value());
				}
			}

		} catch (Exception e) {
		}
	}

}

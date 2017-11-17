package sg.xueyu.zebra.temp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import sg.xueyu.zebra.core.Action;
import sg.xueyu.zebra.core.ActionContainer;
import sg.xueyu.zebra.core.ActionScanner;
import sg.xueyu.zebra.core.PackageScanner;

public class AnnotationTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		PackageScanner packageScanner = new PackageScanner("*");
		List<String> nameList = packageScanner.getFullyQualifiedClassNameList();

		ActionScanner actionScanner = new ActionScanner(nameList);
		ActionContainer actionContainer = actionScanner.scan();

		for (Action action : actionContainer.getAllActions()) {
			Object instance = action.getActionClass().newInstance();
			System.out.println(action.getMethod().invoke(instance));
		}
	}

}

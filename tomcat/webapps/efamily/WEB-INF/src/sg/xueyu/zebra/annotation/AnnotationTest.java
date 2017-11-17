package sg.xueyu.zebra.annotation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import sg.xueyu.zebra.core.Action;
import sg.xueyu.zebra.core.ActionContainer;
import sg.xueyu.zebra.core.ActionScanner;
import sg.xueyu.zebra.core.PackageScanner;

public class AnnotationTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		PackageScanner packageScanner = new PackageScanner("*");
		List<String> nameList = packageScanner.getFullyQualifiedClassNameList();

		ActionScanner actionScanner = new ActionScanner(nameList);
		ActionContainer actionContainer = actionScanner.scan();

		for (Action action : actionContainer.getAllActions()) {
			System.out.println(action.getPath());
			System.out.println(action.getMethod());
			System.out.println(action.getActionClass());
			System.out.println(action.getMethod());
		}
	}

}

package sg.xueyu.zebra.annotation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import sg.xueyu.zebra.core.ActionScanner;
import sg.xueyu.zebra.core.PackageScanner;

public class AnnotationTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		PackageScanner packageScanner = new PackageScanner("*");
		List<String> nameList = packageScanner.getFullyQualifiedClassNameList();

		ActionScanner actionScanner = new ActionScanner(nameList);
		List<String> actionList = actionScanner.getActionClassNameList();

		for (String string : actionList) {
			System.out.println(string);
		}
	}

}

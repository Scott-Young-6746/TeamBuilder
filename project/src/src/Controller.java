import java.io.FileNotFoundException;

import ca.mun.managment.Project;

public class Controller {

	static String classInput;
	static String className;
	static int groupSize;
  
	public static void main(String[] args) throws FileNotFoundException {
    	GUI userGui = new GUI();
    	userGui.run();  
    }
 
	public static void createProject() throws FileNotFoundException {
		new Project(className, classInput, groupSize);
	}
	
	//Set the static variable String (the String of all students' names and student numbers)
	public static void setString(String str) {
		classInput = str;
	}
	
	//Set the static variable groupSize
	public static void setGroupSize(int i) {
		groupSize = i;
	}
	
	//Set the name of the class
	public static void setClassName(String str) {
		className = str;
	}
}
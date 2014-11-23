import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ca.mun.team.*;
import ca.mun.managment.Project;

public class Controller {

	static String classInput;
	static String className;
	static int groupSize;
	static Project project;
  
	public static void main(String[] args) throws FileNotFoundException {
    	GUI userGui = new GUI();
    	userGui.run();
    }
 
	public static void createProject() throws FileNotFoundException {
		project = new Project(className, classInput, groupSize);
	}
	
	public static List<Team> generateGroups(){
        project.constructTeams();
        return project.getTeams();
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
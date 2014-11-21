package ca.mun.managment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ca.mun.team.ProjectMember;

public class StudentListGenerator {
	
	ArrayList<ProjectMember> members = new ArrayList<ProjectMember>();
	
	public StudentListGenerator(String s) throws FileNotFoundException{
	
		String classInput = s;
		Scanner input = new Scanner(classInput);

		//loops through and creates member objects from the text input
		while(input.hasNext()) {
		    String nextLine = input.nextLine();
		    members.add(new ProjectMember(nextLine));
		    
		}
		input.close();
	}
	
	
	
	//returns the arrayList of projectMembers
	public List<ProjectMember> getList() {
		return members;
	}

}

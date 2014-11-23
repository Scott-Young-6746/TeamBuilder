package ca.mun.util;

import ca.mun.team.ProjectMember;

public class StudentRelationEdge {
	private StudentRelationGraph s1;
	private StudentRelationGraph s2;
	
	public StudentRelationEdge(StudentRelationGraph s1, StudentRelationGraph s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	public StudentRelationGraph getOtherStudent(StudentRelationGraph s){
		StudentRelationGraph out;
		if(s1.equals(s)){
			out = s2;
		}
		else{
			out = s1;
		}
		return out;
	}
	
	public ProjectMember getOtherStudent(ProjectMember s){
		ProjectMember out;
		if(s1.getStudent().equals(s)){
			out = s2.getStudent();
		}
		else{
			out = s1.getStudent();
		}
		return out;
	}
	public boolean contains(ProjectMember s){
		if(s1.getStudent().equals(s)){
			return true;
		}
		else if(s2.getStudent().equals(s)){
			return true;
		}
		else{
			return false;
		}
	}
}




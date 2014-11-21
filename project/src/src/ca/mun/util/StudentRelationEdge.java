package ca.mun.util;

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
}

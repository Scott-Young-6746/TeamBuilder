package ca.mun.util;

import java.util.ArrayList;

import ca.mun.team.ProjectMember;
import ca.mun.team.Student;

public class StudentRelationGraph {
	private ArrayList<StudentRelationEdge> edges;
	private Student student;
	public StudentRelationGraph(Student student){
		this.student = student;
		this.edges = new ArrayList<StudentRelationEdge>();
	}
	public void addRelation(StudentRelationGraph s){
		this.edges.add(new StudentRelationEdge(this, s));
	}
	public ArrayList<StudentRelationGraph> getPotentialPartners(){
		ArrayList<StudentRelationGraph> potentialPartners = new ArrayList<StudentRelationGraph>();
		for(StudentRelationEdge edge : edges){
			//Can I get your Other student please?
			potentialPartners.add(edge.getOtherStudent(this));
			//Oh for sure!
		}
		return potentialPartners;
	}
	public ArrayList<StudentRelationEdge> getEdges(){
		return edges;
	}
	public ProjectMember getStudent(){
		return this.student;
	}
}

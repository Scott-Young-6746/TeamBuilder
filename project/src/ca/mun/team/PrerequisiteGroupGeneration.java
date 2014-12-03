package ca.mun.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.mun.util.StudentRelationEdge;
import ca.mun.util.StudentRelationGraph;

public class PrerequisiteGroupGeneration implements GroupGeneration{

	private double numberOfTeams;
	private int numberOfStu;
	private int numSmallerTeams = 0;
	private int numLargerTeams = 0;
	private int smallerTeamSize;
	private int largerTeamSize;
	private int stuInSmallerTeams;
	List<Team> teams; 
	
	public PrerequisiteGroupGeneration(int groupSizes, int numberOfStudents){
		teams = new ArrayList<Team>();
		numberOfTeams = (double)numberOfStudents/(double)groupSizes;
		numberOfTeams = Math.ceil(numberOfTeams);
		for(int i=0; i<(int)numberOfTeams; i++){
			teams.add(new Team(i));
		}
		
		numberOfStu = numberOfStudents;
		numLargerTeams = numberOfStudents/groupSizes;
		stuInSmallerTeams = numberOfStudents%groupSizes;
		largerTeamSize = groupSizes;
		smallerTeamSize = groupSizes - 1;
		if (stuInSmallerTeams != 0 && stuInSmallerTeams != smallerTeamSize) {
			numSmallerTeams = 1;
			calculateTeamSizes();
		}
	}
	
	@Override
	public Collection<Team> generateGroups(int groupSizes, Collection<StudentRelationGraph> students) {
		int teamNum = 0;
		for(StudentRelationGraph stu : students){
			boolean cont = false;
			for(Team team : teams){
				if(team.contains(stu.getStudent())){
					cont = true;
				}
			}
			if(cont){
				continue;
			}
			Team team = teams.get(teamNum);
			if (numLargerTeams > 0) {
				if(team.size() == largerTeamSize){
					teamNum++;
					team = teams.get(teamNum);
					numLargerTeams--;
				}	
			}
			else if (numSmallerTeams > 0) {
				if(team.size() == smallerTeamSize){
					teamNum++;
					team = teams.get(teamNum);
					numSmallerTeams--;
				}
			}
			ArrayList<StudentRelationEdge> potentialPartnerEdges = stu.getEdges();
			int canWorkWith = 0;
			for(Object student : team){
				Student s = (Student)student;
				for(StudentRelationEdge edge : potentialPartnerEdges){
					if(edge.contains(s)){
						canWorkWith++;
						break;
					}
				}
			}
			if(canWorkWith == team.size()){
				team.add(stu.getStudent());
			}
			else{
				for(Team altTeam : teams){
					if(altTeam == team){
						continue;
					}
					for(Object student : altTeam){
						Student s = (Student)student;
						for(StudentRelationEdge edge : potentialPartnerEdges){
							if(edge.contains(s)){
								canWorkWith++;
								break;
							}
						}
					}
					if(canWorkWith == altTeam.size() && altTeam.size() < groupSizes){
						altTeam.add(stu.getStudent());
					}
				}
				
			}
		}
		return teams;
	}
	
	// Recursively determines the number of students in the teams for even distribution
	private void calculateTeamSizes() {
		stuInSmallerTeams += largerTeamSize;
		numSmallerTeams++;
		numLargerTeams--;
		
		if (stuInSmallerTeams%smallerTeamSize == 0) { return; }
		if (numLargerTeams == 0) {
			if ((stuInSmallerTeams%smallerTeamSize) == (smallerTeamSize-1)) { return; }
			largerTeamSize = smallerTeamSize-1;
			smallerTeamSize -= 2;
			numLargerTeams = numberOfStu/largerTeamSize;
			stuInSmallerTeams = numberOfStu%largerTeamSize;
			if (stuInSmallerTeams == 0) {
				numSmallerTeams = 0;
				return;
			}
			else {
				numSmallerTeams = 1;
			}
		}
		calculateTeamSizes();
	}
	
	public void forceGrouping(int groupSizes, Collection<StudentRelationGraph> students){
		for(Team t : teams){
			if(t.size()+students.size() <= groupSizes){
				boolean canAdd = true;
				for(ProjectMember s : t){
					for(StudentRelationGraph stu : students){
						ArrayList<StudentRelationEdge> potentialPartnerEdges = stu.getEdges();							
						boolean canWorkWith = false;
						for(StudentRelationEdge e : potentialPartnerEdges){
							if(e.contains(s)){
								canWorkWith = true;
								break;
							}
						}
						if(!canWorkWith){
							canAdd = false;
							break;
						}
					}
					if(!canAdd){
						break;
					}
				}
				if(canAdd){
					for(StudentRelationGraph stu : students){
						t.add(stu.getStudent());
					}
					return;
				}
			}
		}
	}
}
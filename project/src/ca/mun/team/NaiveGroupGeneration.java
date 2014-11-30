package ca.mun.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.mun.util.StudentRelationGraph;
import ca.mun.util.StudentRelationEdge;

/*
 * This class is a group sorting method that ignores all preferences, does not include skill sets.
 * It loops over all the students, adds them to a team if they are not in one yet, 
 * then checks all people they can work with, who can also work together,
 * and adds them to the team if they can.
 * Students are evenly distributed amongst the teams and no team has more students than the group size designated by the Project Manager
 */

public class NaiveGroupGeneration implements GroupGeneration {
	
	private double numberOfTeams;
	private int numberOfStu;
	private int numSmallerTeams = 0;
	private int numLargerTeams = 0;
	private int smallerTeamSize;
	private int largerTeamSize;
	private int stuInSmallerTeams;
	
	@Override
	public Collection<Team> generateGroups(int groupSizes, Collection<StudentRelationGraph> students) {
		List<Team> teams = new ArrayList<Team>();
		numberOfTeams = (double)students.size()/(double)groupSizes;
		numberOfTeams = Math.ceil(numberOfTeams);
		System.out.println(numberOfTeams);
		for(int i=0; i<(int)numberOfTeams; i++){
			teams.add(new Team(i));
		}
		
		numberOfStu = students.size();
		numLargerTeams = students.size()/groupSizes;
		stuInSmallerTeams = students.size()%groupSizes;
		largerTeamSize = groupSizes;
		smallerTeamSize = groupSizes - 1;
		if (stuInSmallerTeams != 0 && stuInSmallerTeams != smallerTeamSize) {
			numSmallerTeams = 1;
			calculateTeamSizes();
		}
		
		int teamNum = 0;
		System.out.println("numberOfStu: " + numberOfStu);
		System.out.println("numLargerTeams: " + numLargerTeams);
		System.out.println("stuInSmallerTeams: " + stuInSmallerTeams);
		System.out.println("largerTeamSize: " + largerTeamSize);
		System.out.println("smallerTeamSize: " + smallerTeamSize);
		for(StudentRelationGraph stu : students){			
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
			System.out.println("canWorkWith: " + canWorkWith);
			System.out.println("team size: " + team.size());
			for(Object student : team){
				Student s = (Student)student;
				System.out.println(s.getName());
				for(StudentRelationEdge edge : potentialPartnerEdges){
					if(edge.contains(s)){
						canWorkWith++;
						break;
					}
				}
			}
			if(canWorkWith == team.size()){
				System.out.println("adding student");
				team.add(stu.getStudent());
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
}
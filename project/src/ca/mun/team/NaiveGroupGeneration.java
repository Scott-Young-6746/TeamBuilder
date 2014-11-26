package ca.mun.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.mun.util.StudentRelationGraph;
import ca.mun.util.StudentRelationEdge;

/*
 * This class is a group sorting method that ignores all preferences, does not include skill sets
 * and is in general not very useful other than as a means to test the base code.
 * It loops over all the students, adds them to a team if they are not in one yet, 
 * then checks all people they can work with, who can also work together,
 * and adds them to the team if they can.
 */

public class NaiveGroupGeneration implements GroupGeneration {
	
	@Override
	public Collection<Team> generateGroups(int groupSizes, Collection<StudentRelationGraph> students) {
		List<Team> teams = new ArrayList<Team>();
		double numberOfTeams = (double)students.size()/(double)groupSizes;
		numberOfTeams = Math.floor(numberOfTeams);
		for(int i=0; i<(int)numberOfTeams; i++){
			
			teams.add(new Team(i));
			
		}
		int teamNum = 0;
		int numLargerGroups = students.size() % groupSizes;
		for(StudentRelationGraph stu : students){			
			Team team = teams.get(teamNum);
			if (numLargerGroups > 0) {
				if(team.size() == groupSizes+1){
					teamNum++;
					team = teams.get(teamNum);
					numLargerGroups--;
				}	
			}
			else if(team.size() == groupSizes){
				teamNum++;
				team = teams.get(teamNum);
			}
			ArrayList<StudentRelationEdge> potentialPartnerEdges = stu.getEdges();
			int canWorkWith = 0;
			for(Object student : team){
				for(StudentRelationEdge edge : potentialPartnerEdges){
					if(edge.contains((ProjectMember)student)){
						canWorkWith++;
						break;
					}
				}
			}
			if(canWorkWith == team.size()){
				team.add(stu.getStudent());
			}
		}
		return teams;
	}
}
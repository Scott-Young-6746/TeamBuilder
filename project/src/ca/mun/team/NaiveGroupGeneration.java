package ca.mun.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.mun.util.StudentRelationGraph;
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
		for(StudentRelationGraph stu : students){
			boolean cont = false;
			for(Team team : teams){
				if(team.contains(stu.getStudent()) == true){
					cont = true;
				}
			}
			if(!cont){
				int count = 0;
				while(true){
					if(teams.get(count).size() < groupSizes){
					    teams.get(count).add(stu.getStudent());
					    break;
					}
					count++;
				}
			}
			//Could I get all the people you can partner with in a list?
			ArrayList<StudentRelationGraph> potentialPartners = stu.getPotentialPartners();
			//Oh for sure!
			for(StudentRelationGraph partner : potentialPartners){
				boolean canMatch = true;
				//Could I get all the people you can partner with in a list?
				ArrayList<StudentRelationGraph> partnersMatches = partner.getPotentialPartners();
				//Oh for sure!
				for(StudentRelationGraph third : partnersMatches){
					if(potentialPartners.contains(third.getStudent()) != true){
						canMatch = false;
					}
				}
				if(canMatch){
					for(Team team : teams){
						if(team.contains(stu.getStudent()) == true && team.size() < groupSizes){
							team.add(partner.getStudent());
						}
					}
				}
			}
		}
		return teams;
	}

}

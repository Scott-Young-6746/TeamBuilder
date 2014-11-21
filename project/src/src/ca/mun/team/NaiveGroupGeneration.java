package ca.mun.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.mun.util.StudentRelationGraph;

public class NaiveGroupGeneration implements GroupGeneration {
	
	@Override
	public Collection<Team> generateGroups(int groupSizes, Collection<StudentRelationGraph> students) {
		List<Team> teams = new ArrayList<Team>();
		for(StudentRelationGraph stu : students){
			boolean cont = false;
			for(Team team : teams){
				if(team.contains(stu) == true){
					cont = true;
				}
			}
			if(cont){
				continue;
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
					if(potentialPartners.contains(third) != true){
						canMatch = false;
					}
				}
				if(canMatch){
					for(Team team : teams){
						if(team.contains(stu) == true){
							team.add(partner);
						}
					}
				}
			}
		}
		return teams;
	}

}

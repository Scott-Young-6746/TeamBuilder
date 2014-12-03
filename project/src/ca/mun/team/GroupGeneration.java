package ca.mun.team;

import java.util.Collection;

import ca.mun.util.StudentRelationGraph;

public interface GroupGeneration {
	public Collection<Team> generateGroups(int groupSizes, Collection<StudentRelationGraph> students);
	public void forceGrouping(int groupSizes, Collection<StudentRelationGraph> students);
}

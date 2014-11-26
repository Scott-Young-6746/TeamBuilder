package ca.mun.team;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

@SuppressWarnings("hiding")
public class Team<ProjectMember> implements Collection{
    private int teamNumber;
    private List<ProjectMember> listOfTeamMembers;
    
    public Team(int teamNumber){
        this.teamNumber = teamNumber;
        listOfTeamMembers = new ArrayList<ProjectMember>();
    }

	public void add(int arg0, ProjectMember arg1) {
		listOfTeamMembers.add(arg0, arg1);
	}

	public boolean addAll(Collection arg0) {
		return listOfTeamMembers.addAll(arg0);
	}

	public boolean addAll(int arg0, Collection<? extends ProjectMember> arg1) {
		return listOfTeamMembers.addAll(arg0, arg1);
	}

	public void clear() {
		listOfTeamMembers.clear();
	}

	public boolean contains(Object arg0) {
		return listOfTeamMembers.contains(arg0);
	}

	public boolean containsAll(Collection arg0) {
		return listOfTeamMembers.containsAll(arg0);
	}

	public boolean equals(Object arg0) {
		return listOfTeamMembers.equals(arg0);
	}

	public ProjectMember get(int arg0) {
		return listOfTeamMembers.get(arg0);
	}

	public int hashCode() {
		return listOfTeamMembers.hashCode();
	}

	public int indexOf(Object arg0) {
		return listOfTeamMembers.indexOf(arg0);
	}

	public boolean isEmpty() {
		return listOfTeamMembers.isEmpty();
	}

	public Iterator<ProjectMember> iterator() {
		return listOfTeamMembers.iterator();
	}

	public int lastIndexOf(Object arg0) {
		return listOfTeamMembers.lastIndexOf(arg0);
	}

	public ListIterator<ProjectMember> listIterator() {
		return listOfTeamMembers.listIterator();
	}

	public ListIterator<ProjectMember> listIterator(int arg0) {
		return listOfTeamMembers.listIterator(arg0);
	}

	public ProjectMember remove(int arg0) {
		return listOfTeamMembers.remove(arg0);
	}

	public boolean remove(Object arg0) {
		return listOfTeamMembers.remove(arg0);
	}

	public boolean removeAll(Collection arg0) {
		return listOfTeamMembers.removeAll(arg0);
	}

	public boolean retainAll(Collection arg0) {
		return listOfTeamMembers.retainAll(arg0);
	}

	public ProjectMember set(int arg0, ProjectMember arg1) {
		return listOfTeamMembers.set(arg0, arg1);
	}

	public int size() {
		return listOfTeamMembers.size();
	}

	public List<ProjectMember> subList(int arg0, int arg1) {
		return listOfTeamMembers.subList(arg0, arg1);
	}

	public Object[] toArray() {
		return listOfTeamMembers.toArray();
	}
	public  Object[] toArray(Object[] arg0) {
		return listOfTeamMembers.toArray(arg0);
	}

	@Override
	public boolean add(Object arg0) {
		return listOfTeamMembers.add((ProjectMember) arg0);
	}
	
	public String getNumber() {
		String str = Integer.toString(teamNumber);
		return str;
	}
	
	public List<ProjectMember> getListOfMembers() {
		return listOfTeamMembers;
	}

	
}
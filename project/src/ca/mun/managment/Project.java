package ca.mun.managment;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.FileNotFoundException;
import java.lang.Integer;

import ca.mun.team.*;
import ca.mun.util.StudentRelationGraph;

public class Project{
    private String name;
    private int sizeOfTeams;
    private Calendar ProjectMemberPreferencesDeadline;
    
    private List<ProjectMember> listOfMembers;
    private List<List<ProjectMember>> forbiddenMembers;
    private List<List<ProjectMember>> requiredMembers;
    private List<Team> listOfTeams;
    private List<String> selfEvaluationQuestions;
    
    public Project(String className, String classInput, int sizeOfTeams) throws FileNotFoundException{
        name = className;
        this.sizeOfTeams = sizeOfTeams;
        ProjectMemberPreferencesDeadline = new GregorianCalendar(2099,01,01);
        //listOfMembers = new ArrayList<ProjectMember>();
        StudentListGenerator slg = new StudentListGenerator(classInput); 
        listOfMembers = slg.getList();
        forbiddenMembers = new ArrayList<List<ProjectMember>>();
        requiredMembers = new ArrayList<List<ProjectMember>>();
        listOfTeams = new ArrayList<Team>();
        selfEvaluationQuestions = new ArrayList<String>();
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public String getName(){
        return name;
    }
    
    public void setSizeOfTeams(int newSize){
        sizeOfTeams = newSize;
    }
    
    public int getSizeOfTeams(){
        return sizeOfTeams;
    }
    
    public void setNewPreferencesDeadline(int year,int month,int day){
        ProjectMemberPreferencesDeadline.set(year,month,day);
    }
    
    public String getPreferencesDeadline(){
        String year = Integer.toString(ProjectMemberPreferencesDeadline.get(Calendar.YEAR));
        String month = Integer.toString(ProjectMemberPreferencesDeadline.get(Calendar.MONTH) + 1);
        String day = Integer.toString(ProjectMemberPreferencesDeadline.get(Calendar.DAY_OF_MONTH));
        return year+"/"+month+"/"+day;
    }
    
    public void fetchNewProjectsMembersList(){
        /** Will read in initial list of eligible members from a database or stream **/
    }
    
    public List<ProjectMember> getListOfMembers(){
        return listOfMembers;
    }
    
    public void addProjectMember(String newName, String studentNumber){
        if(studentNumber.equals("0")){
            listOfMembers.add(new ProjectMember(newName));
        }
        else{
            listOfMembers.add(new Student(newName, studentNumber));
        }
    }
    
/*    public void addForbiddenMembers(String name1, String name2){
        String[] n = {name1, name2};
        forbiddenMembers.add(n);
    }
    
    public void addRequiredMembers(String name1, String name2){
        String[] n = {name1, name2};
        requiredMembers.add(n);
    }
*/
    
    public void addForbiddenMembers(List<ProjectMember> list) {
    	forbiddenMembers.add(list);
    }
    
    public void addRequiredMembers(List<ProjectMember> list) {
    	requiredMembers.add(list);
    }
    
    public void clearMemberPreferences() {
    	forbiddenMembers = new ArrayList<List<ProjectMember>>();
        requiredMembers = new ArrayList<List<ProjectMember>>();
        System.out.println("Preferences cleared.");
    }
    
    public void constructTeams(){
        GroupGeneration generator = new PrerequisiteGroupGeneration(sizeOfTeams, listOfMembers.size());
        ArrayList<StudentRelationGraph> graph = new ArrayList<StudentRelationGraph>();
        for(List<ProjectMember> team1 : requiredMembers){
        	for(List<ProjectMember> team2 : requiredMembers){
        		if(team1 == team2){
        			continue;
        		}
        		for(ProjectMember m1 : team1){
        			if(team2.contains(m1)){
        				team1.addAll(team2);
        				requiredMembers.remove(team2);
        			}
        		}
        	}
        }
        for(List<ProjectMember> team : requiredMembers){
        	for(ProjectMember m : team){
        		System.out.println(m.getName());
        	}
        }
        for(ProjectMember member : listOfMembers){
        	boolean teamed = false;
        	for(List<ProjectMember> t : requiredMembers){
        		if(t.contains(member)){
        			teamed = true;
        		}
        	}
        	if(!teamed){
        		StudentRelationGraph g = new StudentRelationGraph((Student)member);
                graph.add(g);
        	}
            
        }
        for(List<ProjectMember> t : requiredMembers){
        	ArrayList<StudentRelationGraph> team = new ArrayList<StudentRelationGraph>();
        	for(ProjectMember member : t){
        		StudentRelationGraph g = new StudentRelationGraph((Student)member);
                team.add(g);
                graph.add(g);
        	}
        	generator.forceGrouping(sizeOfTeams, team);
        }
        for(StudentRelationGraph g : graph){
            for(StudentRelationGraph s : graph){
                if(s.equals(g)){
                    continue;
                }
                boolean canWork = true;
                for(List<ProjectMember> ban : forbiddenMembers){
                	if(ban.contains(g.getStudent())){
                		if(ban.contains(s.getStudent())){
                			canWork = false;
                			break;
                		}
                	}
                }
                if(canWork){
                	s.addRelation(g);
                	g.addRelation(s);
                }
            }
        }
        listOfTeams = (List<Team>) generator.generateGroups(sizeOfTeams, graph);
    }
  
    public void addTeam(int newTeamNumber){
        listOfTeams.add(new Team(newTeamNumber));
    }
    
    public void addQuestion(String question){
        selfEvaluationQuestions.add(question);
    }
    
    public void removeQuestion(int i){
        selfEvaluationQuestions.remove(i);
    }
    
    public List<Team> getTeams() {
		return listOfTeams;
    }
    
}
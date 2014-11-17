import java.util.List;
import java.util.ArrayList;

public class ProjectMember{
    private String name;
    private List<String> desiredMembers;
    private List<String> undesiredMembers;
    private Schedule memberSchedule;
    
    public ProjectMember(String name){
        this.name = name;
        desiredMembers = new ArrayList<String>();
        undesiredMembers = new ArrayList<String>();
        memberSchedule = new Schedule();
    }
    
    public String getName(){
        return name;
    }
    
    public List<String> getDesiredMembers(){
        return desiredMembers;
    }
    
    public List<String> getUndesiredMembers(){
        return undesiredMembers;
    }

    public void addTimeToSchedule(String day, int beginTime, int endTime){
        memberSchedule.addTime(day, beginTime, endTime);
    }
    
    public Schedule getSchedule(){
        return memberSchedule;
    }
    
    public void addDesiredMember(String memberName){
        desiredMembers.add(memberName);
    }
    
    public void addUndesiredMember(String memberName){
        undesiredMembers.add(memberName);
    }
    
    public void removeDesiredMember(String memberName){
        if(desiredMembers.contains(memberName)){
            desiredMembers.remove(memberName);
        }
    }
    
    public void removeUndesiredMember(String memberName){
        if(undesiredMembers.contains(memberName)){
            undesiredMembers.remove(memberName);
        }
    }
    
    public void clearDesiredMembersList(){
        desiredMembers.clear();
    }
    
    public void clearUndesiredMembersList(){
        undesiredMembers.clear();
    }
}
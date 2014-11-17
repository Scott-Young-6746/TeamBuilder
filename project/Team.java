import java.util.List;
import java.util.ArrayList;

public class Team{
    private int teamNumber;
    private List<ProjectMember> listOfTeamMembers;
    
    public Team(int teamNumber){
        this.teamNumber = teamNumber;
        listOfTeamMembers = new ArrayList<ProjectMember>();
    }
}
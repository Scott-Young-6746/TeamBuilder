package ca.mun.managment;

import java.util.List;
import java.util.ArrayList;

public class ProjectManager{
    private String name;
    private List<Project> ManagedProjects;
    
    public ProjectManager(String name){
        this.name = name;
        ManagedProjects = new ArrayList<Project>();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public List<Project> getListOfProjects(){
        return ManagedProjects;
    }
}
package ca.mun.team;

import java.util.Map;
import java.util.HashMap;
import java.lang.Integer;

public class Student extends ProjectMember{
    private String studentNumber;
    private Map<String, Integer> courses;
    
    public Student(String name, String studentNumber){
        super(name);
        this.studentNumber = studentNumber;
        courses = new HashMap<String, Integer>();
    }
    
    public String getStudentNumber(){
        return studentNumber;
    }
    
    public void addCourse(String courseName, int courseGrade){
        courses.put(courseName,new Integer(courseGrade));
    }
    
    public int getCourseGrade(String courseName){
        return (int)courses.get(courseName);
    }
}
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ca.mun.managment.StudentListGenerator;
import ca.mun.team.ProjectMember;
import ca.mun.team.Team;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainFrame extends JFrame {
	public MainFrame(String title) {
		super(title);
		
		//Set Layout Manager
		
		getContentPane().setLayout(new BorderLayout());
		
		//Creating the panel for forcingStudents together
		
		final Panel forcePanel = new Panel();
		GridLayout forceLayout = new GridLayout(4,4);
		forcePanel.setLayout(forceLayout);
		getContentPane().add(forcePanel, BorderLayout.EAST);
		forcePanel.setVisible(false);
		
		//Adding components to the forcePanel
		
	
		

		//Creating the panel for student/group outputs (CENTER)
		
		Panel outputPanel = new Panel();
		GridLayout outputLayout = new GridLayout(0,2);
		outputPanel.setLayout(outputLayout);

		//Output for student (scrolls)
		
		final JTextArea textArea = new JTextArea("List of students");
		JScrollPane scrollPane1 = new JScrollPane(textArea);
		outputPanel.add(scrollPane1);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		
		//Output for student groups (scrolls)
		
		final JTextArea groupTextArea = new JTextArea("Optimal groups");
		JScrollPane scrollPane2 = new JScrollPane(groupTextArea);
		outputPanel.add(scrollPane2);
		groupTextArea.setLineWrap(true);
		groupTextArea.setEditable(false);
		
		//Adding the panel to the window
	
		getContentPane().add(outputPanel, BorderLayout.CENTER);
		
		//Creating the Panel (NORTH)
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		GridLayout panelLayout = new GridLayout(3,2);
		panel.setLayout(panelLayout);
			
		//Adding components to the panel
				
		//Class Name
				
		JLabel textArea1 = new JLabel("Class Name: "); 
		panel.add(textArea1);
				
		final JTextField classNameInput = new JTextField();
		panel.add(classNameInput);
				
		//Group Size
				
		JLabel textArea2 = new JLabel("Group Size: ");
		panel.add(textArea2);
				
		final JTextField groupSizeInput = new JTextField();
		panel.add(groupSizeInput);
				
		//Professor
				
		JLabel textArea3 = new JLabel("Prof Name: ");
		panel.add(textArea3);
				
		final JLabel profNameText = new JLabel();
		panel.add(profNameText);
	
		//Buttons to start generating groups/importing class into JLabel
		
		//importing and displaying text from file (or database, if needed) 
		JButton displayClassButton = new JButton("Load Class");
		displayClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				//checks if both class name and group size are filled in
				if (classNameInput.getText().trim().length() == 0 || groupSizeInput.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(getContentPane(), "Please enter values for both class name and group size."); }
				
				String a = groupSizeInput.getText();
				
				//checks if the group size is an actual numeric value. If not, students will not be generated
				
				if (isNumeric(a) != true) {
					
					JOptionPane.showMessageDialog(getContentPane(), "Please enter a numberic value for the specified group size."); }
	
				//if they are, set static variable and choose a file
				else {
						
					JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String fileName = f.getAbsolutePath();
		try {
					
				FileReader reader = new FileReader(fileName);
				BufferedReader br = new BufferedReader(reader);
				textArea.read(br, null);
				br.close();
				textArea.requestFocus();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
					} 
				}
				
				try {
					String className = classNameInput.getText();
					int groupSize = Integer.parseInt(groupSizeInput.getText());
					String classInput = textArea.getText();
					Controller.setGroupSize(groupSize);		//sets the size of the groups
					Controller.setString(classInput); //sets the String of students
					Controller.setClassName(className); //sets the name of the class (ie. 3716)
					Controller.createProject(); //calls the controller to create a new project
					
					}  
					catch(Exception e) {}
				
				profNameText.setText(StudentListGenerator.profName);
				
				//here, we call to populate the forcePanel (since our project now has members within it)
				
				ArrayList<ProjectMember> list = (ArrayList<ProjectMember>) Controller.project.getListOfMembers();
				
				JLabel label1 = new JLabel("Student 1: ");
				forcePanel.add(label1);
				JComboBox combo1 = new JComboBox();
				forcePanel.add(combo1);
				JLabel label2 = new JLabel("Student 2: ");
				forcePanel.add(label2);
				JComboBox combo2 = new JComboBox();
				forcePanel.add(combo2);
				
				JRadioButton forceTogether = new JRadioButton("Force together");
				JRadioButton forceApart = new JRadioButton("Force apart");
				
				ButtonGroup group = new ButtonGroup();
				group.add(forceTogether);
				group.add(forceApart);
				
				forcePanel.add(forceTogether);
				forcePanel.add(forceApart);
				
				JButton force = new JButton("Perform operation");
				forcePanel.add(force);
				
				//populating the JComboBoxes (for student selection)
				
				for (int i = 0; i < list.size(); i++)
				combo1.addItem(list.get(i).getName());
				for (int i = 0; i < list.size(); i++)
				combo2.addItem(list.get(i).getName());
				
			}
		});
		
		//Button to generate optimal teams and output within the groupTextArea
		
		JButton generateButton = new JButton("Generate Groups");
		generateButton.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent event) {
				
			if(Controller.project.getListOfMembers().size() < Controller.project.getSizeOfTeams()) {
				
				JOptionPane.showMessageDialog(getContentPane(), "Please enter a smaller group size and reload the class."); }
			
			else {
				
			ArrayList<Team> list = new ArrayList<Team>();
			if (Controller.project != null ) { //checking if a project has been created already (has a class been loaded into the system?)
				list = (ArrayList<Team>) Controller.generateGroups(); } //generate groups using this project and store them in an ArrayList
			groupTextArea.setText("");
				
			//printing all the students within the groups and displaying them in the groupTextArea
			
			for(Team t : list){
					int i = Integer.parseInt(t.getNumber());
					i = i+1;
					groupTextArea.append("\n" + "Team: " + i + "\n");
					for(Object m : t) {
						ProjectMember mem = (ProjectMember)m;
						groupTextArea.append(mem.getName() + "\n");
						}
					}

				}
			
			}
		});
		
		//Button to handle the forcing of groups by the professor
		
		final JButton forceStudentsButton = new JButton("Set forced groups");
		forceStudentsButton.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent event) {
	
			if (Controller.project == null) {
				JOptionPane.showMessageDialog(getContentPane(), "Please load a class before you try to force groups.");
			}
				
			else {
			
			if (forceStudentsButton.getText() == "Set forced groups") {
				forcePanel.setVisible(true);
				forceStudentsButton.setText("Close");
			}
			
			else if (forceStudentsButton.getText() == "Close") {
				forcePanel.setVisible(false);
				forceStudentsButton.setText("Set forced groups");
					}
			
				}
			
			}
		});
		
		//Adding button panel (SOUTH)
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(displayClassButton);
		buttonPanel.add(generateButton);	
		buttonPanel.add(forceStudentsButton);
		
		
		
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	
}

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
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
					JOptionPane.showMessageDialog(getContentPane(), "Please enter values for both class name and group size.");
				
				}
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
			}
		});
		
		
		//Button to generate optimal teams and output within the groupTextArea
		
		JButton generateButton = new JButton("Generate Groups");
		generateButton.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent event) {
				
			ArrayList<Team> list = new ArrayList<Team>();
			
			if (Controller.project != null ) { //checking if a project has been created already (has a class been loaded into the system?)
				list = (ArrayList<Team>) Controller.generateGroups(); } //generate groups using this project and store them in an ArrayList
					
			//Parsing through the ArrayLists to output who is in each team
			
			ArrayList<ProjectMember> list1 = (ArrayList<ProjectMember>) Controller.project.getListOfMembers();
			
			groupTextArea.setText(""); //sets text space back to empty before filling with groups
			
			for (ProjectMember temp : list1) {
				groupTextArea.append(temp.getName() + "\n");
				}
			}
		});
		
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(displayClassButton);
		buttonPanel.add(generateButton);		
		
	}
	
}

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;



public class MainFrame extends JFrame {
	public MainFrame(String title) {
		super(title);
		
		//Set Layout Manager
		
		getContentPane().setLayout(new BorderLayout());
		
		//Create Swing Components
		//Output for students/student groups
		
		final JTextArea textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.CENTER);
		
		textArea.setLineWrap(true);
		
		//Buttons to start generating groups/importing class into JLabel
		
		JButton displayClassButton = new JButton("Load Class");
		displayClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				//importing and displaying text from file (or database, if needed) 
				
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
		});
		
		JButton generateButton = new JButton("Generate Groups");
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			}
		});
		
		
		
		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(displayClassButton);
		buttonPanel.add(generateButton);		
		//Creating the Panel (north)
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		//Setting the panel layout
		
		GridLayout panelLayout = new GridLayout(3,2);
		panel.setLayout(panelLayout);
		
		//Adding components to the panel
		
		//Class Name
		
		JLabel textArea1 = new JLabel("Class Name: "); 
		panel.add(textArea1);
		
		textArea1.setSize(100, 100);
		
		JTextField classNameInput = new JTextField();
		panel.add(classNameInput);
		
		classNameInput.setSize(100, 100);
		
		//Group Size
		
		JLabel textArea2 = new JLabel("Group Size: ");
		panel.add(textArea2);
		
		textArea2.setSize(100, 100);
		
		JTextField groupSizeInput = new JTextField();
		panel.add(groupSizeInput);
		
		groupSizeInput.setSize(100, 100);
		
		//Professor
		
		JLabel textArea3 = new JLabel("Prof Name: ");
		panel.add(textArea3);
		
		textArea3.setSize(100, 100);
		
		JLabel profNameText = new JLabel();
		panel.add(profNameText);
		
		
	}
	
}

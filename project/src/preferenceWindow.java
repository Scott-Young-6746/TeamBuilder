import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import static java.lang.System.out;
import ca.mun.team.ProjectMember;
import ca.mun.team.Student;

public class preferenceWindow extends JFrame {

	JFrame preferenceWindow = new JFrame("Preference Window");
	ArrayList<ProjectMember> selection;
	ArrayList<Node> display;
	JPanel checkPanel;

	class Node
	   {  
	      public JCheckBox box;
	      public ProjectMember mem;
	   }
	
	public preferenceWindow() {
		createWindow();
	}
	
	public preferenceWindow(List<ProjectMember> members) {
		createWindow();
		if(members.isEmpty()) {
			JOptionPane.showMessageDialog(getContentPane(), "There are no students in this class.");
		}
		else for (ProjectMember m : members) {
			Node n = new Node();
			n.mem = m;
			n.box = new JCheckBox(m.getName());
			display.add(n);
		}
		checkPanel.setLayout(new GridLayout(display.size(), 1));
		printList();
	}
	
	private void createWindow() {
		preferenceWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		preferenceWindow.setSize(200, 500);
		preferenceWindow.setLocation(560, 60);
		
		preferenceWindow.setLayout(new BorderLayout());
		
		// Adding panel to display check boxes
		checkPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane(checkPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		preferenceWindow.add(scrollPane, BorderLayout.CENTER);
		
		//Adding buttons to the SOUTH
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				preferenceWindow.setVisible(false);
			}
		});
		
		JButton performButton = new JButton("Perform Operation");
		
		southPanel.add(performButton, BorderLayout.NORTH);
		southPanel.add(doneButton, BorderLayout.SOUTH);
		preferenceWindow.add(southPanel, BorderLayout.SOUTH);
		preferenceWindow.setVisible(true);
		
		selection = new ArrayList<ProjectMember>();
		display = new ArrayList<Node>();
	}
	
	private void printList() {
		for (Node n : display) {
			checkPanel.add(n.box);		
		}
	}

}

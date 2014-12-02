import java.awt.BorderLayout;
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

import ca.mun.team.ProjectMember;

public class preferenceWindow extends JFrame {

	JFrame preferenceWindow = new JFrame("Preference Window");
	ArrayList<ProjectMember> selection = new ArrayList<ProjectMember>();
	ArrayList<Node> display = new ArrayList<Node>();

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
		printList();
	}
	
	private void createWindow() {
		preferenceWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		preferenceWindow.setSize(160, 500);
		preferenceWindow.setLocation(540, 40);
		
		preferenceWindow.setLayout(new BorderLayout());
		
		getContentPane().setLayout(new BorderLayout());
		
		//Adding buttons to the SOUTH
		
		JPanel southPanel = new JPanel();
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				preferenceWindow.setVisible(false);
			}
		});
		
		JButton performButton = new JButton("Perform Operation");
		
		southPanel.add(performButton, BorderLayout.NORTH);
		southPanel.add(doneButton, BorderLayout.SOUTH);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		preferenceWindow.setVisible(true);
	}
	
	private void printList() {
		JPanel p = new JPanel();
		JCheckBox cb = new JCheckBox();
		for (Node n : display) {
			p.add(n.box);		
		}
		preferenceWindow.add(p);
	}
	
}

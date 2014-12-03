import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ca.mun.team.ProjectMember;

@SuppressWarnings("serial")
public class preferenceWindow extends JFrame {

	JFrame preferenceWindow = new JFrame("Preference Window");
	ArrayList<ProjectMember> selection;
	ArrayList<Node> display;
	JPanel checkPanel;
	JRadioButton radioReq;
	JRadioButton radioForbid;

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

		JButton performButton = new JButton("Save Preferences");
		performButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				addPreferences();
			}
		});
		
		radioReq = new JRadioButton("Must work together");
		radioForbid = new JRadioButton("Can't work together");
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioReq);
		group.add(radioForbid);
		
		JPanel radioPanel = new JPanel(new GridLayout(2,1));
		radioPanel.add(radioReq);
		radioPanel.add(radioForbid);

		southPanel.add(radioPanel, BorderLayout.NORTH);
		southPanel.add(performButton, BorderLayout.CENTER);
		southPanel.add(doneButton, BorderLayout.SOUTH);
		preferenceWindow.add(southPanel, BorderLayout.SOUTH);
		preferenceWindow.setVisible(true);

		display = new ArrayList<Node>();
	}

	private void printList() {
		for (Node n : display) {
			checkPanel.add(n.box);		
		}
	}

	private void addPreferences() {
		selection = new ArrayList<ProjectMember>();
		for (Node n : display) {
			if (n.box.isSelected()) {
				selection.add(n.mem);
			}
		}
		
		int groupSize = Controller.project.getSizeOfTeams();

		if ((selection.size() > groupSize) && radioReq.isSelected()) {
			JOptionPane.showMessageDialog(getContentPane(), "You have selected too many people to work together.\nYou have limited your group size to " + groupSize + " members.\n\nPlease select " + groupSize + " or less members that have to work together.");
		}
		else if (((display.size() - selection.size()) < groupSize) && radioForbid.isSelected()) {
			JOptionPane.showMessageDialog(getContentPane(), "You have selected too many people who cannot work together.\nYou have indicated that your groups should have " + groupSize + " members.\n\nPlease select fewer members that cannot work together.");
		}
		else {
			if (radioReq.isSelected()) {
				Controller.project.addRequiredMembers(selection);
				clearSelection();
			}
			else if (radioForbid.isSelected()) {
				Controller.project.addForbiddenMembers(selection);
				clearSelection();
			}
			else {
				JOptionPane.showMessageDialog(getContentPane(), "Please indicate whether or not these members either have to or can not work together.");
			}
		}	
	}
	
	private void clearSelection() {
		JOptionPane.showMessageDialog(getContentPane(), "Preferences successfully saved.");
		for (Node n : display) {
			n.box.setSelected(false);
		}
	}

}

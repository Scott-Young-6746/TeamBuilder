import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class preferenceWindow {
	JFrame preferenceWindow = new JFrame("Preference Window");

	public preferenceWindow() {
		
		preferenceWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		preferenceWindow.setSize(500, 500);
		preferenceWindow.setLocation(500, 0);
		
		preferenceWindow.setLayout(new BorderLayout());
		
		//Adding buttons to the SOUTH
		
		JPanel southPanel = new JPanel();
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				preferenceWindow.setVisible(false);
			}
		});
		
		JButton performButton = new JButton("Perform Operation");
		
		southPanel.add(performButton);
		southPanel.add(doneButton);
		preferenceWindow.add(southPanel, BorderLayout.SOUTH);
		preferenceWindow.setVisible(true);
	}
	
	
	
}

import java.util.Scanner;

import javax.swing.JFrame;


public class GUI {
	
	//Constructor for GUI
	
	public GUI() {}
		
	public void run() {
		
		JFrame frame = new MainFrame("Team Generator");
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
}

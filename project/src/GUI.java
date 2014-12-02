import java.util.Scanner;

import javax.swing.JFrame;


@SuppressWarnings("unused")
public class GUI {
	
	//Constructor for GUI
	
	public GUI() {}
		
	public void run() {
		
		JFrame frame = new MainFrame("Team Generator");
		frame.setSize(500, 500);
		frame.setLocation(40, 40);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	
}

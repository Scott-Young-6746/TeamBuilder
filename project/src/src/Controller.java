import java.io.FileNotFoundException;

public class Controller {

	static String classInput;
	static int groupSize;
  
	public static void main(String[] args) throws FileNotFoundException {
    	GUI userGui = new GUI();
    	userGui.run();  
    }
 
	
	//Set the static variable String
	public static void setString(String str) {
		classInput = str;
	}
	
	//Set the static variable groupSize
	public static void setGroupSize(int i) {
		groupSize = i;
	}
	
}
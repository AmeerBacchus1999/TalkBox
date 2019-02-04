package talkbox;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
public class ConfigApp {
	
	public static void main(String[] args) {
		
		int size;
	
		//test
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number of buttons: ");
		size = in.nextInt();
		in.close();
		
		
		TalkBoxFrame frame = new TalkBoxFrame(size);
		frame.pack();
		frame.setVisible(true);
		
		
		
	}
	
	

}
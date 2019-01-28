package talkbox;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
public class ConfigApp {
	
	public static void main(String[] args) {
		
		double size;
	
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter number of buttons: ");
		size = in.nextInt();
		in.close();
		
		JButton[] buttons = new JButton[(int)size];
		
		double test = size/7;
		int screens = (int)Math.ceil(test);
		
		for (int i = 0; i < buttons.length;i++) {
			
			
			buttons[i] = new JButton (createImageIcon("button.jpg"));
			
		}
		
		TalkBoxFrame frame = new TalkBoxFrame(buttons);
		frame.pack();
		frame.setVisible(true);
		
		
		
		
		
	}
	
	
	protected static  ImageIcon createImageIcon (String path)    
	{
		java.net.URL imgURL = ConfigApp.class.getResource (path);  
		if (imgURL != null)
		{
			return new ImageIcon (imgURL);
		}
		else
		{
			System.err.println ("Couldn't find file: " + path);
			return null;
		}
	}

}

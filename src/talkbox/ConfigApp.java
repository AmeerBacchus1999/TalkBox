package talkbox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ConfigApp extends JFrame implements TalkBoxConfiguration, ActionListener { 
	
	private JButton enter;
	private JTextField entNumB;
	public static int numButtons;
	private JLabel numBLabel; 
	
	
	public ConfigApp() {
		super ("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//For the Welcome Screen
		numBLabel = new JLabel("Enter the number of buttons on the device: ");
		enter = new JButton("ENTER");
		entNumB = new JTextField(3); //only enter 3 digit number
		
		enter.addActionListener(this);
		entNumB.addActionListener(this);
		
		entNumB.setBounds(10, 45, 200, 10);
		
		//write object and read object to the file ....for serialization object (contains all fields for the object)
		
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.white);
		contentPane.setPreferredSize(new Dimension(300, 100));
		contentPane.add(numBLabel, BorderLayout.NORTH);
		contentPane.add(entNumB, BorderLayout.CENTER);
		contentPane.add(enter, BorderLayout.SOUTH);
		setContentPane(contentPane);
	}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == enter) {
			String text = entNumB.getText();
		    entNumB.selectAll();
		    numButtons = Integer.parseInt(text);
		    TalkBoxFrame frame = new TalkBoxFrame(numButtons);
			frame.pack();
			frame.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		
		
		ConfigApp welFrame = new ConfigApp();
		welFrame.pack();
		welFrame.setVisible(true);
		
		//int size;
		
		//test
		/*Scanner in = new Scanner(System.in);
		System.out.print("Enter number of buttons: ");
		size = in.nextInt();
		in.close();
		
		
		TalkBoxFrame frame = new TalkBoxFrame(numButtons);
		frame.pack();
		frame.setVisible(true);
		*/ 
		
		
	}




	@Override
	public int getNumberOfAudioButtons() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
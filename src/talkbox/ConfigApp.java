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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ConfigApp extends JFrame implements TalkBoxConfiguration, ActionListener {
	
	/**
	 * 
	 */
	private JButton enter;
	private JTextField numButtons;
	
	public ConfigApp() {
		super ("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		enter = new JButton("ENTER");
		numButtons = new JTextField(3); //only enter 3 digit number
		
		enter.addActionListener(this);
		
		JPanel welPanel = new JPanel();
		welPanel.setBackground(Color.cyan);
		welPanel.setPreferredSize(new Dimension(200, 100));
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(welPanel);
		contentPane.add(enter, BorderLayout.SOUTH);
		setContentPane(contentPane);
	}
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == enter) {
			
		}
	}
	
	public static void main(String[] args) {
		
		
		ConfigApp welFrame = new ConfigApp();
		welFrame.pack();
		welFrame.setVisible(true);
		
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
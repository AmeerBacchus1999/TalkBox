
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class TalkBoxFrame extends JFrame implements ActionListener {
	

	//These are the images for the buttons below (B1-B7) (not actual buttons)

	int i =0;

	private JButton washroom;
	private JButton food;
	private JButton drink;
	private JButton play;
	private JButton sick;
	private JButton tired;
	private JButton record_pic;
	
	//B1-B6 are the buttons for the words/expressions, which will have pre-recorded audio files attached to them 
	private JButton B1;
	private JButton B2;
	private JButton B3;
	private JButton B4;
	private JButton B5;
	private JButton B6;
	private JButton B7; //This will act as the recording/search button
	private TalkBoxPanel canvas;//This sets up the panel to 
	
	
	
	public TalkBoxFrame() {
		
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new TalkBoxPanel();
		setContentPane(canvas); 
		setResizable(false);//prevents user from resizing the window
		
		washroom = new JButton (createImageIcon("washroom.jpg"));
		washroom.setBackground(Color.WHITE);
		food = new JButton (createImageIcon("food.jpg"));
		food.setBackground(Color.WHITE);
		drink = new JButton (createImageIcon("drink.jpg"));
		drink.setBackground(Color.WHITE);
		play = new JButton (createImageIcon("play.jpg"));
		play.setBackground(Color.WHITE);
		sick = new JButton (createImageIcon("sick.jpg"));
		sick.setBackground(Color.WHITE);
		tired = new JButton (createImageIcon("tired.jpg"));
		tired.setBackground(Color.WHITE);
		record_pic = new JButton (createImageIcon("record_pic.jpg"));
		record_pic.setBackground(Color.WHITE);
		
		B1 = new JButton (createImageIcon("button.jpg"));
		B1.addActionListener(this);
		B2 = new JButton (createImageIcon("button.jpg"));
		B2.addActionListener(this);
		B3 = new JButton (createImageIcon("button.jpg"));
		B3.addActionListener(this);
		B4 = new JButton (createImageIcon("button.jpg"));
		B4.addActionListener(this);
		B5 = new JButton (createImageIcon("button.jpg"));
		B5.addActionListener(this);
		B6 = new JButton (createImageIcon("button.jpg"));
		B6.addActionListener(this);
		B7 = new JButton (createImageIcon("button.jpg"));
		B7.addActionListener(this);
		
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,7));
		buttonPanel.setPreferredSize(new Dimension(1000,300));
		
		buttonPanel.add(B1);
		buttonPanel.add(B2);
		buttonPanel.add(B3);
		buttonPanel.add(B4);
		buttonPanel.add(B5);
		buttonPanel.add(B6);
		buttonPanel.add(B7);
		
		buttonPanel.add(washroom);
		buttonPanel.add(food);
		buttonPanel.add(drink);
		buttonPanel.add(play);
		buttonPanel.add(sick);
		buttonPanel.add(tired);
		buttonPanel.add(record_pic);
		
		setContentPane(buttonPanel);//adds this panel to the window	
	}
	
	
	
	//This method listens for the button to be pressed
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		Object source = ae.getSource();
		File button = new File("button_sound.wav"); //Creates new file with the sound we want
		
		if (source == B1) {
			
			System.out.println("Washroom");
			play_sound(button);
			
		}
		else if (source == B2) {
			
			System.out.println("Food");
			play_sound(button);
		}
		else if (source == B3) {
			
			System.out.println("Drink");
			play_sound(button);
		}
		else if (source == B4) {
			
			System.out.println("Play");
			play_sound(button);
		}
		else if (source == B5) {
			
			System.out.println("Sick");
			play_sound(button);
		}
		else if (source == B6) {
			
			System.out.println("Tired");
			play_sound(button);
		}
		else if (source == B7) {
			
			System.out.println("Record");
			play_sound(button);
		}
	}
	
	public static void play_sound(File Sound) {
		
		
		try {
			
			Clip clip = AudioSystem.getClip();	//Initializes new audio clip to be played
			clip.open(AudioSystem.getAudioInputStream(Sound));	//Loads the sound we want to play
			clip.start();	//Starts playing the clip
			
		}
		
		catch(Exception e){
			
			System.out.println("Audio File Not Found");
			
		}
		
		
	}
	
	protected static  ImageIcon createImageIcon (String path)    
	{
		java.net.URL imgURL = TalkBoxFrame.class.getResource (path);  
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
	



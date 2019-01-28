package talkbox;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class TalkBoxFrame extends JFrame implements ActionListener {
	
	//test
	//These are the images for the buttons below (B1-B7) (not actual buttons)

	Panel canvas;//This sets up the panel to
	Panel screen2;
	
	public JPanel buttonPanel;
	public JPanel buttonPanel1;
	
	CardLayout cdLayout = new CardLayout ();
	
	public JButton[] buttons;
	
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
	private JButton white1;
	private JButton white2;
	private JButton left_arrow;
	private JButton right_arrow;
	private JButton left;
	
	
	
	
	
	public TalkBoxFrame(JButton[] buttons) {
		
		super();
		
		this.buttons = new JButton[buttons.length];
		for (int i = 0; i < buttons.length;i++) {
			
			this.buttons[i] = buttons[i];
			
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Panel();
		
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
		
		white1 = new JButton (createImageIcon("sound.gif"));
		white2 = new JButton (createImageIcon("sound.gif"));
		
	
		
	
		left_arrow = new JButton (createImageIcon("left_arrow.jpg"));
		left_arrow.addActionListener(this);
		right_arrow = new JButton (createImageIcon("right_arrow.jpg"));
		right_arrow.addActionListener(this);
		
		
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new GridLayout(2,9));
		buttonPanel.setPreferredSize(new Dimension(1200,300));
		
		buttonPanel.add(white1);
		buttonPanel.add(B1);
		buttonPanel.add(B2);
		buttonPanel.add(B3);
		buttonPanel.add(B4);
		buttonPanel.add(B5);
		buttonPanel.add(B6);
		buttonPanel.add(B7);
		buttonPanel.add(white2);
		
		buttonPanel.add(left_arrow);
		buttonPanel.add(washroom);
		buttonPanel.add(food);
		buttonPanel.add(drink);
		buttonPanel.add(play);
		buttonPanel.add(sick);
		buttonPanel.add(tired);
		buttonPanel.add(record_pic);
		buttonPanel.add(right_arrow);
		
		System.out.println(this.buttons.length);
		setContentPane(buttonPanel);//adds this panel to the window	
		

		
		
		
		
		int counter = 0;
		
		buttonPanel1 = new JPanel();
		buttonPanel1.setLayout(new GridLayout(2,9));
		buttonPanel1.setPreferredSize(new Dimension(1200,300));
		
		JButton s1 = new JButton(createImageIcon("sound.gif"));
		buttonPanel1.add(s1);
		
		for (int i = 0; i < this.buttons.length;i++) {
			
			buttonPanel1.add(this.buttons[i]);
			counter++;
			
		}
	
		JButton[] fillers = new JButton[18-this.buttons.length];
		
		for (int j = 0; j < 15-this.buttons.length;j++) {
			
			if (counter == 7) {
				JButton s2 = new JButton(createImageIcon("sound.gif"));
				buttonPanel1.add(s2);
				left = new JButton(createImageIcon("left_arrow.jpg"));
				left.addActionListener(this);
				buttonPanel1.add(left);
				
			}
			
			
			else {
			 fillers[j] = new JButton(createImageIcon("white.jpg"));
			 buttonPanel1.add(fillers[j]);
			}
			counter++;
			
		}
		
		
			
		JButton right = new JButton(createImageIcon("right_arrow.jpg"));
		buttonPanel1.add(right);
			
			
			
		
		
		buttonPanel1.setVisible(false);
		
	
	}
	

	
	
	
	//This method listens for the button to be pressed
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		Object source = ae.getSource();
		//File button = new File("button_sound.wav"); //Creates new file with the sound we want
		URL url = getClass().getResource("button_sound.wav");
		File button = new File(url.getPath());
		
	
		URL url2 = getClass().getResource("click.wav");
		File click = new File(url2.getPath());
		
		
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
		
		
		else if (source == left_arrow) {
			
			System.out.println("left arrow");
			play_sound(click);
		}
		
		else if (source == right_arrow) {
			
			buttonPanel.setVisible(false);
		    setContentPane(buttonPanel1);
			buttonPanel1.setVisible(true);
			System.out.println("right arrow");
			play_sound(click);
		}
		else if (source == left) {
			
			buttonPanel.setVisible(true);
		    setContentPane(buttonPanel);
			buttonPanel1.setVisible(false);
			System.out.println("left arrow");
			play_sound(click);
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
	


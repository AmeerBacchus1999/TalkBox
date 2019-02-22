package talkbox;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class TalkBoxFrame extends JFrame implements ActionListener {

	// test
	// These are the images for the buttons below (B1-B7) (not actual buttons)

	Panel canvas;// This sets up the panel to
	Panel screen2;

	public JPanel buttonPanel;
	public JPanel BP;
	public JPanel[] buttonPanels;
	public int buttonPanels_counter = 0;
	public JButton[] left_arrows;

	public int left_counter = 0;
	public int right_counter = 0;

	public int size;
	public int size_rounded;

	public JButton[] right_arrows;

	public JButton[] sound_gif;
	public int sound_counter = 0;

	public JButton[] fillers;
	public int filler_counter = 0;

	public JButton[] buttons;
	public int button_counter = 0;

	CardLayout cdLayout = new CardLayout();

	private JButton washroom;
	private JButton food;
	private JButton drink;
	private JButton play;
	private JButton sick;
	private JButton tired;
	private JButton record_pic;

	// B1-B6 are the buttons for the words/expressions, which will have pre-recorded
	// audio files attached to them
	private JButton B1;
	private JButton B2;
	private JButton B3;
	private JButton B4;
	private JButton B5;
	private JButton B6;
	private JButton B7; // This will act as the recording/search button
	private JButton sound1;
	private JButton sound2;
	private JButton left_arrow;
	private JButton right_arrow;

	public TalkBoxFrame(int size) {

		super();

		this.size = size;

		double panels = (double) size / 7;

		size_rounded = (int) Math.ceil(panels);

		buttonPanels = new JPanel[size_rounded];
		left_arrows = new JButton[size_rounded];

		right_arrows = new JButton[size_rounded];

		for (int i = 0; i < size_rounded; i++) {

			left_arrows[i] = new JButton(createImageIcon("left_arrow.jpg"));
			left_arrows[i].addActionListener(this);

			right_arrows[i] = new JButton(createImageIcon("right_arrow.jpg"));
			right_arrows[i].addActionListener(this);
		}

		sound_gif = new JButton[size_rounded * 2];

		for (int t = 0; t < size_rounded * 2; t++) {

			sound_gif[t] = new JButton(createImageIcon("sound.gif"));

		}

		fillers = new JButton[(size_rounded + 1) * 7];

		for (int y = 0; y < (size_rounded + 1) * 7; y++) {

			fillers[y] = new JButton(createImageIcon("white.jpg"));

		}

		buttons = new JButton[size_rounded * 7];

		for (int g = 0; g < size_rounded * 7; g++) {

			buttons[g] = new JButton(createImageIcon("button.jpg"));
			buttons[g].addActionListener(this);
		}

		System.out.println(this.size);
		System.out.println(size_rounded);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Panel();

		setContentPane(canvas);
		setResizable(false);// prevents user from resizing the window

		washroom = new JButton(createImageIcon("washroom.jpg"));
		washroom.setBackground(Color.WHITE);
		food = new JButton(createImageIcon("food.jpg"));
		food.setBackground(Color.WHITE);
		drink = new JButton(createImageIcon("drink.jpg"));
		drink.setBackground(Color.WHITE);
		play = new JButton(createImageIcon("play.jpg"));
		play.setBackground(Color.WHITE);
		sick = new JButton(createImageIcon("sick.jpg"));
		sick.setBackground(Color.WHITE);
		tired = new JButton(createImageIcon("tired.jpg"));
		tired.setBackground(Color.WHITE);
		record_pic = new JButton(createImageIcon("record_pic.jpg"));
		record_pic.setBackground(Color.WHITE);

		B1 = new JButton(createImageIcon("button.jpg"));
		B1.addActionListener(this);
		B2 = new JButton(createImageIcon("button.jpg"));
		B2.addActionListener(this);
		B3 = new JButton(createImageIcon("button.jpg"));
		B3.addActionListener(this);
		B4 = new JButton(createImageIcon("button.jpg"));
		B4.addActionListener(this);
		B5 = new JButton(createImageIcon("button.jpg"));
		B5.addActionListener(this);
		B6 = new JButton(createImageIcon("button.jpg"));
		B6.addActionListener(this);
		B7 = new JButton(createImageIcon("button.jpg"));
		B7.addActionListener(this);

		sound1 = new JButton(createImageIcon("sound.gif"));
		sound2 = new JButton(createImageIcon("sound.gif"));

		left_arrow = new JButton(createImageIcon("left_arrow.jpg"));
		left_arrow.addActionListener(this);
		right_arrow = new JButton(createImageIcon("right_arrow.jpg"));
		right_arrow.addActionListener(this);

		buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(2, 9));
		buttonPanel.setPreferredSize(new Dimension(1200, 300));

		buttonPanel.add(sound1);
		buttonPanel.add(B1);
		buttonPanel.add(B2);
		buttonPanel.add(B3);
		buttonPanel.add(B4);
		buttonPanel.add(B5);
		buttonPanel.add(B6);
		buttonPanel.add(B7);
		buttonPanel.add(sound2);

		buttonPanel.add(left_arrow);
		buttonPanel.add(washroom);
		buttonPanel.add(food);
		buttonPanel.add(drink);
		buttonPanel.add(play);
		buttonPanel.add(sick);
		buttonPanel.add(tired);
		buttonPanel.add(record_pic);
		buttonPanel.add(right_arrow);

		setContentPane(buttonPanel);// adds this panel to the window

		for (int k = 0; k < size_rounded; k++) {

			if (k == size_rounded - 1) {

				makeFinalButtonPanel();
				break;
			}

			makeButtonPanel();

		}

	}

	public void makeFinalButtonPanel() {

		buttonPanels[buttonPanels_counter] = new JPanel();
		buttonPanels[buttonPanels_counter].setLayout(new GridLayout(2, 9));
		buttonPanels[buttonPanels_counter].setPreferredSize(new Dimension(1200, 300));

		buttonPanels[buttonPanels_counter].add(sound_gif[sound_counter]);
		sound_counter++;
		int left_over = size - (size_rounded - 1) * 7;

		for (int i = 0; i < left_over; i++) {

			buttonPanels[buttonPanels_counter].add(buttons[button_counter]);
			button_counter++;

		}

		int rest = 15 - left_over;

		for (int j = 0; j < rest; j++) {

			if (j == 7 - left_over) {

				buttonPanels[buttonPanels_counter].add(sound_gif[sound_counter]);
				sound_counter++;

				buttonPanels[buttonPanels_counter].add(left_arrows[left_counter]);
				left_counter++;

			}

			else {

				buttonPanels[buttonPanels_counter].add(fillers[filler_counter]);
				filler_counter++;
			}

		}

		buttonPanels[buttonPanels_counter].add(right_arrows[right_counter]);
		right_counter++;

		buttonPanels[buttonPanels_counter].setVisible(false);

	}

	public void makeButtonPanel() {

		buttonPanels[buttonPanels_counter] = new JPanel();
		buttonPanels[buttonPanels_counter].setLayout(new GridLayout(2, 9));
		buttonPanels[buttonPanels_counter].setPreferredSize(new Dimension(1200, 300));

		buttonPanels[buttonPanels_counter].add(sound_gif[sound_counter]);
		sound_counter++;

		for (int i = 0; i < 7; i++) {

			buttonPanels[buttonPanels_counter].add(buttons[button_counter]);
			button_counter++;

		}

		for (int j = 0; j < 8; j++) {

			if (j == 0) {

				buttonPanels[buttonPanels_counter].add(sound_gif[sound_counter]);
				sound_counter++;

				buttonPanels[buttonPanels_counter].add(left_arrows[left_counter]);
				left_counter++;

			}

			else {

				buttonPanels[buttonPanels_counter].add(fillers[filler_counter]);
				filler_counter++;
			}

		}

		buttonPanels[buttonPanels_counter].add(right_arrows[right_counter]);
		right_counter++;

		buttonPanels[buttonPanels_counter].setVisible(false);
		buttonPanels_counter++;

	}

	// This method listens for the button to be pressed
	@Override
	public void actionPerformed(ActionEvent ae) {

		Object source = ae.getSource();
		// File button = new File("button_sound.wav"); //Creates new file with the sound
		// we want
		URL url = getClass().getResource("button_sound.wav");
		File button = new File(url.getPath());

		URL url2 = getClass().getResource("click.wav");
		File click = new File(url2.getPath());

		if (source == B1) {

			System.out.println("Washroom");
			play_sound(button);

		} else if (source == B2) {

			System.out.println("Food");
			play_sound(button);
		} else if (source == B3) {

			System.out.println("Drink");
			play_sound(button);
		} else if (source == B4) {

			System.out.println("Play");
			play_sound(button);
		} else if (source == B5) {

			System.out.println("Sick");
			play_sound(button);
		} else if (source == B6) {

			System.out.println("Tired");
			play_sound(button);
		} else if (source == B7) {

			System.out.println("Record");
			play_sound(button);
		}

		else if (source == right_arrow) {

			buttonPanel.setVisible(false);
			setContentPane(buttonPanels[0]);
			buttonPanels[0].setVisible(true);

			play_sound(click);
		} else if (source == left_arrows[0]) {

			buttonPanels[0].setVisible(false);

			setContentPane(buttonPanel);
			buttonPanel.setVisible(true);

			play_sound(click);
		}

		for (int k = 0; k < size; k++) {

			if (source == buttons[k]) {

				play_sound(button);

			}

		}

		for (int i = 0; i < size_rounded; i++) {

			if (source == left_arrows[i] && i != 0) {

				play_sound(click);
				buttonPanels[i].setVisible(false);

				setContentPane(buttonPanels[i - 1]);
				buttonPanels[i - 1].setVisible(true);
			}

			else if (source == right_arrows[i] && i != size_rounded - 1) {

				play_sound(click);
				buttonPanels[i].setVisible(false);

				setContentPane(buttonPanels[i + 1]);
				buttonPanels[i + 1].setVisible(true);

			}

		}

	}

	public static void play_sound(File Sound) {

		try {

			Clip clip = AudioSystem.getClip(); // Initializes new audio clip to be played
			clip.open(AudioSystem.getAudioInputStream(Sound)); // Loads the sound we want to play
			clip.start(); // Starts playing the clip

		}

		catch (Exception e) {

			System.out.println("Audio File Not Found");

		}

	}

	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TalkBoxFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public static void main(String[] args) {
		//String filename_arg = args[0];
		String filename = ConfigApp.PATH + "\\TalkBoxObject.tbc";
		TalkBoxConfiguration config = null;
		//ConfigApp config = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			config = (TalkBoxConfiguration) in.readObject();
			//config = (ConfigApp) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		System.out.println(config.getNumberOfAudioButtons());
		
	}
}

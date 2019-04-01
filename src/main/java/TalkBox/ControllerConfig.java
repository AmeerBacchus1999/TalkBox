package main.java.TalkBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class ControllerConfig implements ActionListener {
	
	private int num_AudioButtons;
	private int num_AudioSets;
	
	
	private JFrame window;
	private JPanel screens;
	private CardLayout cl;
	
	
	private Clip[][] clips;
	private SetButton[][] data;
	private JButton[][] AudioButtons;
	private JButton[] AudioSets;
	private JPanel[] Panels;
	private final JScrollPane[] ScrollPanes;
	
	public ConfigurationApp TalkBox;

	
	
	
	public ControllerConfig(ConfigurationApp TalkBox) {
		
		
		this.num_AudioButtons = TalkBox.getNumberOfAudioButtons();
		this.num_AudioSets = TalkBox.getNumberOfAudioSets();
		
		this.TalkBox = TalkBox;
		
		AudioSets = new JButton[num_AudioSets];
		
		AudioButtons = new JButton[num_AudioSets][num_AudioButtons];
		data = new SetButton[num_AudioSets][num_AudioButtons];
		clips = new Clip[num_AudioSets][num_AudioButtons];
		
		ScrollPanes = new JScrollPane[num_AudioSets];
		
		Panels = new JPanel[num_AudioSets];
		
		cl = new CardLayout();
		screens = new JPanel();
		screens.setLayout(cl);
		
		
		for (int i = 0; i < num_AudioSets;i++) {
			
			for (int j = 0; j < num_AudioButtons;j++) {
			
			AudioButtons[i][j] = new JButton(""+(j+1));
			
		
			AudioButtons[i][j].setPreferredSize(new Dimension(100,100));
			AudioButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			AudioButtons[i][j].addActionListener(this);
			data[i][j] = new SetButton(AudioButtons[i][j],this.TalkBox.audioSets[i][j]);

			new DropTarget(AudioButtons[i][j],data[i][j]);
			
			try {
				clips[i][j] = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
			
			Panels[i] = new JPanel();
			Panels[i].setPreferredSize(new Dimension(320,(num_AudioButtons/3*100)+(num_AudioButtons/3*50)));
			Panels[i].setBackground(Color.LIGHT_GRAY);
			Panels[i].setLayout(new FlowLayout());
			
			ScrollPanes[i] = new JScrollPane(Panels[i], JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			screens.add(ScrollPanes[i],""+i);
		}
		
		int counter = 0;
		for (int i = 0; i < num_AudioSets;i++) {
			
			for (int j = 0; j < (num_AudioButtons);j++) {
				
				if (counter == 3) {
					
					Panels[i].add(new JLabel("                                                                                                        "));
					counter = 0;
				}
				
				Panels[i].add(AudioButtons[i][j]);
				counter++;
		
			}
			
			counter = 0;
			
		}
		
		window = new JFrame("                                    Audio Buttons                                                                                                                 Audio Sets");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setSize(800,500);
		
		
		JPanel AudioButtonsPanel = new JPanel(new FlowLayout());
		AudioButtonsPanel.setPreferredSize(new Dimension(320,(num_AudioButtons/3*100)+(num_AudioButtons/3*50)));
		AudioButtonsPanel.setBackground(Color.LIGHT_GRAY);
		AudioButtonsPanel.setLayout(new FlowLayout());
		
		JPanel AudioSetsPanel = new JPanel();
		AudioSetsPanel.setPreferredSize(new Dimension(100,(num_AudioSets*50)+(num_AudioSets*25)));
		AudioSetsPanel.setBackground(Color.GRAY);
		AudioSetsPanel.setLayout(new FlowLayout());
	

	
        
		final JScrollPane AudioSets_Scroll = new JScrollPane(AudioSetsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		
	
		
		
		for (int i = 0; i < (num_AudioSets);i++) {
			
			AudioSets[i] = new JButton(""+(i+1));
			AudioSets[i].addActionListener(this);
			AudioSets[i].setPreferredSize(new Dimension(50,50));
			AudioSets[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
			AudioSetsPanel.add(new JLabel("                                 "));
			AudioSetsPanel.add(AudioSets[i]);
			AudioSetsPanel.add(new JLabel("                                                                                                                   "));
			
		}
		
		AudioSets[0].setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
	
	
		
		GridLayout layout = new GridLayout(0,2);
		window.setLayout(layout);
		
		window.add(BorderLayout.EAST,screens);
	
		window.add(BorderLayout.WEST,AudioSets_Scroll);
		
		
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		
		
	}

	private void play_sound(URL Sound,int i1,int i2) {

		for (int i = 0; i < this.num_AudioSets;i++) {
			
			
			for (int j = 0; j < this.num_AudioButtons;j++) {
			
			if (clips[i][j].isOpen()) {
				
				if (clips[i][j].isRunning() == true) {
				
				clips[i][j].stop();
				try {
					clips[i][j] = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			}
		
			}
		}
		
		try {
		
			clips[i1][i2].open(AudioSystem.getAudioInputStream(Sound));
			clips[i1][i2].start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	
	
public void actionPerformed(ActionEvent ae) {
		
		Object source = ae.getSource();
		
		
	
		
		
		
		
		for (int i = 0; i < this.num_AudioSets;i++) {
			
			
			
			for (int j = 0; j < this.num_AudioButtons;j++) {
				
				if (source == AudioButtons[i][j]) {
					
				
				
					
					try {
					
						
						clips[i][j].stop();
						clips[i][j] = AudioSystem.getClip();
					
						play_sound(data[i][j].sound.toURL(),i,j);
						}
						catch(Exception e) {
							
							System.out.println("Audio not Found. Please make sure it is a .wav file.");
						}
		
				
				}
			}
			
			
			
			
			
			if (source == AudioSets[i]) {
				
				for (int k = 0; k < this.num_AudioSets;k++) {
					
					AudioSets[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				AudioSets[i].setBorder(BorderFactory.createLineBorder(Color.GREEN,4));
				cl.show(screens,""+i);
				
			
				
				
				
				
			}
		}
		
	
		
		
		
}
	
	

}

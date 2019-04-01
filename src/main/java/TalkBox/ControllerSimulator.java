package main.java.TalkBox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.net.MalformedURLException;
import java.net.URL;


public class ControllerSimulator implements ActionListener {
	private List<JButton> buttons;
	private ConfigurationApp configApp;
	private JFrame frame;
	private JPanel panel;

	
	private Clip clip;
	
	public ControllerSimulator(TalkBoxConfiguration tbc)
	{
		this.configApp = new ConfigurationApp(tbc);
		this.setUpView();
	}
	
	public ControllerSimulator(ConfigurationApp c)
	{
		this.configApp = c;
		this.setUpView();
	}
	
	private void setUpView()
	{
		this.panel = new JPanel();
		//this.panel.setPreferredSize(new Dimension(500, 500));
		this.frame = new JFrame("Simulator");
		this.frame.setPreferredSize(new Dimension(1000, 175));
		this.frame.setLayout(new FlowLayout());
		this.panel.setLayout(
				new FlowLayout());
		this.buttons = new ArrayList<JButton>(this.configApp.getTotalNumberOfButtons());
		
		
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Error getting clip");
		}
		
		

		
		for(int i = 0; i < configApp.getTotalNumberOfButtons(); i++)
		{
			JButton jb = new JButton();
			jb.setVisible(true);
			jb.addActionListener(this);
			jb.setPreferredSize(new Dimension(100,100));
			this.buttons.add(jb);
			this.panel.add(jb);
			if(i < configApp.getNumberOfAudioButtons())
			{
				
				
				if (configApp.getAudioButtons()[i].getImage() == null) {
				
				
				jb.setText("<html>AUDIO<br />"+"&nbsp;&nbsp;&nbsp;&nbsp;"+(i+1)+"<br/></html>");
				}
				
				else {
					
					BufferedImage picture = null;
					
					try {
						
						
						picture = ImageIO.read(new File(configApp.getAudioButtons()[i].getImage()));
						
					}
					
					catch(Exception e) {
						
					}
					
					ImageIcon icon = new ImageIcon(picture);
					
					Image image = icon.getImage();
					Image newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
					
					icon = new ImageIcon(newImage);
					this.buttons.get(i).setText("");
					this.buttons.get(i).setIcon(icon);
					}
					
	
					
					
				}
			
			else
			{
				jb.setText("<html>SWAP<br />"+"&nbsp;&nbsp;&nbsp;&nbsp;"+(i+1-configApp.getNumberOfAudioButtons())+"<br/></html>");
			}
			//this.panel.revalidate();
			//this.panel.repaint();
		}
		final JScrollPane Scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.frame.add(Scroll);
		this.frame.setContentPane(Scroll);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
	}
	
	
	private void play_sound(URL Sound) {
		
		
		if (clip.isOpen()) {
			
			if (clip.isRunning() == true) {
				
				clip.stop();
				
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		try {
			
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
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


	
	
	
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		for (int k = 0; k < this.configApp.getTotalNumberOfButtons();k++) {
			
			if (e.getSource() == this.buttons.get(k)) {
				
				int buttonNumber = this.buttons.indexOf(e.getSource()) + 1;
		
				
				if(k < configApp.getNumberOfAudioButtons())
				{
					
					try {
						
						
						
						clip.stop();
						try {
						
						
							
							clip = AudioSystem.getClip();
							
							String url = this.configApp.getAudioButtons()[k].getAudio();
							play_sound((new File(url).toURL()));
							
						} catch (LineUnavailableException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					
						
						
					}
					
					catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error");
					} catch(NullPointerException e2)
					{
						System.out.println("Audio File Not Found. Please remember to use a .wav file.");
					}
						
					
		
						
			
				}
				else
				{
					configApp.updateAudioSetSwapButton(buttonNumber - configApp.getNumberOfAudioButtons());
					
					for (int p = 0; p < configApp.getNumberOfAudioButtons();p++) {
						
						
						
						if (configApp.getAudioButtons()[p].getImage() != null) {
						
						BufferedImage picture = null;
						
						try {
							
							
							picture = ImageIO.read(new File(configApp.getAudioButtons()[p].getImage()));
							
						}
						
						catch(Exception e2) {
							System.out.println("Picture Error");
						}
						
						ImageIcon icon = new ImageIcon(picture);
						
						Image image = icon.getImage();
						Image newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
						
						icon = new ImageIcon(newImage);
						this.buttons.get(p).setText("");
						this.buttons.get(p).setIcon(icon);
						}
						
						
						else {
							
							
						this.buttons.get(p).setIcon(null);
				
					    this.buttons.get(p).setText("<html>AUDIO<br />"+"&nbsp;&nbsp;&nbsp;&nbsp;"+(p+1)+"<br/></html>");
						}
						
					}
						
						
					
					
				}
			}
			
			
				
				
			}
			
			
			
			
		}
	
	public static void main(String[] args)
	{
		
		ConfigurationApp c = new ConfigurationApp(2, 5, 3);	
		c.serialize("Test1ControllerSimulator");
		TalkBoxConfiguration t = ConfigurationApp.unserializeInterface("Test1ControllerSimulator.tbc");
		ControllerSimulator co = new ControllerSimulator(t);
		
	}
	
}

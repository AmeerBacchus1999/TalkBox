package main.java.TalkBox;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControllerSimulator implements ActionListener {
	private List<JButton> buttons;
	private ConfigurationApp configApp;
	private JFrame frame;
	private JPanel panel;
	
	public ControllerSimulator(TalkBoxConfiguration tbc)
	{
		this.configApp = new ConfigurationApp(tbc.getNumberOfAudioSets(), 
				tbc.getNumberOfAudioButtons(),
				tbc.getTotalNumberOfButtons() - 
				tbc.getNumberOfAudioButtons(), 
				tbc.getAudioFileNames());
		this.panel = new JPanel();
		//this.panel.setPreferredSize(new Dimension(500, 500));
		this.frame = new JFrame();
		//this.frame.setPreferredSize(new Dimension(500, 500));
		this.frame.setLayout(new FlowLayout());
		this.panel.setLayout(
				new FlowLayout());
		this.buttons = new ArrayList<JButton>(tbc.getTotalNumberOfButtons());
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
				jb.setText("AUDIO");
			}
			else
			{
				jb.setText("SWAP");
			}
			//this.panel.revalidate();
			//this.panel.repaint();
		}
		
		this.frame.add(panel);
		this.frame.setContentPane(panel);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(this.buttons.contains(e.getSource()))
		{
			int index = this.buttons.indexOf(e.getSource()) + 1;
			if(index < configApp.getNumberOfAudioButtons())
			{
				AudioButton[] audioSet = configApp.getAudioButtons();
			}
			else
			{
				configApp.updateAudioSetSwapButton(index - configApp.getNumberOfAudioButtons());
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

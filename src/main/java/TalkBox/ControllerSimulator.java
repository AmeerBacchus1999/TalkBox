package main.java.TalkBox;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControllerSimulator implements ActionListener {
	List<JButton> buttons;
	ConfigurationApp configApp;
	JFrame frame;
	JPanel panel;
	
	public ControllerSimulator(TalkBoxConfiguration tbc)
	{
		this.configApp = new ConfigurationApp(tbc.getNumberOfAudioSets(), 
				tbc.getNumberOfAudioButtons(),
				tbc.getTotalNumberOfButtons() - 
				tbc.getNumberOfAudioButtons(), 
				tbc.getAudioFileNames());
		this.panel = new JPanel();
		this.frame = new JFrame();
		this.frame.setLayout(new FlowLayout());
		this.panel.setLayout(
				new BoxLayout(this.panel, BoxLayout.X_AXIS));
		this.buttons = new ArrayList<JButton>(tbc.getTotalNumberOfButtons());
		for(int i = 0; i < tbc.getTotalNumberOfButtons(); i++)
		{
			JButton jb = new JButton();
			jb.addActionListener(this);
			this.buttons.add(jb);
			panel.add(jb);
		}
		this.frame.add(panel);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(this.buttons.contains(e.getSource()))
		{
			int index = this.buttons.indexOf(e.getSource());
			if(index < configApp.getNumberOfAudioButtons())
			{
				AudioButton[] audioSet = configApp.getAudioButtons();
			}
			else
			{
				configApp.updateAudioSetSwapButton(index);
			}
		}
	}
	
}

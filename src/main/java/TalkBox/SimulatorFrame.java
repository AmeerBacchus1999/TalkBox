package main.java.TalkBox;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimulatorFrame extends JFrame {
	List<JButton> buttons;
	JPanel panel;
	
	public SimulatorFrame(int numButtons)
	{
		this.panel = new JPanel();
		this.panel.setLayout(
				new BoxLayout(this.panel, BoxLayout.X_AXIS));
		this.buttons = new ArrayList<JButton>(numButtons);
		for(int i = 0; i < numButtons; i++)
		{
			this.buttons.add(new JButton());
		}
	}
	
	public int index(JButton jb)
	{
		return this.buttons.indexOf(jb);
	}
	
	
	
}

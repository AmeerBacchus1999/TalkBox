package main.java.TalkBox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test implements ActionListener {
	
	private ConfigurationApp config;
	
	private JButton button;
	
	
	public Test(ConfigurationApp config) {
		
		
		this.config = config;
		
	}
	
	
	
	
	
	private void build() {
		
		
		JFrame window = new JFrame();
		window.setSize(200,200);
		button = new JButton("hi");
		button.addActionListener(this);
		window.add(button);
		
		window.setLayout(new GridLayout(1,0));
		
		
	
	
		
		
		
		
		
		window.setVisible(true);
		
		
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		
	/*	ConfigurationApp c = new ConfigurationApp(4, 15, 2);
		new ControllerConfig(c);
		Test test = new Test(c);
		test.build();*/
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource()==button) {
			
			this.config.serialize("Test2ControllerSimulator");
			TalkBoxConfiguration t = ConfigurationApp.unserializeInterface("Test2ControllerSimulator.tbc");
			ControllerSimulator co = new ControllerSimulator(t);
			
			
		}
		
		
		
	}

}

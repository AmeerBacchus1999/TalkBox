
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TalkBoxFrame extends JFrame implements ActionListener {
	
	
	private JButton washroom;
	private JButton food;
	private JButton drink;
	private JButton play;
	private JButton sick;
	private JButton tired;
	
	
	private JButton B1;
	private JButton B2;
	private JButton B3;
	private JButton B4;
	private JButton B5;
	private JButton B6;
	private JButton B7;
	private JButton record_pic;
	private TalkBoxPanel canvas;
	
	
	
	public TalkBoxFrame() {
		
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new TalkBoxPanel();
		
		
		washroom = new JButton (createImageIcon("washroom.jpg"));
		food = new JButton (createImageIcon("food.jpg"));
		drink = new JButton (createImageIcon("drink.jpg"));
		play = new JButton (createImageIcon("play.jpg"));
		sick = new JButton (createImageIcon("sick.jpg"));
		tired = new JButton (createImageIcon("tired.jpg"));
		record_pic = new JButton (createImageIcon("record_pic.jpg"));
		
		B1 = new JButton (createImageIcon("2.jpg"));
		B1.addActionListener(this);
		B2 = new JButton (createImageIcon("2.jpg"));
		B2.addActionListener(this);
		B3 = new JButton (createImageIcon("2.jpg"));
		B3.addActionListener(this);
		B4 = new JButton (createImageIcon("2.jpg"));
		B4.addActionListener(this);
		B5 = new JButton (createImageIcon("2.jpg"));
		B5.addActionListener(this);
		B6 = new JButton (createImageIcon("2.jpg"));
		B6.addActionListener(this);
		B7 = new JButton (createImageIcon("2.jpg"));
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
		
		
		
		setContentPane(buttonPanel);
		
		
		
		
	}
	
	
	@Override
	
	public void actionPerformed(ActionEvent ae) {
		
		Object source = ae.getSource();
		
		if (source == B1) {
			
			System.out.println("Button 1 pressed");
			
		}
		else if (source == B2) {
			
			System.out.println("Button 2 pressed");
			
		}
		else if (source == B3) {
			
			System.out.println("Button 3 pressed");
			
		}
		else if (source == B4) {
			
			System.out.println("Button 4 pressed");
			
		}
		else if (source == B5) {
			
			System.out.println("Button 5 pressed");
			
		}
		else if (source == B6) {
			
			System.out.println("Button 6 pressed");
			
		}
		else if (source == B7) {
			
			System.out.println("Button 7 pressed");
			
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
	



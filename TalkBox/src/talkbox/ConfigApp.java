package talkbox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
public class ConfigApp extends JFrame implements TalkBoxConfiguration, ActionListener { 
	
	private JButton enter;
	private JTextField entNumB;
	private JButton openFile;
	private JLabel fileDescrip ;
	public static int numButtons;
	JFileChooser fc;
	
	
	public ConfigApp() {
		super ("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//For the Welcome Screen

		enter = new JButton("ENTER");
		
		openFile = new JButton("Open files");
		openFile.setAlignmentX(CENTER_ALIGNMENT);//center the button
		
		fileDescrip = new JLabel ("*Name picture files and audio files that go together, with the same name");
		fileDescrip.setLabelFor(openFile);
		
		fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		
		//text field for number of buttons 
		entNumB = new JTextField(10);
		entNumB.setHorizontalAlignment(JTextField.CENTER);
		entNumB.setMargin(new Insets(0, 3, 0, 0));
		entNumB.setMaximumSize(entNumB.getPreferredSize());
		
		//add action listener
		enter.addActionListener(this);
		entNumB.addActionListener(this);
		openFile.addActionListener(this);
		
		//write object and read object to the file ....for serialization object (contains all fields for the object)
		JPanel numBPanel = new JPanel();
		numBPanel.setLayout(new BoxLayout(numBPanel, BoxLayout.Y_AXIS));
	    numBPanel.add(entNumB);
	    numBPanel.setBorder(new TitledBorder(new EtchedBorder(), "Enter the number of buttons on device:"));
		
	    JPanel filePanel = new JPanel();
	    filePanel.setLayout(new BoxLayout(filePanel, BoxLayout.Y_AXIS));
	    filePanel.add(openFile);
	    filePanel.setBorder(new TitledBorder(new EtchedBorder(), "Enter any files you want to use: "));
		
	    JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.gray);
		contentPane.setPreferredSize(new Dimension(300, 125));
	    contentPane.add(numBPanel, BorderLayout.NORTH);
	    contentPane.add(filePanel, BorderLayout.CENTER);
	    contentPane.add(enter, BorderLayout.SOUTH);
		setContentPane(contentPane);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//to get the screen size of any screen 
		int width = dim.width;
		int height = dim.height;
		//contentPane.setLocation(width/2, height/2);
		//this.setSize(width/2, height/2);//to centre on screen
		this.setLocationRelativeTo(null);
	}
		
	
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source == openFile) {
			int returnFile = fc.showOpenDialog(ConfigApp.this);
			if (returnFile == JFileChooser.APPROVE_OPTION) {
				File[] files = fc.getSelectedFiles();
				//want to create a list of opened files
				//contentPane.setPreferredSize(new Dimension(300, 125));// To increase the length of the window to see the list
			}
		}
		else if (source == enter) {
			String text = entNumB.getText();
		    entNumB.selectAll();
		    numButtons = Integer.parseInt(text);
		    TalkBoxFrame frame = new TalkBoxFrame(numButtons);
			frame.pack();
			frame.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		
		
		ConfigApp welFrame = new ConfigApp();
		welFrame.pack();
		welFrame.setVisible(true);
		
		//int size;
		
		//test
		/*Scanner in = new Scanner(System.in);
		System.out.print("Enter number of buttons: ");
		size = in.nextInt();
		in.close();
		
		
		TalkBoxFrame frame = new TalkBoxFrame(numButtons);
		frame.pack();
		frame.setVisible(true);
		*/ 
		
		
	}




	@Override
	public int getNumberOfAudioButtons() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
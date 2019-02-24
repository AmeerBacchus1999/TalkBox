
package talkbox;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
	private static final long serialVersionUID = 4L;
	
	private ArrayList<String[]> audioFileNames = new ArrayList<String[]>();
	private Path pathSer;
	private int numButtons;
	
	private transient JButton enter;
	private transient JButton RunSim;
	private transient JTextField entNumB;
	private transient JLabel fileDescrip ;
	transient JFileChooser fc;
	private transient Path relativePath;
	transient File TalkBoxDataFolder;
	public transient static String PATH = "TalkBoxData"; 
	transient File[] files = new File[0];
	
	public TalkBoxFrame frame;
	public JButton[] pictures;
	public SetButton[] SetButtons;
	
	
	
	
	
	
	
	public ConfigApp() {
		super ("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new File(PATH).mkdir();
		this.TalkBoxDataFolder = new File(PATH);
		this.relativePath = this.TalkBoxDataFolder.toPath();
		
		//For the Welcome Screen

		enter = new JButton("Configure");
		RunSim = new JButton("Launch Simulator");
		RunSim.addActionListener(this);
		
		
		//text field for number of buttons 
		entNumB = new JTextField(10);
		entNumB.setHorizontalAlignment(JTextField.CENTER);
		entNumB.setMargin(new Insets(0, 3, 0, 0));
		entNumB.setMaximumSize(entNumB.getPreferredSize());
		
		//add action listener
		enter.addActionListener(this);
		entNumB.addActionListener(this);
		//openFile.addActionListener(this);
		
		//write object and read object to the file ....for serialization object (contains all fields for the object)
		JPanel numBPanel = new JPanel();
		numBPanel.setLayout(new BoxLayout(numBPanel, BoxLayout.Y_AXIS));
	    numBPanel.add(entNumB);
	    
	    numBPanel.setBorder(new TitledBorder(new EtchedBorder(), "Enter the number of buttons on device:"));
		
	    
	    JPanel contentPane = new JPanel();
	    
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.gray);
		contentPane.setPreferredSize(new Dimension(300, 100));
	  
	    contentPane.add(numBPanel, BorderLayout.NORTH);
	    contentPane.add(enter, BorderLayout.CENTER);
	    contentPane.add(RunSim, BorderLayout.SOUTH);
	    
		setContentPane(contentPane);
		setResizable(false);
		
		this.setLocationRelativeTo(null);
	}
		
	
	public ConfigApp(int size) {
		

		TalkBoxFrame.check = false;
		
		frame = new TalkBoxFrame(size);
		frame.pack();
		frame.setVisible(true);

		
	}
	
	

	
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		 	if (source == enter) {
			
			String text = entNumB.getText();
		    entNumB.selectAll();
		    numButtons = Integer.parseInt(text);
		    
		    new ConfigApp(numButtons);
		    
			String filename = PATH + "\\TalkBoxObject.tbc";
			System.out.println(filename);
			FileOutputStream fos = null;
			ObjectOutputStream out = null;
			try {
				fos = new FileOutputStream(filename);
				out = new ObjectOutputStream(fos);
				TalkBoxConfiguration t = (TalkBoxConfiguration) this;
				//ConfigApp t = this;
				System.out.println(t.getNumberOfAudioButtons());
				out.writeObject(t);
				out.close();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
			
		}
		
		
		
		
		
		else if (source == RunSim) {
			
			TalkBoxFrame.check = true;
			
			
			
		}
		
	}
	


	public static void main(String[] args) {
		
		
		ConfigApp welFrame = new ConfigApp();
		welFrame.pack();
		welFrame.setVisible(true);

		
	}




	@Override
	public int getNumberOfAudioButtons() {
		// TODO Auto-generated method stub
		return this.numButtons+6;
	}




	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return TalkBoxFrame.Audio_Sets.length;
	}



	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		int audio_buttons = getNumberOfAudioButtons()+1;
		
		int arrows = (int)((double)numButtons/7)*2-2;
		
		return audio_buttons+arrows;
	}




	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return TalkBoxFrame.AudioSets.toPath();
	}




	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return TalkBoxFrame.AudioFileNames;
	}
	
	


}
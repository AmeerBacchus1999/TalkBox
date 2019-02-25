
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
	public static String destination = PATH + "\\AudioFileNames.tbc";
	transient File[] files = new File[0];
	
	public TalkBoxFrame frame;
	public JButton[] pictures;
	public SetButton[] SetButtons;
	
	public String[][] AudioFileNames;
	
	
	
	
	
	public ConfigApp() {
		super ("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new File(PATH).mkdir();
		new File("AudioFiles").mkdir();
		
		
	
		new File("AudioSets").mkdir();
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
		
	
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		 	if (source == enter) {

		 		try {
		 			ObjectInputStream is = new ObjectInputStream(new FileInputStream(destination));
		 		
		 			this.AudioFileNames = (String[][]) is.readObject();
		 			is.close();
		 			
		 			}
		 			catch (FileNotFoundException ex1) {
		 				
		 				System.out.println("File not Found");
		 			}
		 			
		 			catch(IOException ex2) {
		 				
		 				System.out.println("IOException");
		 			}
		 			
		 			catch(ClassNotFoundException ex3) {
		 				
		 				System.out.println("Class not Found");
		 			}
			
			String text = entNumB.getText();
		    entNumB.selectAll();
		    numButtons = Integer.parseInt(text);

		    TalkBoxFrame.check = false;
			
			
			
		    String filename = PATH + "\\TalkBoxObject.tbc";

			System.out.println(filename);
			
			
		
		
			if (TalkBoxFrame.firstTime == true) {
				
			frame = new TalkBoxFrame(numButtons,this.AudioFileNames);
			frame.pack();
			frame.setVisible(true);
			}
		}
		
		
		
		
		
		else if (source == RunSim) {
			
			
			String text = entNumB.getText();
		    entNumB.selectAll();
		    numButtons = Integer.parseInt(text);
		    String filename = PATH + "\\TalkBoxObject.tbc";
			System.out.println(filename);
			
			if (TalkBoxFrame.check == false) {
	
			
			ArrayList<ArrayList<String>> audio_files = TalkBoxFrame.AudioFilesSets;
			this.AudioFileNames = new String[numButtons][];
			if (audio_files != null) {
			
			for (int i = 0; i < numButtons; i++) {
				
				Collections.reverse(audio_files.get(i));
				if (audio_files.get(i).size() == 0) {
					
					this.AudioFileNames[i] = new String[1];
				}
				else {
				this.AudioFileNames[i] = new String[audio_files.get(i).size()];
				}
				for (int k = 0; k < audio_files.get(i).size();k++) {
					
					if (audio_files.get(i).get(k) != null) {
					
					this.AudioFileNames[i][k] = audio_files.get(i).get(k);
					}
				}
						
			}
			
			System.out.println(Arrays.deepToString(this.AudioFileNames));
			
			
		
			
			try {
				
				ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destination));
				os.writeObject(this.AudioFileNames);
				os.close();
			}
			
			catch (FileNotFoundException ex1) {
				
				System.out.println("File not Found");
			}
			
			catch(IOException ex2) {
				
				System.out.println("IOException");
			}
			}
			}
			
			else {
				
				
				
				try {
					ObjectInputStream is = new ObjectInputStream(new FileInputStream(destination));
				
					this.AudioFileNames = (String[][]) is.readObject();
					is.close();
					
					}
					catch (FileNotFoundException ex1) {
						
						System.out.println("File not Found");
					}
					
					catch(IOException ex2) {
						
						System.out.println("IOException");
					}
					
					catch(ClassNotFoundException ex3) {
						
						System.out.println("Class not Found");
					}
				
				
				if (TalkBoxFrame.firstTime == true) {
					frame = new TalkBoxFrame(numButtons,this.AudioFileNames);
					frame.pack();
					frame.setVisible(true);
				}
				
				
				
				
			
			}
			
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
		return this.numButtons;
	}




	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return TalkBoxFrame.Audio_Sets.length;
	}



	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		int audio_buttons = getNumberOfAudioButtons()+7;
		
		int arrows = (int)((double)numButtons/7)*2-2;
		
		return audio_buttons+arrows;
	}




	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		
		this.relativePath = TalkBoxFrame.Audio.toPath();
		
		return this.relativePath;
	}

	


	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		try {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(destination));
	
		this.AudioFileNames = (String[][]) is.readObject();
		is.close();
		
		}
		catch (FileNotFoundException ex1) {
			
			System.out.println("File not Found");
		}
		
		catch(IOException ex2) {
			
			System.out.println("IOException");
		}
		
		catch(ClassNotFoundException ex3) {
			
			System.out.println("Class not Found");
		}
		
		
		
		return this.AudioFileNames;
		
		
		
	}
	

}
	


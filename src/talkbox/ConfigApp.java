package talkbox;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
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
	private transient JTextField entNumB;
	private transient JButton openFile;
	private transient JLabel fileDescrip ;
	transient JFileChooser fc;
	private transient Path relativePath;
	transient File TalkBoxDataFolder;
	public transient static String PATH = "TalkBoxData"; 
	transient File[] files = new File[0];
	
	
	public ConfigApp() {
		super ("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new File(PATH).mkdir();
		this.TalkBoxDataFolder = new File(PATH);
		this.relativePath = this.TalkBoxDataFolder.toPath();
		
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
				files = fc.getSelectedFiles();
				
				//want to create a list of opened files
				//contentPane.setPreferredSize(new Dimension(300, 125));// To increase the length of the window to see the list
			}
			
			
		}
		else if (source == enter) {
			String[] arr = new String[files.length];
			for(int i = 0; i < files.length; i++)
			{
				try {
					Files.copy(files[i].toPath(), new File(PATH + "\\" + files[i].getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
					arr[i] = files[i].getName();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			this.audioFileNames.add(arr);
			
			String text = entNumB.getText();
		    entNumB.selectAll();
		    numButtons = Integer.parseInt(text);
		    TalkBoxFrame frame = new TalkBoxFrame(numButtons);
			frame.pack();
			frame.setVisible(true);
			
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
		return this.numButtons;
	}




	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return this.getAudioFileNames().length;
	}




	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return 18 + (this.numButtons+1)*2 + (9-(this.numButtons+1))*2;
	}




	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return this.pathSer;
	}




	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return this.audioFileNames.toArray(new String[0][0]);
	}
	
	

}
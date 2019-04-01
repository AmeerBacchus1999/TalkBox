
package main.java.TalkBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements ActionListener {
	private static final long serialVersionUID = 4L;

	private Path pathSer;
	private int numAudButtons;
	private int numSwapButtons;
	private int numAudSets;

	private final int WIDTH = 350;// width of welcome screen
	private final int HEIGHT = 220;// height of welcome screen
	
	private boolean firstTime = true;
	private JButton configure;
	private JButton RunSim;
	private JButton record;
	private JTextField entNumB;
	private JTextField entSwap;
	private JTextField entAudSet;
	private JTextField saveFileName;
	private JButton save;
	private JButton load;
	private JButton log;
	private JButton saveButton;
	private JFrame saveProfile;
		
	public ConfigurationApp config;
	public TalkBoxConfiguration talkbox;
	public ControllerConfig figControl;
	
	public File TalkBoxDataFolder;
	public static String loadPath = "TalkBoxData"; 
	

	public Main() {
		super("Welcome");

		/*new File(PATH).mkdir();
		new File("AudioFiles").mkdir();

		new File("AudioSets").mkdir();
		this.TalkBoxDataFolder = new File(PATH);
		this.relativePath = this.TalkBoxDataFolder.toPath();*/

		// For the Welcome Screen
		configure = new JButton("Configure");
		RunSim = new JButton();
		save = new JButton("Save");
		load = new JButton("Load");
		record = new JButton("Record");
		log = new JButton("Logs");
		RunSim = new JButton ("<html><center>Launch<br>Simulator</center></html>");
		
		// text field for number of Audio buttons
		entNumB = new JTextField(10);
		entNumB.setHorizontalAlignment(JTextField.CENTER);
		entNumB.setMargin(new Insets(0, 3, 0, 0));
		entNumB.setMaximumSize(entNumB.getPreferredSize());

		// text field for number of Swap buttons
		entSwap = new JTextField(10);
		entSwap.setHorizontalAlignment(JTextField.CENTER);
		entSwap.setMargin(new Insets(0, 3, 0, 0));
		entSwap.setMaximumSize(entSwap.getPreferredSize());

		// text field for number of Audio Sets
		entAudSet = new JTextField(10);
		entAudSet.setHorizontalAlignment(JTextField.CENTER);
		entAudSet.setMargin(new Insets(0, 3, 0, 0));
		entAudSet.setMaximumSize(entAudSet.getPreferredSize());

		// add action listener
		configure.addActionListener(this);
		RunSim.addActionListener(this);
		entNumB.addActionListener(this);
		entSwap.addActionListener(this);
		entAudSet.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		record.addActionListener(this);
		log.addActionListener(this);

		// Label for the Audio Buttons
		JPanel numBPanel = new JPanel();
		numBPanel.setLayout(new BoxLayout(numBPanel, BoxLayout.Y_AXIS));
		numBPanel.add(entNumB);
		numBPanel.setBorder(new TitledBorder(new EtchedBorder(), "Enter the number of audio buttons on device:"));

		// Label for the Swap Buttons
		JPanel swapPanel = new JPanel();
		swapPanel.setLayout(new BoxLayout(swapPanel, BoxLayout.Y_AXIS));
		swapPanel.add(entSwap);
		swapPanel.setBorder(new TitledBorder(new EtchedBorder(), "Enter the number of swap buttons on device:"));

		// Label for the Audio Sets
		JPanel audSetPanel = new JPanel();
		audSetPanel.setLayout(new BoxLayout(audSetPanel, BoxLayout.Y_AXIS));
		audSetPanel.add(entAudSet);
		audSetPanel.setBorder(new TitledBorder(new EtchedBorder(), "Enter the number of audio sets:"));

		JPanel qPanel = new JPanel();
		qPanel.setLayout(new BorderLayout());
		qPanel.add(numBPanel, BorderLayout.NORTH);
		qPanel.add(swapPanel, BorderLayout.CENTER);
		qPanel.add(audSetPanel, BorderLayout.SOUTH);

		JPanel bPanel = new JPanel();

		bPanel.add(load);
		bPanel.add(save);
		bPanel.add(record);

		JPanel launchPanel = new JPanel();
		launchPanel.setLayout(new BorderLayout());
		launchPanel.add(configure, BorderLayout.WEST);
		launchPanel.add(RunSim);
		launchPanel.add(log, BorderLayout.EAST);

		JPanel contentPane = new JPanel();

		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.gray);
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		contentPane.add(qPanel, BorderLayout.NORTH);
		contentPane.add(bPanel, BorderLayout.CENTER);
		contentPane.add(launchPanel, BorderLayout.SOUTH);

		setContentPane(contentPane);
		pack();

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == load) {
			JFileChooser loadFile = new JFileChooser();
			loadFile.setFileFilter(new FileNameExtensionFilter("TBC files", "tbc"));
			
			new File(loadPath).mkdir();
			this.TalkBoxDataFolder = new File(loadPath);
			
			loadFile.setCurrentDirectory(TalkBoxDataFolder);
			
			loadFile.setDialogTitle("Load Profile");
			
			int returnFile = loadFile.showOpenDialog(this);
			if (returnFile == JFileChooser.APPROVE_OPTION) {
				 String fileLoadName = loadFile.getSelectedFile().getName();
				 talkbox = ConfigurationApp.unserializeInterface(fileLoadName);//CHECK NEED TO BE PASSED IN AS A TALK 
				 ControllerSimulator loaded = new ControllerSimulator(talkbox);
			}
				
			if (returnFile == JFileChooser.CANCEL_OPTION)
				return;

		}

		else if (source == record) {
			RecordButton rec_button = new RecordButton();
		}

		else if (source == save) {
			if (config == null) {
				JOptionPane.showMessageDialog(null, "No Profile opened!", "Warning", JOptionPane.WARNING_MESSAGE );
				return;
			}
			
			saveProfile = new JFrame("Save Profile");
			
			saveFileName = new JTextField (30);
			saveFileName.setHorizontalAlignment(JTextField.CENTER);
			saveFileName.setMargin(new Insets(0, 3, 0, 0));
			saveFileName.setMaximumSize(saveFileName.getPreferredSize());
			
			saveButton = new JButton ("Save");
			
			saveFileName.addActionListener(this);
			saveButton.addActionListener(this);
			
			JPanel savePan = new JPanel();
			savePan.setLayout(new BoxLayout(savePan, BoxLayout.Y_AXIS));
			savePan.add(saveFileName);
			savePan.setBorder(new TitledBorder(new EtchedBorder(), "Enter the name of the profile: "));
			
			
			JPanel savePAN = new JPanel();
			savePAN.setLayout(new BorderLayout());
			savePAN.add(savePan, BorderLayout.NORTH);
			savePAN.add(saveButton, BorderLayout.SOUTH);
			
			saveProfile.setContentPane(savePAN);
			saveProfile.pack();
			saveProfile.setVisible(true);
			saveProfile.setLocationRelativeTo(null);
			
		}
		
		else if (source == saveButton) {
			String text = saveFileName.getText();
			config.serialize(text);
			saveProfile.dispose();
		}

		else if (source == configure) {
			
			//Gives warnings if any of the textfields are blank or if they are <= 0 and only creates the config
			if (configWarning(entNumB, numAudButtons) && configWarning(entSwap, numSwapButtons) && configWarning(entAudSet, numAudSets)) {
				String text = entNumB.getText().toString();
				numAudButtons = Integer.parseInt(text);
				
				String text2 = entSwap.getText().toString();
				numSwapButtons = Integer.parseInt(text2);
				
				String text3 = entAudSet.getText().toString();
				numAudSets = Integer.parseInt(text3);
				if (firstTime == true) {
				config = new ConfigurationApp(numAudSets, numAudButtons, numSwapButtons);
				figControl = new ControllerConfig(config);
				firstTime = false;
				}
				
				else {
					
					figControl = new ControllerConfig(config);
					
				}
			}
		}

		else if (source == RunSim) {
			
			if (firstTime == true) {
				
				if (configWarning(entNumB, numAudButtons) && configWarning(entSwap, numSwapButtons) && configWarning(entAudSet, numAudSets)) {
					String text = entNumB.getText().toString();
					numAudButtons = Integer.parseInt(text);
					
					String text2 = entSwap.getText().toString();
					numSwapButtons = Integer.parseInt(text2);
					
					String text3 = entAudSet.getText().toString();
					numAudSets = Integer.parseInt(text3);
					config = new ConfigurationApp(numAudSets, numAudButtons, numSwapButtons);
				
					firstTime = false;

				}
	
			}
			ControllerSimulator co = new ControllerSimulator(config);
		}
		
		else if (source == log) {
			
		}
	}
	
	public boolean configWarning (JTextField field, int number) {
		String empty = "";
		String text = field.getText().toString();
		field.selectAll();
		if (text.equals(empty)) {
			JOptionPane.showMessageDialog(null, "Fill in all 3 parameters!", "Warning", JOptionPane.WARNING_MESSAGE );
			return false;
		}
		number = Integer.parseInt(text);
		if (number <= 0) {
			JOptionPane.showMessageDialog(null, "Invaild Entry!", "Warning", JOptionPane.WARNING_MESSAGE );
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {

		Main welFrame = new Main();
		welFrame.pack();
		welFrame.setVisible(true);

	}

	}

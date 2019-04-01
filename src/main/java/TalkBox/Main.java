
package main.java.TalkBox;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
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
	
	
	private transient JButton configure;
	private transient JButton RunSim;
	private transient JButton record;
	private transient JTextField entNumB;
	private transient JTextField entSwap;
	private transient JTextField entAudSet;
	private transient JButton save;
	private transient JButton load;
	private transient JButton log;
	
	public ConfigurationApp config;
	private transient Path relativePath;
	transient File TalkBoxDataFolder;
	public transient static String PATH = "TalkBoxData";
	public static String destination = PATH + "\\AudioFileNames.tbc";
	transient File loaded;
	

	public Main() {
		super("Welcome");

		new File(PATH).mkdir();
		new File("AudioFiles").mkdir();

		new File("AudioSets").mkdir();
		this.TalkBoxDataFolder = new File(PATH);
		this.relativePath = this.TalkBoxDataFolder.toPath();

		// For the Welcome Screen
		configure = new JButton("Configure");
		RunSim = new JButton();
		save = new JButton("Save");
		load = new JButton("Load");
		record = new JButton("Record");
		log = new JButton("Logs");

		RunSim.setLayout(new BorderLayout());
		JLabel l1 = new JLabel("Launch");
		JLabel l2 = new JLabel("Simulator");
		RunSim.add(BorderLayout.NORTH, l1);
		RunSim.add(BorderLayout.SOUTH, l2);

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
		launchPanel.add(log);
		launchPanel.add(RunSim, BorderLayout.EAST);

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
			
			loadFile.setDialogTitle("Load Profile");
			
			int returnFile = loadFile.showOpenDialog(this);
			if (returnFile == JFileChooser.APPROVE_OPTION)
				loaded = loadFile.getSelectedFile();
			if (returnFile == JFileChooser.CANCEL_OPTION)
				return;

			if (!Desktop.isDesktopSupported()) {
				System.out.print("Desktop is not supported");
				return;
			}

			Desktop desktop = Desktop.getDesktop();
			if (loaded.exists())
				try {
					desktop.open(loaded);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		else if (source == record) {
			RecordButton rec_button = new RecordButton();
		}

		else if (source == save) {
			JFileChooser saveFile = new JFileChooser();
			saveFile.setFileFilter(new FileNameExtensionFilter("TBC files", "tbc"));
			saveFile.setDialogTitle("Save Profile");

			int file = saveFile.showSaveDialog(this);
			if (file == JFileChooser.APPROVE_OPTION) {
				//should this save directly to the config folder

			}

			if (file == JFileChooser.CANCEL_OPTION)
				return;
		}

		else if (source == configure) {
			
			String empty = "";
			
			//Gives warnings if any of the textfields are blank or if they are <= 0 
			String text = entNumB.getText().toString();
			entNumB.selectAll();
			if (!(text.equals(empty)))
				numAudButtons = Integer.parseInt(text);
			else {
				JOptionPane.showMessageDialog(null, "Fill in all 3 parameters!", "Warning", JOptionPane.WARNING_MESSAGE );
				return;
			}
				
			String text2 = entSwap.getText();
			entSwap.selectAll();
			if (!(text2.equals(empty)))
				numSwapButtons = Integer.parseInt(text2);
			else {
				JOptionPane.showMessageDialog(null, "Fill in all 3 parameters!", "Warning", JOptionPane.WARNING_MESSAGE );
				return;
			}
			
			String text3 = entAudSet.getText();
			entAudSet.selectAll();
			if (!(text3.equals(empty)))
				numAudSets = Integer.parseInt(text3);
			else {
				JOptionPane.showMessageDialog(null, "Fill in All 3 Parameters!", "Warning", JOptionPane.WARNING_MESSAGE );
				return;
			}
			
			if (numAudSets <= 0 || numAudButtons <= 0 || numSwapButtons <= 0) {
				JOptionPane.showMessageDialog(null, "Invaild Entry!", "Warning", JOptionPane.WARNING_MESSAGE );
				return;
			}
			
			config = new ConfigurationApp(numAudSets, numAudButtons, numSwapButtons);
			
		}

		else if (source == RunSim) {

			
		}
		else if (source == log) {
			
		}
	}
	public static void main(String[] args) {

		Main welFrame = new Main();
		welFrame.pack();
		welFrame.setVisible(true);

	}

	}
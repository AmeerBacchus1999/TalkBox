
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends JFrame implements TalkBoxConfiguration, ActionListener {
	private static final long serialVersionUID = 4L;

	private Path pathSer;
	private int numButtons;

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
	private transient JLabel fileDescrip;
	private transient RecordButton rec_button;
	// transient JFileChooser loadFile;
	private transient Path relativePath;
	transient File TalkBoxDataFolder;
	public transient static String PATH = "TalkBoxData";
	public static String destination = PATH + "\\AudioFileNames.tbc";
	transient File[] files = new File[0];
	transient File loaded;
	public TalkBoxFrame frame;
	public JButton[] pictures;
	public SetButton[] SetButtons;

	public String[][] AudioFileNames;

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
			rec_button = new RecordButton();
		}

		else if (source == save) {
			JFileChooser saveFile = new JFileChooser();
			saveFile.setDialogTitle("Save Profile");

			int file = saveFile.showSaveDialog(this);
			if (file == JFileChooser.APPROVE_OPTION) {
				// contents of the file

			}

			if (file == JFileChooser.CANCEL_OPTION)
				return;
		}

		else if (source == configure) {

			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(destination));

				this.AudioFileNames = (String[][]) is.readObject();
				is.close();

			} catch (FileNotFoundException ex1) {

				System.out.println("File not Found");
			}

			catch (IOException ex2) {

				System.out.println("IOException");
			}

			catch (ClassNotFoundException ex3) {

				System.out.println("Class not Found");
			}

			String text = entNumB.getText();
			entNumB.selectAll();
			numButtons = Integer.parseInt(text);

			TalkBoxFrame.check = false;

			String filename = PATH + "\\TalkBoxObject.tbc";

			System.out.println(filename);

			if (TalkBoxFrame.firstTime == true) {

				frame = new TalkBoxFrame(numButtons, this.AudioFileNames);
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
						} else {
							this.AudioFileNames[i] = new String[audio_files.get(i).size()];
						}
						for (int k = 0; k < audio_files.get(i).size(); k++) {

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

					catch (IOException ex2) {

						System.out.println("IOException");
					}
				}
			}

			else {

				try {
					ObjectInputStream is = new ObjectInputStream(new FileInputStream(destination));

					this.AudioFileNames = (String[][]) is.readObject();
					is.close();

				} catch (FileNotFoundException ex1) {

					System.out.println("File not Found");
				}

				catch (IOException ex2) {

					System.out.println("IOException");
				}

				catch (ClassNotFoundException ex3) {

					System.out.println("Class not Found");
				}

				if (TalkBoxFrame.firstTime == true) {
					frame = new TalkBoxFrame(numButtons, this.AudioFileNames);
					frame.pack();
					frame.setVisible(true);
				}

			}

			TalkBoxFrame.check = true;

		}

	}

	public static void main(String[] args) {

		Main welFrame = new Main();
		welFrame.pack();
		welFrame.setVisible(true);

	}

	@Override
	public int getNumberOfAudioButtons() {
		// TODO Auto-generated method stub
		return TalkBoxFrame.Audio_Sets.length;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		int audio_buttons = getNumberOfAudioButtons() + 7;

		int arrows = (int) ((double) numButtons / 7) * 2 - 2;

		return audio_buttons + arrows;
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

		} catch (FileNotFoundException ex1) {

			System.out.println("File not Found");
		}

		catch (IOException ex2) {

			System.out.println("IOException");
		}

		catch (ClassNotFoundException ex3) {

			System.out.println("Class not Found");
		}

		return this.AudioFileNames;

	}

	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return 0;
	}

}

package main.java.TalkBox;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class RecordButton implements ActionListener {
	
	final static Desktop PC = Desktop.getDesktop();
	private JButton recordButton;
	private JTextField recordFileName;
	private JFrame recordWin;
	public File recFile;
	public File TalkBoxRecordingFolder;
	public static String recPath = "TalkBoxRecording"; 
	private boolean isRecording = false;
	private Record newRecord;
	
	public RecordButton() {
		
		recordWin = new JFrame ("Record");
		
		recordFileName = new JTextField (30);
		recordFileName.setHorizontalAlignment(JTextField.CENTER);
		recordFileName.setMargin(new Insets(0, 3, 0, 0));
		recordFileName.setMaximumSize(recordFileName.getPreferredSize());
		
		recordButton = new JButton ("RECORD");
		
		
		recordFileName.addActionListener(this);
		recordButton.addActionListener(this);
		
		JPanel namePan = new JPanel();
		namePan.setLayout(new BoxLayout(namePan, BoxLayout.Y_AXIS));
		namePan.add(recordFileName);
		namePan.setBorder(new TitledBorder(new EtchedBorder(), "Enter the name of the new sound: "));
		
		
		JPanel recordPAN = new JPanel();
		recordPAN.setLayout(new BorderLayout());
		recordPAN.add(namePan, BorderLayout.NORTH);
		recordPAN.add(recordButton, BorderLayout.SOUTH);
		
		recordWin.setContentPane(recordPAN);
		recordWin.pack();
		recordWin.setVisible(true);
		recordWin.setLocationRelativeTo(null);
	}
	
	
	
	//start recording
	public void startRecording() {
		new File(recPath).mkdir();
		this.TalkBoxRecordingFolder = new File(recPath);
		
		String text = this.TalkBoxRecordingFolder.toURI()+recordFileName.getText()+".wav";
		String filename =  text.substring(5, text.length());

		
		recFile = new File (filename);
		newRecord = new Record(recFile);
		
		Thread stopper = new Thread(new Runnable() {
			public void run() {
            	recordButton.setText("STOP");
            	isRecording = true;
            	// start recording
    	        newRecord.start();
            }
        });
 
        stopper.start();
 
	}
	
	//stop recording
	public void stopRecording() {
		
		  isRecording = false;
		  recordButton.setText("RECORD");
		  newRecord.finish();
		  
          //opens record folder after recording finished
            try {
				PC.open(recFile.getParentFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == recordButton) {
			if (!isRecording)
				startRecording();
			else
				stopRecording();
		}
		
	}
	
}

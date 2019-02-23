package talkbox;
import java.io.*;
import javax.sound.sampled.*;

public class Record {

	String recordName;
	
	File recordFile;
	AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	TargetDataLine line;
	
	Record(File filename){
		recordFile = filename;
		
	}
	
	AudioFormat getAudioFormat() {
		float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat audFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        
        return audFormat;
	}
	
	void start() {
		try {
			AudioFormat audFormat = getAudioFormat();
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, audFormat);
			
			if (!AudioSystem.isLineSupported(info)) {
				   System.out.println("Line not supported");
	                System.exit(0);
	            }
	            line = (TargetDataLine) AudioSystem.getLine(info);
	            line.open(audFormat);
	            line.start();   // start capturing
	 
	            System.out.println("Start capturing...");
	 
	            AudioInputStream ais = new AudioInputStream(line);
	 
	            System.out.println("Start recording....");
	 
	            // start recording
	            AudioSystem.write(ais, fileType, recordFile);
		} 
		catch (LineUnavailableException ex) {
			ex.printStackTrace();
	    } 
		catch (IOException ioe) {
			ioe.printStackTrace();
	    }
	}
	
	void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }
	
}

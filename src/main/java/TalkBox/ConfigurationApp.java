package main.java.TalkBox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ConfigurationApp implements TalkBoxConfiguration {

	private List<AudioSet> audioSets;
	private int currentAudioSet;
	public static int totalNumAudioSets = 0;
	private static File fileTalkBoxData = new File("TalkBoxData");
	public static String dirTalkBoxData = ConfigurationApp.fileTalkBoxData.getName();
	
	ConfigurationApp()
	{
		fileTalkBoxData.mkdir();
	}
		
	public int getCurrentAudioSet() {
		return currentAudioSet;
	}

	public void setCurrentAudioSet(int currentAudioSet) {
		this.currentAudioSet = currentAudioSet;
	}

	public void addAudioSet()
	{
		ConfigurationApp.totalNumAudioSets++;
		audioSets.add(new AudioSet(ConfigurationApp.totalNumAudioSets));
		currentAudioSet = ConfigurationApp.totalNumAudioSets;
	}
	
	public void removeAudioSet(int audioSet)
	{
		Collections.sort(this.audioSets);
		boolean removed = false;
		for (Iterator<AudioSet> iter = audioSets.iterator(); iter.hasNext(); )
		{
			AudioSet as = iter.next();
			if(removed == true)
			{
				as.setAudioSetNum(as.getAudioSetNum() - 1);
			}
			if(as.getAudioSetNum() == audioSet && removed == false)
			{
				iter.remove();
				removed = true;
			}
		}
		this.doDefaultAudioSetBehaviour();
	}
	
	public AudioSet getAudioSet(int audioSetNum)
	{
		for (Iterator<AudioSet> iter = audioSets.iterator(); iter.hasNext(); )
		{
			AudioSet as = iter.next();
			if(as.getAudioSetNum() == audioSetNum)
			{
				return as;
			}
		}
		return null;
	}
	
	public void serialize()
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(ConfigurationApp.dirTalkBoxData);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void doDefaultAudioSetBehaviour()
	{
		for(AudioSet a : this.audioSets)
		{
			a.normalizeSwapButtons();
		}
	}

	
	@Override
	public int getNumberOfAudioButtons() {
		return this.audioSets.get(0).numAudioButtons();
	}

	@Override
	public int getNumberOfAudioSets() {
		return this.audioSets.size();
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return this.audioSets.get(0).numAudioButtons() + this.audioSets.get(0).numSwapButtons();
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return Paths.get(ConfigurationApp.dirTalkBoxData);
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		String[][] return_ = new String[this.audioSets.size()][];
		int i = 0;
		for(AudioSet a : this.audioSets)
		{
			return_[i] = a.getAudioFileNames();
		}
		return return_;
	}
	
}

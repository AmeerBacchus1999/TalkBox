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
	private int size;
	private static File fileTalkBoxData = new File("TalkBoxData");
	public static String dirTalkBoxData = ConfigurationApp.fileTalkBoxData.getName();
	
	public ConfigurationApp()
	{
		fileTalkBoxData.mkdir();
		this.size = 0;
	}
	public AudioSet getAudioSet()
	{
		return this.audioSets.get(currentAudioSet);
	}
	
	public static int getTotalNumAudioSets()
	{
		return ConfigurationApp.getTotalNumAudioSets();
	}
	
	public void swapButtonPress(int location)
	{
		this.setCurrentAudioSet(getAudioSet().getNewAudioSet(location));
	}

	public void addAudioSet()
	{
		audioSets.add(new AudioSet(++size));
		currentAudioSet = size;
	}
	
	private void setCurrentAudioSet(int currentAudioSet)
	{
		this.currentAudioSet = currentAudioSet;
	}
	
	public void removeAudioSet(int audioSet)
	{
		/*
		for (Iterator<AudioSet> iter = audioSets.iterator(); iter.hasNext(); )
		{
			AudioSet as = iter.next();
			if(as.getAudioSetNum() == audioSet)
			{
				iter.remove();
			}
			if(as.getAudioSetNum() > audioSet)
			{
				as.setAudioSetNum(as.getAudioSetNum() - 1);
			}
			
		}
		*/
		this.audioSets.remove(audioSet - 1);
		this.normalizeAllAudioSets();
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
	
	public void setAudioForButton(int location, File audio)
	{
		getAudioSet().setAudioForButton(location, audio);
	}
	
	public void setImageForButton(int location, File image)
	{
		getAudioSet().setImageForButton(location, image);
	}
	
	public void resetValuesSwapButton(int location)
	{
		getAudioSet().resetValuesSwapButton(location);
	}
	
	public void addValueSwapButton(int location, int value)
	{
		getAudioSet().addValueSwapButton(location, value);
	}
	
	public void removeAudioButton(int location)
	{
		getAudioSet().removeAudioButton(location);
	}
	
	public void removeSwapButton(int location)
	{
		getAudioSet().removeSwapButton(location);
		getAudioSet().normalizeSwapButtons(size);
	}
	
	public void normalizeAllAudioSets()
	{
		for(AudioSet a : this.audioSets)
		{
			a.normalizeSwapButtons(size);
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

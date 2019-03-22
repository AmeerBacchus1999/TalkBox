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
	private static File fileTalkBoxData = new File("TalkBoxData");
	public static String dirTalkBoxData = ConfigurationApp.fileTalkBoxData.getName();
	
	public ConfigurationApp()
	{
		ConfigurationApp.fileTalkBoxData.mkdir();
		this.audioSets = new ArrayList<AudioSet>();
	}
	
	private AudioSet getAudioSet()
	{
		return this.audioSets.get(currentAudioSet - 1);
	}
	
	public void swapButtonPress(int location)
	{
		this.setCurrentAudioSet(getAudioSet().getNewAudioSet(location));
	}
	
	public void swapButtonPressAll(int location)
	{
		for(AudioSet a : this.audioSets)
		{
			this.setCurrentAudioSet(a.getNewAudioSet(location));
		}
	}
	

	public void addAudioSet()
	{
		audioSets.add(new AudioSet());
		currentAudioSet = this.getNumberOfAudioSets();
		this.normalizeAllAudioSets();
	}
	
	public void setCurrentAudioSet(int currentAudioSet)
	{
		this.currentAudioSet = currentAudioSet;
	}
	
	public void removeAudioSet(int audioSet)
	{
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
	
	public void addAudioButton()
	{
		getAudioSet().addAudioButton();
	}
	
	public void addSwapButtonToAllSets()
	{
		for(AudioSet a : this.audioSets)
		{
			a.addSwapButton();
		}
		this.normalizeAllAudioSets();
	}
	
	public void addAudioButtonToAllSets()
	{
		for(AudioSet a : this.audioSets)
		{
			a.addAudioButton();
		}

	}
	
	public void addSwapButton()
	{
		getAudioSet().addSwapButton();
		getAudioSet().normalizeSwapButtons(this.getNumberOfAudioSets());
	}
	
	public AudioButton getAudioButton(int location)
	{
		return getAudioSet().getAudioButton(location);
	}
	
	public SwapButton getSwapButton(int location)
	{
		return getAudioSet().getSwapButton(location);
	}
	
	public void removeAudioButton(int location)
	{
		getAudioSet().removeAudioButton(location);
	}
	
	public void removeAudioButtonAllSets(int location)
	{
		for(AudioSet a : this.audioSets)
		{
			a.removeAudioButton(location);
		}
	}
	
	public void removeSwapButton(int location)
	{
		getAudioSet().removeSwapButton(location);
		getAudioSet().normalizeSwapButtons(this.getNumberOfAudioSets());
	}
	
	public void removeSwapButtonAllSets(int location)
	{
		for(AudioSet a : this.audioSets)
		{
			a.removeSwapButton(location);
		}
		this.normalizeAllAudioSets();
	}
	
	private void normalizeAllAudioSets()
	{
		for(AudioSet a : this.audioSets)
		{
			a.normalizeSwapButtons(this.getNumberOfAudioSets());
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

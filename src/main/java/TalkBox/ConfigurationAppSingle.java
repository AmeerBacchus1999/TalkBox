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

public class ConfigurationAppSingle extends ConfigurationApp {

	private List<AudioSet> audioSets;
	private int currentAudioSet;
	private static File fileTalkBoxData = new File("TalkBoxData");
	public static String dirTalkBoxData = ConfigurationAppSingle.fileTalkBoxData.getName();
	
	public ConfigurationAppSingle()
	{
		super();
	}
	
	private AudioSet getAudioSet()
	{
		return this.audioSets.get(currentAudioSet - 1);
	}
	
	public void swapButtonPress(int location)
	{
		this.setCurrentAudioSet(getAudioSet().getNewAudioSet(location));
	}
	
	
	public void addAudioSet()
	{
		audioSets.add(new AudioSet());
		currentAudioSet = this.getNumberOfAudioSets();
		this.normalizeAllAudioSets();
	}
	
	public void addAudioButton()
	{
		getAudioSet().addAudioButton();
	}
	
	
	public void addSwapButton()
	{
		getAudioSet().addSwapButton();
		getAudioSet().normalizeSwapButtons(this.getNumberOfAudioSets());
	}
	
	public void removeAudioButton(int location)
	{
		getAudioSet().removeAudioButton(location);
	}
	
	public void removeSwapButton(int location)
	{
		getAudioSet().removeSwapButton(location);
		getAudioSet().normalizeSwapButtons(this.getNumberOfAudioSets());
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
		if(this.audioSets.size() == 0)
		{
			return 0;
		}
		return this.audioSets.get(0).numAudioButtons();
	}

	@Override
	public int getNumberOfAudioSets() {
		return this.audioSets.size();
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		if(this.audioSets.size() == 0)
		{
			return 0;
		}
		return this.audioSets.get(0).numAudioButtons() + this.audioSets.get(0).numSwapButtons();
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return Paths.get(ConfigurationAppSingle.dirTalkBoxData);
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


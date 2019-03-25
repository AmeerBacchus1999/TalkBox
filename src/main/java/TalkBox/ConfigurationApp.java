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
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

public class ConfigurationApp implements TalkBoxConfiguration {

	private AudioButton[][] audioButtons;
	private SwapButton[] swapButtons;
	private int currentAudioSet;
	private static File fileTalkBoxData = new File("TalkBoxData");
	public static String dirTalkBoxData = ConfigurationApp.fileTalkBoxData.getName();
	
	public ConfigurationApp(int numAudioSets, int numAudioButtons, int numSwapButtons)
	{
		if(numAudioSets < 1 || numAudioButtons < 1)
		{
			throw new IllegalArgumentException();
		}
		ConfigurationApp.fileTalkBoxData.mkdir();
		this.audioButtons = new AudioButton[numAudioSets][numAudioButtons];
		this.swapButtons = new SwapButton[numSwapButtons];
		int currentAudioSet = 1;
		for(int i = 0; i < swapButtons.length; i++)
		{
			
		}
	}
	
	public AudioButton[] getAudioButtons()
	{
		return audioButtons[currentAudioSet - 1];
	}
	
	public void setCurrentAudioSet(int currentAudioSet)
	{
		if(currentAudioSet > this.getNumberOfAudioSets() ||
				currentAudioSet < 1)
		{
			throw new IllegalArgumentException();
		}
		this.currentAudioSet = currentAudioSet;
	}
	
	
	
	public int getCurrentAudioSet()
	{
		return this.currentAudioSet;
	}
	
	
	
	@Override
	public int getNumberOfAudioButtons() {
		return this.audioButtons[0].length;
	}

	@Override
	public int getNumberOfAudioSets() {
		return this.audioButtons.length;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return this.getNumberOfAudioButtons() + this.swapButtons.length; 
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return Paths.get(ConfigurationApp.dirTalkBoxData);
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

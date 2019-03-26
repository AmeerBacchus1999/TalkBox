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

	private AudioButton[][] audioSets;
	private SwapButton[] swapButtons;
	private int currentAudioSet;
	public static String dir = "TalkBoxData";
	
	public ConfigurationApp(int numaudioSetsOfButtons, int numAudioButtons, int numSwapButtons)
	{
		if(numaudioSetsOfButtons < 1 || numAudioButtons < 1)
		{
			throw new IllegalArgumentException();
		}
		
		this.audioSets = new AudioButton[numaudioSetsOfButtons][numAudioButtons];
		this.swapButtons = new SwapButton[numSwapButtons];
		int currentAudioSet = 1;
		this.instantiateSwapButtons();
		
	}
	
	public AudioButton[] getAudioButtons()
	{
		return audioSets[currentAudioSet - 1];
	}
	
	public Iterator[] getIterators()
	{
		return (Iterator[]) this.swapButtons;
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
	
	private void instantiateSwapButtons()
	{
		for(int i = 0; i < swapButtons.length; i++)
		{
			List<Integer> values = new ArrayList<Integer>();
			if(swapButtons.length > this.getNumberOfAudioSets())
			{
				values.add(i % this.getNumberOfAudioSets() + 1);	
			}
			else
			{
				if(i == swapButtons.length - 1)
				{
					for(; i < this.getNumberOfAudioSets(); i++)
					{
						values.add(i+1);
					}
				}
				else
				{
					values.add(i + 1);
				}
			}
			this.swapButtons[i] = new SwapButton(values);
		}
	}
	
	
	
	
	@Override
	public int getNumberOfAudioButtons() {
		return this.audioSets[0].length;
	}

	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return this.audioSets.length;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return this.getNumberOfAudioButtons() + this.swapButtons.length; 
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return Paths.get(ConfigurationApp.dir);
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		String[][] audioSetFileNames = new String[audioSets.length]
				[audioSets[0].length];
		for(int i = 0; i < audioSets.length; i++)
		{
			for(int j = 0; j < audioSets[0].length; j++)
			{
				audioSetFileNames[i][j] = 
						this.audioSets[i][j].getAudio();			
			}
		}
		return audioSetFileNames;
	}

	
	
}

package main.java.TalkBox;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AudioSet implements Comparable<AudioSet> {
	private List<AudioButton> audioButtons;
	private List<SwapButton> swapButtons;
	private int audioSetNum;

	AudioSet(int audioSetNum)
	{
		this.audioSetNum = audioSetNum;
	}
	
	public int numAudioButtons()
	{
		return this.audioButtons.size();
	}
	
	public int numSwapButtons()
	{
		return this.swapButtons.size();
	}
	
	public int getAudioSetNum() {
		return audioSetNum;
	}

	public void setAudioSetNum(int audioSetNum) {
		this.audioSetNum = audioSetNum;
	}
	
	public String[] getAudioFileNames()
	{
		String[] audioFileNames = new String[this.audioButtons.size()];
		int i = 0;
		for(AudioButton a : this.audioButtons)
		{
			audioFileNames[i] = a.getAudio().getName();
			i++;
		}
		return audioFileNames;
	}
	
	
	public void addAudioButton(int location)
	{
		this.audioButtons.add(new AudioButton(location));
		Collections.sort(this.audioButtons);
	}
	
	public void addSwapButton(int location)
	{
		this.swapButtons.add(new SwapButton(location));
		Collections.sort(this.swapButtons);
		normalizeSwapButtons();
	}
	
	private AudioButton getAudioButton(int location)
	{
		for(AudioButton a : this.audioButtons)
		{
			if(a.getLocation() == location)
			{
				return a;
			}
		}
		return null;
	}
	
	private SwapButton getSwapButton(int location)
	{
		for(SwapButton s : this.swapButtons)
		{
			if(s.getLocation() == location)
			{
				return s;
			}
		}
		return null;
	}
	
	public void setAudioForButton(int location, File audio)
	{
		getAudioButton(location).setAudio(audio);
	}
	
	public void setImageForButton(int location, File image)
	{
		getAudioButton(location).setImage(image);
	}
	
	public void resetValuesSwapButton(int location)
	{
		getSwapButton(location).resetValues();
	}
	
	public void addValueSwapButton(int location, int value)
	{
		getSwapButton(location).addValue(value);
	}
	
	public void removeTalkBoxButton(int location)
	{
		Collections.sort(this.audioButtons);
		Collections.sort(this.swapButtons);
		boolean decrementLocation = false;
		boolean normalize;
		for (Iterator<AudioButton> iter = audioButtons.iterator(); iter.hasNext(); ) 
		{
			AudioButton ab = iter.next();
			if(decrementLocation == true)
			{
				ab.setLocation(ab.getLocation() - 1);
			}
			if(ab.getLocation() == location && decrementLocation == false)
			{
				iter.remove();
				decrementLocation = true;
			}
			
		}
		normalize = decrementLocation;
		decrementLocation = false;
		
		
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); ) 
		{
			SwapButton sw = iter.next();
			if(decrementLocation == true)
			{
				sw.setLocation(sw.getLocation() - 1);
			}
			if(sw.getLocation() == location && decrementLocation == false)
			{
				iter.remove();
				decrementLocation = true;
			}
			
		}
		if(normalize)
		{
			normalizeSwapButtons();
		}
		
	}
	
	public void normalizeSwapButtons()
	{
		int currentAudioSet = 1;
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); )
		{
			SwapButton sw = iter.next();
			if(swapButtons.size() >  ConfigurationApp.totalNumAudioSets)
			{
				sw.addValue(((currentAudioSet - 1) % ConfigurationApp.totalNumAudioSets) + 1);
			}
			else
			{
				if(iter.hasNext() == false)
				{
					for(; currentAudioSet < ConfigurationApp.totalNumAudioSets + 1; currentAudioSet++)
					{
						sw.addValue(currentAudioSet);
					}
				}
				else
				{
					sw.addValue(currentAudioSet);
				}
			}
				
			currentAudioSet++;
		}
		

	}

	@Override
	public int compareTo(AudioSet o) {
		// TODO Auto-generated method stub
		return this.audioSetNum - o.audioSetNum;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AudioSet)) {
			return false;
		}
		AudioSet other = (AudioSet) obj;
		if (audioButtons == null) {
			if (other.audioButtons != null) {
				return false;
			}
		} else if (!audioButtons.equals(other.audioButtons)) {
			return false;
		}
		if (audioSetNum != other.audioSetNum) {
			return false;
		}
		
		if (swapButtons == null) {
			if (other.swapButtons != null) {
				return false;
			}
		} else if (!swapButtons.equals(other.swapButtons)) {
			return false;
		}
		return true;
	}
	
	
	
	
	
}

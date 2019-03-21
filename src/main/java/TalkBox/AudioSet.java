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
	//private List<TalkBoxButton> talkBoxButtons;
	private int size;
	private int audioSetNum;

	public AudioSet(int audioSetNum)
	{
		this.audioSetNum = audioSetNum;
		this.audioButtons = new ArrayList<AudioButton>();
		this.swapButtons = new ArrayList<SwapButton>();
		//this.talkBoxButtons = new ArrayList<TalkBoxButton>();
		this.size = 0;
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
	
	
	public void addAudioButton()
	{
		this.audioButtons.add(new AudioButton(++this.size));
	}
	
	public void addSwapButton()
	{
		if(!AudioSet.isValidAudioSet(this.audioButtons.size(), this.numSwapButtons() + 1))
		{
			return;
		}
		this.swapButtons.add(new SwapButton(++this.size));
		
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
	
	/*
	public void removeTalkBoxButton(int location)
	{
		
		boolean normalize = true;
		for (Iterator<AudioButton> iter = audioButtons.iterator(); iter.hasNext(); ) 
		{
			AudioButton ab = iter.next();
			if(ab.getLocation() == location)
			{
				iter.remove();
				normalize = false;
			}
			if(ab.getLocation() > location)
			{
				ab.setLocation(ab.getLocation() - 1);
			}
			
			
		}
		
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); ) 
		{
			SwapButton sw = iter.next();
			if(sw.getLocation() == location)
			{
				iter.remove();
			}
			if(sw.getLocation() > location)
			{
				sw.setLocation(sw.getLocation() - 1);
			}
		}
		if(normalize)
		{
			normalizeSwapButtons();
		}
		
	} */
	
	public void removeAudioButton(int location)
	{
		for (Iterator<AudioButton> iter = audioButtons.iterator(); iter.hasNext(); ) 
		{
			AudioButton ab = iter.next();
			if(ab.getLocation() == location)
			{
				iter.remove();
			}
			if(ab.getLocation() > location)
			{
				ab.setLocation(ab.getLocation() - 1);
			}
			
			
		}
		size--;
	}
	
	public void removeSwapButton(int location)
	{
		if(!AudioSet.isValidAudioSet(this.audioButtons.size(), this.numSwapButtons() - 1))
		{
			return;
		}
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); ) 
		{
			SwapButton sw = iter.next();
			if(sw.getLocation() == location)
			{
				iter.remove();
			}
			if(sw.getLocation() > location)
			{
				sw.setLocation(sw.getLocation() - 1);
			}
		}
		size--;
	}
	
	public void normalizeSwapButtons(int totalNumAudioSets)
	{
		int currentAudioSet = 1;
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); )
		{
			SwapButton sw = iter.next();
			sw.resetValues();
			if(swapButtons.size() >  totalNumAudioSets)
			{
				sw.addValue(((currentAudioSet - 1) % totalNumAudioSets) + 1);
			}
			else
			{
				if(iter.hasNext() == false)
				{
					for(; currentAudioSet < totalNumAudioSets + 1; currentAudioSet++)
					{
						sw.addValue(currentAudioSet);
					}
					return;
				}
				else
				{
					sw.addValue(currentAudioSet);
				}
			}
				
			currentAudioSet++;
		}
		

	}
	
	public int getNewAudioSet(int location)
	{
		return this.getSwapButton(location).next();
	}
	
	/*
	public boolean validAudioSet()
	{
		this.talkBoxButtons = new ArrayList<TalkBoxButton>();
		this.talkBoxButtons.addAll(this.audioButtons);
		this.talkBoxButtons.addAll(this.swapButtons);
		Collections.sort(this.talkBoxButtons);
		if(this.talkBoxButtons.size() == 0)
		{
			return true;
		}
		else
		{
			if(this.audioButtons.size() == 0)
			{
				return false;
			}
		}
			
		int i = 1;
		
		for(TalkBoxButton t : this.talkBoxButtons)
		{
			if(t.getLocation() != i)
			{
				return false;
			}
			i++;
		}
		return true;
	}
	*/
	
	private static boolean isValidAudioSet(int audioButtonsSize, int swapButtonsSize)
	{
		if(swapButtonsSize > 0 && audioButtonsSize == 0)
		{
			return false;
		}
		return true;
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

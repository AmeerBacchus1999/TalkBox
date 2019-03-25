package main.java.TalkBox;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AudioSet {
	private List<AudioButton> audioButtons;
	private List<SwapButton> swapButtons;
	

	public AudioSet()
	{
		this.audioButtons = new ArrayList<AudioButton>();
		this.swapButtons = new ArrayList<SwapButton>();
	}
	
	public AudioSet(List<AudioButton> audioButtons, List<SwapButton> swapButtons)
	{
		this.audioButtons = new ArrayList<AudioButton>(audioButtons);
		this.swapButtons = new ArrayList<SwapButton>(swapButtons);
	}
	
	public List<AudioButton> getAudioButtons()
	{
		List<AudioButton> list = new ArrayList<AudioButton>();
		for(AudioButton a : this.audioButtons)
		{
			list.add(new AudioButton(a));
		}
		return list;
	}
	
	public List<SwapButton> getSwapButtons()
	{
		List<SwapButton> list = new ArrayList<SwapButton>();
		for(SwapButton s : this.swapButtons)
		{
			list.add(new SwapButton(s));
		}
		return list;
	}
	
	private int size()
	{
		return numAudioButtons() + numSwapButtons();
	}
	
	public int numAudioButtons()
	{
		return this.audioButtons.size();
	}
	
	public int numSwapButtons()
	{
		return this.swapButtons.size();
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
		if(this.isValidAudioSet(this.audioButtons.size() + 1, this.numSwapButtons()) == false)
		{
			return;
		}
		this.audioButtons.add(new AudioButton(size() + 1));
	}
	
	public void addSwapButton()
	{
		if(this.isValidAudioSet(this.audioButtons.size(), this.numSwapButtons() + 1) == false)
		{
			return;
		}
		this.swapButtons.add(new SwapButton(size() + 1));
		
	}
	
	public AudioButton getAudioButton(int location)
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
	
	public SwapButton getSwapButton(int location)
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
	
	public void removeAudioButton(int location)
	{
		if(this.isValidAudioSet(this.audioButtons.size() - 1, this.numSwapButtons()) == false)
		{
			return;
		}
		for (Iterator<AudioButton> iter = audioButtons.iterator(); iter.hasNext(); ) 
		{
			AudioButton ab = iter.next();
			if(ab.getLocation() == location)
			{
				iter.remove();
				decrementLocations(location);
				return;
			}
		}
	}
	
	public void removeSwapButton(int location)
	{
		
		if(this.isValidAudioSet(this.audioButtons.size(), this.numSwapButtons() - 1) == false)
		{
			return;
		}
		
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); ) 
		{
			SwapButton sw = iter.next();
			if(sw.getLocation() == location)
			{
				iter.remove();
				decrementLocations(location);
				return;
			}
		}
		
	}
	
	private void decrementLocations(int location)
	{
		for(AudioButton a : this.audioButtons)
		{
			if(a.getLocation() > location)
			{
				a.setLocation(a.getLocation() - 1);
			}
		}
		for(SwapButton s : this.swapButtons)
		{
			if(s.getLocation() > location)
			{
				s.setLocation(s.getLocation() - 1);
			}
		}
	}
	
	public void normalizeSwapButtons(int totalNumAudioSets)
	{
		int currentAudioSet = 1;
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); )
		{
			SwapButton sw = iter.next();
			sw.reset();
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
	
	private boolean isValidAudioSet(int audioButtonsSize, int swapButtonsSize)
	{
		if(swapButtonsSize > 0 && audioButtonsSize == 0)
		{
			return false;
		}
		return true;
	}

	
	
	
}

package main.java.TalkBox;

import java.util.ArrayList;
import java.util.List;

public class AudioSet {
	private List<AudioButton> audioButtons;
	private List<SwapButton> swapButtons;
	SwapNormalized s = SwapNormalized.NO;
	public enum SwapNormalized { YES, NO}
	
	
	AudioSet()
	{
		
	}
	
	public void addAudioButton()
	{
		this.audioButtons.add(new AudioButton());
	}
	
	public void addSwapButton()
	{
		s = SwapNormalized.NO;
		this.swapButtons.add(new SwapButton());
	}
	
	private void removeAudioButton(int location)
	{
		boolean updateLocation = false;
		for(AudioButton a : audioButtons)
		{
			if(a.getLocation() == location)
			{
				audioButtons.remove(location);
				updateLocation = true;
			}
			if(updateLocation)
			{
				a.setLocation(location);
			}
		}
	}
	
	private void removeSwapButton(int location)
	{
		s = SwapNormalized.NO;
		for(SwapButton sw : swapButtons)
		{
			if(sw.getLocation() == location)
			{
				swapButtons.remove(location);
			}
		}
		
	}
	
	public void removeButton(int location)
	{
		removeAudioButton(location);
		removeSwapButton(location);
	}
	
	public void normalizeSwapButtons()
	{
		if(s == SwapNormalized.NO)
		{
			
			for(int i = 0; i < swapButtons.size(); i++) 
			{
				SwapButton sw = swapButtons.get(i);
				if(i == swapButtons.size()-1 && i < ConfigurationApp.numAudioSets)
				{
					for(; i < ConfigurationApp.numAudioSets; i++)
					{
						sw.addValue(i + 1);
					}
				}
				else
				{
					sw.addValue((i % ConfigurationApp.numAudioSets) + 1 );
				}
				
				
				
			}
			s = SwapNormalized.YES;
		}
	}
	
	
	
	
	
}

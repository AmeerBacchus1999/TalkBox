package main.java.TalkBox;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	private void removeTalkBoxButton(int location)
	{
		for (Iterator<AudioButton> iter = audioButtons.iterator(); iter.hasNext(); ) 
		{
			AudioButton next = iter.next();
			if(next.getLocation() == location)
			{
				iter.remove();
				decrementLocations(location);
				return;
			}
		}
		s = SwapNormalized.NO;
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); ) 
		{
			SwapButton next = iter.next();
			
			if(next.getLocation() == location)
			{
				iter.remove();
				decrementLocations(location);
				return;
			}
		}
	}
	
	private void decrementLocations(int location)
	{
		for (Iterator<AudioButton> iter = audioButtons.iterator(); iter.hasNext(); ) 
		{
			AudioButton next = iter.next();
			if(next.getLocation() > location)
			{
				next.setLocation(next.getLocation() - 1);
			}
		}
		for (Iterator<SwapButton> iter = swapButtons.iterator(); iter.hasNext(); ) 
		{
			SwapButton next = iter.next();
			
			if(next.getLocation() > location)
			{
				next.setLocation(next.getLocation() - 1);
			}
		}
	}
	
	public void normalizeSwapButtons(int numAudioSets)
	{
		if(s == SwapNormalized.NO)
		{
			
			for(int i = 0; i < swapButtons.size(); i++) 
			{
				SwapButton sw = swapButtons.get(i);
				if(i == swapButtons.size()-1 && i < numAudioSets)
				{
					for(; i < numAudioSets; i++)
					{
						sw.addValue(i + 1);
					}
				}
				else
				{
					sw.addValue((i % numAudioSets) + 1 );
				}
				
				
				
			}
			s = SwapNormalized.YES;
		}
	}
	
	
	
	
	
}
